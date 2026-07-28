import tkinter as tk
import time
from dataclasses import dataclass
from typing import List, Tuple, Optional
from AI.Advanced_Heuristics.god_ai import GodAI
from AI.Advanced_Heuristics.aggressive_ai import AggressiveAI
from AI.Advanced_Heuristics.safety_ai import SafetyAI
from AI.Heuristics.simple_ai import SimpleAI
from AI.Heuristics.line_ai import LineAI
from AI.Heuristics.edge_ai import EdgeAI
from AI.Heuristics.random_ai import RandomAI
from AI.Heuristics.pawns_ai import PawnsAI
from AI.Heuristics.kings_ai import KingsAI
from AI.Heuristics.safePawns_ai import SafePawnsAI
from AI.Heuristics.safeKings_ai import SafeKingsAI
from AI.Heuristics.promotion_ai import PromotionAI
from AI.Advanced_Heuristics.heuristic_mcts_ai import PureMCTSAI, GuidedMCTSAI
from game_logic import RulesEngine, GameState, Move

Pos = Tuple[int, int]

#Instructions for adding your own AI!
#1. Create a new class in the AI folder that inherits from BaseAI.
#2. Implement the heuristic function to evaluate the game state.
#3. Optionally, override the choose_move method if you want to implement a different move selection strategy.
#4. In the DraughtsUI class, replace the AI instantiation with your new AI class for which player you want. 
#4 - Line 140 is AI selection
#5 OPTIONAL: Leave ai2 as none if you want to play against your AI instead of having two AIs play against each other.
#6. Run draughts.py
#I MADE A CHANGE

# =========================================================
# CONTROLLER (TURN + RULES ORCHESTRATION)
# =========================================================

class GameController:
    def __init__(self, state, ai, ai2=None):
        self.state = state
        self.ai = ai
        if ai2 is not None:
            self.ai2 = ai2
        self.chain_piece = None
        self.chain_move_start = None
        self.move_history = []
        self.move_number = 1

    def apply_move(self, move: Move):
        board = self.state.board
        piece = board[move.path[0][0]][move.path[0][1]]

        # remove origin
        board[move.path[0][0]][move.path[0][1]] = 0

        # remove captures
        for r, c in move.captures:
            board[r][c] = 0

        # place final
        er, ec = move.path[-1]
        board[er][ec] = piece

        # promotion
        if piece == 1 and er == 0:
            board[er][ec] = 2
        elif piece == -1 and er == 9:
            board[er][ec] = -2

        # capture chains can extend the same move
        if move.captures:
            if self.chain_move_start is None:
                self.chain_move_start = move.path[0]

            further = RulesEngine.get_captures(self.state, (er, ec))
            if further:
                self.chain_piece = (er, ec)
                return  # same turn continues

        # record completed move after chain ends or normal move
        start = self.chain_move_start if self.chain_move_start is not None else move.path[0]
        end = move.path[-1]
        self.record_move(start, end)
        self.chain_move_start = None
        self.chain_piece = None

        # END TURN
        self.state.turn *= -1

    def record_move(self, start, end):
        player = "Red" if self.state.turn == 1 else "Blue"
        formatted_start = f"({start[0] + 1},{start[1] + 1})"
        formatted_end = f"({end[0] + 1},{end[1] + 1})"
        move_text = f"Move {self.move_number}: {player}: From{formatted_start} to {formatted_end}"
        board_text = self.format_board(self.state.board)
        history_entry = f"{move_text}\n{board_text}"

        self.move_history.append(history_entry)
        self.move_number += 1

    def format_board(self, board):
        symbol_map = {
            0: '.',
            1: 'r',
            2: 'R',
            -1: 'b',
            -2: 'B'
        }
        header = '   ' + ' '.join(f'{c+1:>2}' for c in range(10))
        rows = [header]
        for r in range(10):
            line = f'{r+1:>2} ' + ' '.join(symbol_map[board[r][c]] for c in range(10))
            rows.append(line)
        return '\n'.join(rows)

    def ai_turn(self):
        if self.state.turn == 1 and hasattr(self, "ai2"):
            move = self.ai2.choose_move(self.state)  # RED AI
        else:
            move = self.ai.choose_move(self.state)   # BLUE AI

        if move:
            self.apply_move(move)


# =========================================================
# TKINTER UI
# =========================================================

class DraughtsUI:
    def __init__(self, root, ai=SafetyAI, ai2=None):
        self.root = root
        self.ai = ai  # Blue AI
        self.ai2 = ai2  # Red AI
        self.root.title(f"ai1: {self.ai.__class__.__name__} (Blue) vs ai2: {self.ai2.__class__.__name__ if self.ai2 else 'Human'} (Red)")
        self.highlighted = []

        board = [[0]*10 for _ in range(10)]

        # setup pieces
        for r in range(4):
            for c in range(10):
                if (r + c) % 2:
                    board[r][c] = -1

        for r in range(6, 10):
            for c in range(10):
                if (r + c) % 2:
                    board[r][c] = 1

        self.state = GameState(board=board, turn=1)
        self.controller = GameController(self.state, self.ai, self.ai2)

        self.selected = None
        self.moves = []
        self.winner = None
        self.game_finished = False

        self.squares = [[None]*10 for _ in range(10)]
        self.total_time_ai1 = 0.0
        self.count_ai1 = 0
        self.max_time_ai1 = 0.0
        self.min_time_ai1 = float('inf')
        self.total_time_ai2 = 0.0
        self.count_ai2 = 0
        self.max_time_ai2 = 0.0
        self.min_time_ai2 = float('inf')
        self.build_board()

        self.refresh()

    # ---------------- UI ----------------

    def build_board(self):
        for r in range(10):
            for c in range(10):
                cell = tk.Canvas(self.root, width=50, height=50,
                                 bg="white" if (r+c)%2==0 else "black")
                cell.grid(row=r, column=c)
                cell.bind("<Button-1>", lambda e, rr=r, cc=c: self.on_click(rr, cc))
                self.squares[r][c] = cell

    def draw(self):
        for r in range(10):
            for c in range(10):
                cell = self.squares[r][c]
                cell.delete("all")

                val = self.state.board[r][c]
                if val != 0:
                    color = "red" if val > 0 else "blue"
                    cell.create_oval(10, 10, 40, 40, fill=color)
                
                if abs(val) == 2:
                    cell.create_text(25, 25, text="K", fill="white", font=("Arial", 16, "bold"))

    def refresh(self):
        self.moves = RulesEngine.get_all_moves(self.state)
        self.clear_highlights()
        self.draw()

        # Check for winner
        winner = RulesEngine.check_winner(self.state)
        if winner != 0:
            self.end_game(winner)
            return

        if hasattr(self.controller, "ai2") or self.state.turn == -1:
            self.root.after(200, self.ai_loop)
    
    def end_game(self, winner):
        """Store winner and close the game"""
        if winner == 1:
            self.winner = "Red"
            result = "Red Wins!"
        elif winner == -1:
            self.winner = "Blue"
            result = "Blue Wins!"
        elif winner == 10:
            self.winner = "Draw"
            result = "It's a Draw!"
        else:
            return
        
        self.game_finished = True
        self.root.quit()
    
    def clear_highlights(self):
        for r, c in self.highlighted:
            self.squares[r][c].config(bg="white" if (r + c) % 2 == 0 else "black")
        self.highlighted = []
    
    def highlight_moves(self, moves):
        self.clear_highlights()

        targets = set(m.path[-1] for m in moves)

        for r, c in targets:
            self.squares[r][c].config(bg="yellow")

        self.highlighted = list(targets)

    # ---------------- INPUT ----------------

    def on_click(self, r, c):
        if self.state.turn == 1 and hasattr(self.controller, "ai2"):
            return  # Red is AI
        if self.state.turn == -1:
            return  # Blue is AI

        # get ALL moves for current position
        moves_here = [m for m in self.moves if m.path[0] == (r, c)]

        # selecting a piece
        if moves_here:
            self.selected = (r, c)
            self.highlight_moves(moves_here)
            return

        # attempting move
        if self.selected:
            for m in self.moves:
                if m.path[0] == self.selected and m.path[-1] == (r, c):
                    self.controller.apply_move(m)
                    self.selected = None
                    self.clear_highlights()
                    self.refresh()
                    return

        # invalid click resets selection
        self.selected = None
        self.clear_highlights()

    def try_move(self, r, c):
        for m in self.moves:
            if m.path[0] == (r, c):
                pass

    # ---------------- AI LOOP ----------------

    def ai_loop(self):
        if self.game_finished:
            return
        start_time = time.perf_counter()
        self.controller.ai_turn()
        end_time = time.perf_counter()
        elapsed = end_time - start_time
        if self.state.turn == 1:
            self.total_time_ai2 += elapsed
            self.count_ai2 += 1
            self.max_time_ai2 = max(self.max_time_ai2, elapsed)
            if elapsed < self.min_time_ai2:
                self.min_time_ai2 = elapsed
        elif self.state.turn == -1:
            self.total_time_ai1 += elapsed
            self.count_ai1 += 1
            self.max_time_ai1 = max(self.max_time_ai1, elapsed)
            if elapsed < self.min_time_ai1:
                self.min_time_ai1 = elapsed
        self.refresh()


# =========================================================
# RUN
# =========================================================

if __name__ == "__main__":
    root = tk.Tk()
    ai1 = SimpleAI(depth=3)  # Blue AI
    ai2 = SafePawnsAI(depth=3) # Red AI
    app = DraughtsUI(root, ai1, ai2)
    root.mainloop()
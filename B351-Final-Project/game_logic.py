from dataclasses import dataclass
from typing import List, Tuple
import copy
'''
    Simple data structures and rules engine for the draughts game.
    Board representation:
    0 = empty
    1 = red
    2 = red king
    -1 = blue
    -2 = blue king
    Turn: 1 = red, -1 = blue
'''
Pos = Tuple[int, int]

# Global state history for draw detection
state_history = {}

@dataclass
class Move:
    path: List[Pos]
    captures: List[Pos]

@dataclass
class GameState:
    board: List[List[int]]
    turn: int  # 1 = red, -1 = blue

# =========================================================
# RULES ENGINE (PURE LOGIC)
# =========================================================

class RulesEngine:

    @staticmethod
    def in_bounds(r, c):
        return 0 <= r < 10 and 0 <= c < 10
    
    # ---------------- WIN CONDITION ----------------

    @staticmethod
    def check_winner(state: GameState):
        red_pawns, red_kings = 0, 0
        blue_pawns, blue_kings = 0, 0

        for r in state.board:
            red_pawns += r.count(1)
            red_kings += r.count(2)
            blue_pawns += r.count(-1)
            blue_kings += r.count(-2)

        if red_pawns + red_kings == 0:
            return -1  # Blue wins
        elif blue_pawns + blue_kings == 0:
            return 1   # Red wins

        # If the current player has no legal moves, they lose
        if not RulesEngine.get_all_moves(state):
            return -state.turn

        if RulesEngine.check_draw(state):
            return 10   # Draw
        return 0   # No winner yet
        
    @staticmethod
    def check_draw(state: GameState):
        #Captures current game state and checks if it has occurred before. If the same state occurs 3 times, it's a draw.
        state_tuple = tuple(tuple(row) for row in state.board)
        if state_tuple in state_history:
            state_history[state_tuple] += 1
        else:            
            state_history[state_tuple] = 1
        return state_history[state_tuple] >= 3


    # ---------------- MOVE GENERATION ----------------

    @staticmethod
    def get_all_moves(state: GameState):
        captures = []
        normals = []

        for r in range(10):
            for c in range(10):
                piece = state.board[r][c]
                if piece == 0 or (piece > 0) != (state.turn > 0):
                    continue

                caps = RulesEngine.get_captures(state, (r, c))
                if caps:
                    captures.extend(caps)
                else:
                    normals.extend(RulesEngine.get_moves(state, (r, c)))

        # MAX CAPTURE RULE
        if captures:
            max_len = max(len(m.captures) for m in captures)
            return [m for m in captures if len(m.captures) == max_len]

        return normals

    @staticmethod
    def get_moves(state, pos):
        r, c = pos
        piece = state.board[r][c]
        moves = []

        directions = [(-1,-1), (-1,1), (1,-1), (1,1)]

        # man
        if abs(piece) == 1:
            forward = -1 if piece == 1 else 1
            for dr, dc in [(forward, -1), (forward, 1)]:
                nr, nc = r + dr, c + dc
                if RulesEngine.in_bounds(nr, nc) and state.board[nr][nc] == 0:
                    moves.append(Move(path=[pos, (nr, nc)], captures=[]))

        # king
        else:
            for dr, dc in directions:
                nr, nc = r + dr, c + dc
                while RulesEngine.in_bounds(nr, nc):
                    if state.board[nr][nc] != 0:
                        break
                    moves.append(Move(path=[pos, (nr, nc)], captures=[]))
                    nr += dr
                    nc += dc

        return moves

    # ---------------- CAPTURE GENERATION ----------------

    @staticmethod
    def get_captures(state, pos):
        results = []
        RulesEngine.dfs_catpure(state.board, pos, [pos], [], results)
        return [Move(path=p, captures=c) for p, c in results]

    @staticmethod
    def dfs_catpure(board, pos, path, caps, results):
        r, c = pos
        piece = board[r][c]

        found = False
        directions = [(-1,-1), (-1,1), (1,-1), (1,1)]

        # ================= MAN =================
        if abs(piece) == 1:
            for dr, dc in directions:
                nr, nc = r + dr, c + dc
                jr, jc = r + 2*dr, c + 2*dc

                if RulesEngine.in_bounds(jr, jc):
                    target = board[nr][nc]

                    if target != 0 and (target > 0) != (piece > 0) and board[jr][jc] == 0:

                        new_board = copy.deepcopy(board)
                        new_board[r][c] = 0
                        new_board[nr][nc] = 0
                        new_board[jr][jc] = piece

                        RulesEngine.dfs_catpure(
                            new_board,
                            (jr, jc),
                            path + [(jr, jc)],
                            caps + [(nr, nc)],
                            results
                        )
                        found = True

        # king flying captures
        else:
            for dr, dc in directions:
                tr, tc = r + dr, c + dc
                enemy_found = False
                enemy_pos = None

                while RulesEngine.in_bounds(tr, tc):
                    if board[tr][tc] == 0:
                        # empty square BEFORE enemy → keep scanning
                        if not enemy_found:
                            tr += dr
                            tc += dc
                            continue

                        # empty square AFTER enemy → VALID LANDING
                        new_board = copy.deepcopy(board)
                        new_board[r][c] = 0
                        er, ec = enemy_pos
                        new_board[er][ec] = 0
                        new_board[tr][tc] = piece

                        RulesEngine.dfs_catpure(
                            new_board,
                            (tr, tc),
                            path + [(tr, tc)],
                            caps + [enemy_pos],
                            results
                        )
                        found = True

                    else:
                        # encountered a piece
                        if (board[tr][tc] > 0) != (piece > 0) and not enemy_found:
                            enemy_found = True
                            enemy_pos = (tr, tc)
                        else:
                            # blocked (own piece OR second enemy)
                            break

                    tr += dr
                    tc += dc

        if not found and len(path) > 1:
            results.append((path, caps))


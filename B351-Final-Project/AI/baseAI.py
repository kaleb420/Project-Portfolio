import copy
from game_logic import GameState, RulesEngine
'''
    BaseAI class with minimax and alpha-beta pruning.
    To create a new AI, inherit from this class and implement the heuristic function.
    The choose_move method can also be overridden for different move selection strategies.
    
    heuristic guide:
    A high score is good for red (1) and bad for blue (-1). 
    So positive scores favor red, negative scores favor blue.
'''
class BaseAI:
    def __init__(self, depth=2):
        self.depth = depth
        self.maximizing = False  # will be set in choose_move based on current turn
        self.minimizing = False
        self.name = "BaseAI"  # Default name
        

    def heuristic(self, state):
        return 0
    
    def choose_move(self, state):
        return self.get_move(state)

    def get_move(self, state):
        best_score = float("-inf") if state.turn == 1 else float("inf")
        best_move = None
       
        moves = RulesEngine.get_all_moves(state)

        for move in moves:
            new_state = self.simulate_move(state, move)

            score = self.minimax(new_state, self.depth - 1, float("-inf"), float("inf"), new_state.turn == 1)

            if state.turn == 1:
                if score > best_score:
                    best_score = score
                    best_move = move
            else:
                if score < best_score:
                    best_score = score
                    best_move = move

        return best_move

    def minimax(self, state, depth, alpha, beta, maximizing):
        moves = RulesEngine.get_all_moves(state)

        if depth == 0 or not moves:
            return self.heuristic(copy.deepcopy(state))

        if maximizing:
            max_eval = float("-inf")
            for move in moves:
                new_state = self.simulate_move(state, move)
                eval = self.minimax(new_state, depth - 1, alpha, beta, False)
                max_eval = max(max_eval, eval)
                alpha = max(alpha, eval)
                if beta <= alpha:
                    break
            return max_eval
        else:
            min_eval = float("inf")
            for move in moves:
                new_state = self.simulate_move(state, move)
                eval = self.minimax(new_state, depth - 1, alpha, beta, True)
                min_eval = min(min_eval, eval)
                beta = min(beta, eval)
                if beta <= alpha:
                    break
            return min_eval
        
    def simulate_move(self, state, move):
        new_state = copy.deepcopy(state)
        board = new_state.board

        piece = board[move.path[0][0]][move.path[0][1]]
        board[move.path[0][0]][move.path[0][1]] = 0

        for r, c in move.captures:
            board[r][c] = 0

        er, ec = move.path[-1]
        board[er][ec] = piece

        if piece == 1 and er == 0:
            board[er][ec] = 2
        elif piece == -1 and er == 9:
            board[er][ec] = -2

        new_state.turn *= -1

        return new_state
    
    def count_threatened_pieces(self, state, player):
        # Create a temporary state with the opponent as the current player
        temp_state = copy.deepcopy(state)
        temp_state.turn = -player
        
        # Get all possible capture moves for the opponent
        all_moves = RulesEngine.get_all_moves(temp_state)
        
        # Collect all positions that can be captured and classify threatened pieces
        threatened_positions = set()
        for move in all_moves:
            for pos in move.captures:
                threatened_positions.add(pos)
        
        threatened = 0
        threatened_kings = 0
        for r in range(10):
            for c in range(10):
                piece = state.board[r][c]
                if piece == 0 or ((piece > 0) != (player > 0)):
                    continue
                if (r, c) in threatened_positions:
                    threatened += 1
                    if abs(piece) == 2:
                        threatened_kings += 1
        
        return threatened, threatened_kings
  
    def count_pieces(self, state):
        red_pawns, blue_pawns = 0, 0
        red_kings, blue_kings = 0, 0
        for r in state.board:
            red_pawns += r.count(1)
            red_kings += r.count(2)
            blue_pawns += r.count(-1)
            blue_kings += r.count(-2)
        return ((red_pawns, red_kings), (blue_pawns, blue_kings))
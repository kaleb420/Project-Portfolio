from AI.baseAI import BaseAI
from game_logic import RulesEngine
'''
    SafetyAI is a simple AI that prioritizes moves that keep it's own pieces safe.
    It does this by evaluating the board state and assigning a score based on how many pieces are
    threatened by the opponent.
    If all of it's pieces are safe, then it will choose a move that maximizes its material advantage.
    If all moves are risky, it will choose the least risky move.
'''
class SafetyAI(BaseAI):
    def __init__(self):
        super().__init__()
        self.name = "SafetyAI"

    def choose_move(self, state):
        moves = RulesEngine.get_all_moves(state)
        safe_candidates = []
        risky_candidates = []
        maximizing = state.turn == 1

        for move in moves:
            new_state = self.simulate_move(state, move)
            score = self.minimax(new_state, self.depth - 1, float("-inf"), float("inf"), new_state.turn == 1)
            if self.has_immediate_capture(new_state):
                risky_candidates.append((score, move))
            else:
                safe_candidates.append((score, move))

        candidates = safe_candidates if safe_candidates else risky_candidates
        if not candidates:
            return None

        best_score = float("-inf") if maximizing else float("inf")
        best_move = None
        for score, move in candidates:
            if maximizing:
                if score > best_score:
                    best_score = score
                    best_move = move
            else:
                if score < best_score:
                    best_score = score
                    best_move = move
    
        return best_move

    def has_immediate_capture(self, state):
        return any(move.captures for move in RulesEngine.get_all_moves(state))

    def heuristic(self, state):
        (red_pieces, red_kings), (blue_pieces, blue_kings) = BaseAI.count_pieces(self, state)
        red_threatened, red_threatened_kings = BaseAI.count_threatened_pieces(self, state, 1)
        blue_threatened, blue_threatened_kings = BaseAI.count_threatened_pieces(self, state, -1)

        # Material score: red wants more men/kings, blue wants fewer.
        material_score = 150 * (red_pieces - blue_pieces) + 300 * (red_kings - blue_kings)

        # Safety is symmetric: threatened red pieces hurt the score, threatened blue pieces help it.
        threat_score = 0
        threat_score -= 12000 * red_threatened
        threat_score -= 700 * red_threatened_kings
        threat_score += 12000 * blue_threatened
        threat_score += 700 * blue_threatened_kings

        # A small incremental bonus/penalty for additional threatened pieces.
        threat_score -= 250 * red_threatened
        threat_score += 250 * blue_threatened

        

        return material_score + threat_score
    


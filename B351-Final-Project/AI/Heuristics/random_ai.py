from AI.baseAI import BaseAI
from game_logic import RulesEngine
import random

class RandomAI(BaseAI):
    def __init__(self):
        super().__init__()
        self.name = "RandomAI"
    def heuristic(self, state):
        return 0  # Random AI does not evaluate the board

    def choose_move(self, state):
        moves = RulesEngine.get_all_moves(state)
        if not moves:
            return None
        return random.choice(moves)
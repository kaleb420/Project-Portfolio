from AI.baseAI import BaseAI

""" This is a heuristic to favor states with more kings on the board.

This function counts the total number of pieces on the board, then returns the difference in kings for each player.
Red is considered the maximizing player, whereas blue is the minimizing player.
"""

class KingsAI(BaseAI):
    def __init__(self):
        super().__init__()
        self.name = "KingsAI"

    def heuristic(self, state):
        king_composition = BaseAI.count_pieces(self, state)
        return king_composition[0][1] - king_composition[1][1]
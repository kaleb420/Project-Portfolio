from AI.baseAI import BaseAI

""" This is a heuristic to favor states with more pawns on the board.

This function counts the total number of pieces on the board, then returns the difference in pawns for each player.
Red is considered the maximizing player, whereas blue is the minimizing player.
"""

class PawnsAI(BaseAI):
    def __init__(self):
        super().__init__()
        self.name = "PawnsAI"

    def heuristic(self, state):
        pawn_composition = BaseAI.count_pieces(self, state)
        return pawn_composition[0][0] - pawn_composition[1][0]
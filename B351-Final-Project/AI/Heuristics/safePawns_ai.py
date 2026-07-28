from AI.baseAI import BaseAI

""" This is a heuristic to favor states with more "safe" pawns on the board.

The function counts the total number of pieces on the board.
Then, the function gets the number of threatened pieces (at risk of capture in the next player's move).
Finally, the function separately calculates the difference between the number of pawns and the number of threatened pawns.
The scores are calculated separately for each player, with the corressponding score being returned, based on the current turn.
"""

class SafePawnsAI(BaseAI):
    def __init__(self):
        super().__init__()
        self.name = "SafePawnsAI"

    def heuristic(self, state):
        piece_composition = BaseAI.count_pieces(self, state)
        pawn_threat_red = BaseAI.count_threatened_pieces(self, state, 1)
        pawn_threat_blue = BaseAI.count_threatened_pieces(self, state, -1)
        red_score = piece_composition[0][0] - pawn_threat_red[0]
        blue_score = piece_composition[1][0] - pawn_threat_blue[0]
        return red_score if state.turn == 1 else blue_score
    
    
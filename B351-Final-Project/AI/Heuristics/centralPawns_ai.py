from AI.baseAI import BaseAI

""" This is a heuristic to favor states with pawns in the center of the board.

The CentralKingsAI heuristic function iterates over a pre-defined set of 10 tiles that represent the center of a 10x10 board.
For each tile, depending on the players turn, it is determined if the given player has a pawn on that tile.
The player's score is updated if a pawn is found in one of the center spaces, otherwise it is left alone. 
"""

class CentralPawnsAI(BaseAI):
    def __init__(self):
        super().__init__()
        self.name = "CentralPawnsAI"
        
    def heuristic(self, state):
        red_score, blue_score = 0, 0
        central_tiles = [(4,1),(4,3),(4,5),(4,7),(4,9),(5,0),(5,2),(5,4),(5,6),(5,8)]
        for tile in central_tiles:
            if state.board[tile[0]][tile[1]] == 1:
                red_score += 1
            elif state.board[tile[0]][tile[1]] == -1:
                blue_score += 1
        return red_score if state.turn == 1 else -1*(blue_score)
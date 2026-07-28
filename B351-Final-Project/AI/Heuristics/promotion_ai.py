from AI.baseAI import BaseAI

""" This is a heuristic that favors states with pawns closer to the promotion line.

This function gets the row of each piece for the current player.
The row of any given piece correlates to the minimum number of moves it needs to get to the promotion line.
The aggregated distance from the promotion line is then calculated and substracted from a base score.
The base score 150 was chose because it is the aggregate distance of all pawns from the promotion line at game start.
"""

class PromotionAI(BaseAI):
    def __init__(self):
        super().__init__()
        self.name = "PromotionAI"
        
    def heuristic(self, state):
        board = state.board 
        base_score = 150
        aggregate_distance = 0
        piece_type = 1 if state.turn == 1 else -1
        for r in range (len(board)):
            for c in range(len(board)):
                if board[r][c] == piece_type:
                    if state.turn == 1:
                        aggregate_distance += 9-r
                    else:
                        aggregate_distance += r
        return base_score - aggregate_distance if state.turn == 1 else -1*(base_score-aggregate_distance)
        
            
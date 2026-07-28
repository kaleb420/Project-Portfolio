from AI.baseAI import BaseAI
from game_logic import RulesEngine

class EdgeAI(BaseAI):
    """" 
    As other heuristics for this project, red wants a higher score while blue wants a lower score.

    Having pieces on the edge of the board is generally advantageous as the opponent will often struggle to capture them,
    letting the player who has the pieces on the edge control more space at a low risk.

    This heuristic will prioritize the player with more pieces on the edge.

    Classes:
    EdgeAI: This is the class that will compute the edge heuristic

    Functions:
    Heuristic: This is the function in the Edge class that will compute the heuristic, each piece on the edge 
    adds 1 to the score

    This sucks in an actual game because in practice solely priortizing edges will only send a singular piece down the center
    in hopes of reaching an edge before it is captured, and does not take into the account the risk of going to that position.
    Adding other heuristics to this score would likely improve this algorithm.
    """
    def __init__(self):
        super().__init__()
        self.name = "EdgeAI"
        
    def heuristic(self, state):
        score=0
        board=state.board
        for r in range(10):
            for c in range(10):
                piece=board[r][c]
                if (piece==0):
                    continue
                if (r==0 or r==10 or c==0 or c==10):
                    if (piece>0):
                        score+=1
                    else:
                        score-=1
        return score
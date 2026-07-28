from AI.baseAI import BaseAI
from game_logic import RulesEngine 


class MakeKingAI(BaseAI):
    """" 
    As other heuristics for this project, red wants a higher score while blue wants a lower score.

    Having a king is really good, this heuristic will attempt to see if a king can be made safely

    Classes:
    MakeKingAI: This is the class that will compute the heuristic

    Functions:
    Heuristic: This is the function in the Edge class that will compute the heuristic, if a king can be made safely it will
    run there no matter the cost of positioning or other pieces.

    TODO:
    
    """
    def __init__(self):
        super().__init__()
        self.name = "MakeKingAI"
        
    def heuristic(self, state):
        for r in range(10):
            for c in range(10):
                piece=state.board[r][c]
                if (piece==0 and ((state.turn == 1 and r == 8) or (state.turn == -1 and r == 1))): #You are one space away from a king
                    if any(move.captures for move in RulesEngine.get_all_moves(state.board[r][c])):
                        break
                    continue
                
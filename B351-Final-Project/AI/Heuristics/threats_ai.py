from AI.baseAI import BaseAI
from game_logic import RulesEngine

class ThreatsAI(BaseAI):

    """" 
    As other heuristics for this project, red wants a higher score while blue wants a lower score.

    Having a king is really good, this heuristic will attempt to see if a king can be made safely

    Classes:
    MakeKingAI: This is the class that will compute the heuristic

    Functions:
    Heuristic: This is the function in the Edge class that will compute the heuristic, if a king can be made safely it will
    run there no matter the cost of positioning or other pieces.

    TODO:
    Change the description
    """
    def __init__(self):
        super().__init__()
        self.name = "ThreatsAI"

    def heuristic(self, state):
        current_turn = state.turn

        temp_state = state
        temp_state.turn = 1
        moves = RulesEngine.get_all_moves(temp_state)
        red_threats = sum(len(m.captures) for m in moves)

        temp_state.turn = -1
        moves = RulesEngine.get_all_moves(temp_state)
        blue_threats = sum(len(m.captures) for m in moves)

        state.turn = current_turn  # restore

        return red_threats, blue_threats
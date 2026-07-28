from AI.baseAI import BaseAI
from game_logic import RulesEngine

class areaCoverage(BaseAI):

    """" 
    This heuristic will focus on covering the most area possible, this will mean pieces will likely be one space apart from 
    each other, so this is not great by itself but with other heuristics may show some potential

    Classes:
    areaCover: This is the class that will compute how much area is being covered

    Functions:
    Heuristic: This is the function that will compute the area

    TODO:
    """
    def __init__(self):
        super().__init__()
        self.name = "areaCoverage"

    def heuristic(self, state):
        set=RulesEngine.get_all_moves(self, state)
        if (state.turn==1):
            return len(set)
        else:
            return len(set)*-1
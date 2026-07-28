from AI.baseAI import BaseAI
from AI.Heuristics.simple_ai import SimpleAI
from AI.Heuristics.edge_ai import EdgeAI
from AI.Heuristics.line_ai import LineAI

class AggressiveAI(BaseAI):
    """" 
    This AI will focus on attacking the opponent, it will combine several different heuristics with different weights to
    achieve this goal.

    Classes:
    AggressiveAI: This is the class that will compute the score relevant to the heuristic's being used

    Functions:
    Heuristic: This is the function that will combine the heuristics to determine which one allows for optimal "aggressive play"

    TODO:
    """

    def __init__(self):
        super().__init__()
        self.name = "AggressiveAI"

    def heuristic(self, state):
        edgeWeight = 0.5
        lineWeight = 1.5
        simpleWeight = 1.25
        return (edgeWeight * EdgeAI().heuristic(state) + lineWeight * LineAI().heuristic(state) + simpleWeight * SimpleAI().heuristic(state))
    
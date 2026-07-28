from AI.baseAI import BaseAI
from AI.Heuristics.materialPosition_ai import MaterialPositionAI
from AI.Heuristics.mobility_ai import MobilityAI
from AI.Heuristics.threats_ai import ThreatsAI

"""
#God Tier Heuristic:

Considers the number of pieces, future capture chains, and potential threats on the board. 

It evaluates the strategic value of each piece and its position, giving higher scores to 
pieces that are more likely to lead to a win or prevent a loss.

Assigns higher scores to pieces that are in advantageous positions, such as those that can 
create multiple capture opportunities or block the opponent's key pieces.

Uses a more complex evaluation function that considers not only the current state of the 
board but also potential future states, allowing it to anticipate and counter the opponent's 
strategies more effectively.

Uses weights for different factors such as piece value, mobility, and control of the board to 
calculate a more nuanced score for each position.
"""
class GodAI(BaseAI):
    
    MAN_VALUE = 1
    KING_VALUE = 3.5

    CENTER_WEIGHT = 0.1
    ADVANCE_WEIGHT = 0.05
    MOBILITY_WEIGHT = 0.2
    THREAT_WEIGHT = 0.8

    def __init__(self):
        super().__init__()
        self.name = "GodAI"
        
    def heuristic(self, state):
        score = 0

        red_material = 0
        blue_material = 0

        red_positional = 0
        blue_positional = 0

        red_threats = 0
        blue_threats = 0

        red_mobility = 0
        blue_mobility = 0

        red_material, blue_material, red_positional, blue_positional = MaterialPositionAI.heuristic(self, state)
        red_mobility, blue_mobility = MobilityAI.heuristic(self, state)
        red_threats, blue_threats = ThreatsAI.heuristic(self, state)
        
        # ---------------- FINAL SCORE ----------------
        score += (red_material - blue_material)
        score += (red_positional - blue_positional)
        score += self.MOBILITY_WEIGHT * (red_mobility - blue_mobility)
        score += self.THREAT_WEIGHT * (red_threats - blue_threats)

        return score

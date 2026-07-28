from AI.baseAI import BaseAI

class MaterialPositionAI(BaseAI):

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
    Material is likely already counted somewhere in another heuristic, we could use that function instead of reimplementing it
    here
    """
    def __init__(self):
        super().__init__()
        self.name = "MaterialPositionAI"


    def heuristic(self, state):
        blue_positional = 0
        red_positional = 0
        blue_material = 0
        red_material = 0
        board = state.board
        for r in range(10):
            for c in range(10):
                piece = board[r][c]
                if piece == 0:
                    continue

                is_red = piece > 0
                is_king = abs(piece) == 2

                value = self.KING_VALUE if is_king else self.MAN_VALUE

                # center control (closer to center = better)
                center_dist = abs(4.5 - r) + abs(4.5 - c)
                center_score = (9 - center_dist) * self.CENTER_WEIGHT

                # advancement (men only)
                advance_score = 0
                if not is_king:
                    if is_red:
                        advance_score = (9 - r) * self.ADVANCE_WEIGHT
                    else:
                        advance_score = r * self.ADVANCE_WEIGHT

                total_piece_score = value + center_score + advance_score

                if is_red:
                    red_material += value
                    red_positional += center_score + advance_score
                else:
                    blue_material += value
                    blue_positional += center_score + advance_score
        return red_material, blue_material, red_positional, blue_positional
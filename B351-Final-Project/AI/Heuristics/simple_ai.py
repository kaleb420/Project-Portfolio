from AI.baseAI import BaseAI

class SimpleAI(BaseAI):
    def heuristic(self, state):
        score = 0
        for row in state.board:
            for piece in row:
                if piece == 1:
                    score += 1
                elif piece == -1:
                    score -= 1
                elif piece == 2:
                    score += 3
                elif piece == -2:
                    score -= 3

        return score
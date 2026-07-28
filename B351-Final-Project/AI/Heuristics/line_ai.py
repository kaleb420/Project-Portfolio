from AI.baseAI import BaseAI
from game_logic import RulesEngine

class LineAI(BaseAI):
    """" 
    As other heuristics for this project, red wants a higher score while blue wants a lower score.

    Having pieces lined up in a row is ideal for play, as it makes it more difficult for the opponent to capture pieces

    This heuristic will prioritize the player with more pieces in a line

    Classes:
    LineAI: This is the class that will compute the heuristic

    Functions:

    inBounds: This function determines if the position on the board is still in bounds. 

    lenOfLine: This functions determines how long a line is, this will count multiple time for every piece in the same line.

    evaluteAdjacency: Get every possible direction adjacent to the piece being searched.

    Heuristic: This is the function in the Edge class that will compute the heuristic, it adds or subtracts to the score based
    on the follow formula, the length of pieces in a line * +/-1

    This will inherently count multiple times for the same pieces, as if one is in a line then other pieces must be in the same 
    line, this will bias the heuristic but can be fixed later if desired.
    """

    def __init__(self):
        super().__init__()
        self.name = "LineAI"

    # determines if a space is within the bounds of the board
    def inBounds(self, r, c):
        if (r>=0 and r<10 and c>=0 and c<10):
            return True
        return False

    # determines how long a line is, if piece is by itself length is 0, else it is the length of the line
    # the while loop does the work in calculating the new piece on the board, then compares it to see if it is the same color
    # if it is then the pieces must be in a line
    def lenOfLine(self, state, r, c, dir):
        score=0
        color=state.board[r][c]
        new_piece=color
        new_r=r
        new_c=c
        while (color==new_piece):
            new_r+=dir[0]
            new_c+=dir[1]
            if (self.inBounds(new_r, new_c) is False):
                return score
            new_piece=state.board[new_r][new_c]
            score+=1
        return score

    # gather each square diaganol of a piece, then determine the length of the line, returns the sum of each posssible direction
    def evaluateAdjacency(self, state, r, c):
        score=0
        lst=[(-1, -1), (-1, 1), (1, -1), (1, 1)]
        for dir in lst:
            score+=self.lenOfLine(state, r, c, dir)
        return score

    def heuristic(self, state):
        score=0
        for r in range(10):
            for c in range(10):
                piece=state.board[r][c]
                if (piece==0):
                    continue
                adjacencyScore=self.evaluateAdjacency(state, r, c)
                if (piece>0):
                    score+=(adjacencyScore*1)
                else:
                    score+=(adjacencyScore*-1)
        return score


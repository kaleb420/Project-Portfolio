import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem9 {
    /**
     * receives a move position and determines if it is valid
     * @param board 2d array of legal positions
     * @param mx x axis move
     * @param my y axis move
     * @return true if it is a valid move, false otherwise
     */
    static boolean isValidMove(char[][] board, int mx, int my){
        if (board.length>mx && board[0].length>my && mx>=0 && my>=0)
            return true;
        return false;
    }

    /**
     * returns all valid (in bounds) neighbors within a x+/-1 and y+/-1 area
     * @param board space given
     * @param mx x axis given
     * @param my y axis given
     * @return if the surrounding space are valid
     */
    static List<int[]> getValidNeighbors(char[][] board, int mx, int my){
        List<int[]> ls= new ArrayList<>();
        int[] arr;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (isValidMove(board, mx+i, my+j) && !(mx+i==mx && my+j==my)) {
                    arr=new int[]{mx + i, my + j};
                    ls.add(arr);
                }
            }
        }
        return ls;
    }

    /**
     * determines if adjacent tiles are not mines
     * @param board board space
     * @param mx x axis given
     * @param my y axis given
     * @return an array list of adjacent spaces that are not mines
     */
    static List<int[]> getNonMineNeighbors(char[][] board, int mx, int my){
        List<int[]> ls= new ArrayList<>();
        List<int[]> temp= new ArrayList<>(getValidNeighbors(board, mx, my));
        for (int[] i : temp){
            if (board[i[0]][i[1]]=='-')
                ls.add(i);
        }
        return ls;
    }
    /**
     * determines if adjacent tiles are not mines
     * @param board board space
     * @param mx x axis given
     * @param my y axis given
     * @return the number of non-mine tiles adjacent to the given space
     */
    static List<int[]> getMineNeighbors(char[][] board, int mx, int my) {
        List<int[]> ls= new ArrayList<>();
        List<int[]> temp= new ArrayList<>(getValidNeighbors(board, mx, my));
        for (int[] i : temp){
            if (board[i[0]][i[1]]=='B')
                ls.add(i);
        }
        return ls;
    }

    /**
     * counts the number of adjacent mines to the given space
     * @param board board space
     * @param mx x axis given
     * @param my y axis given
     * @return number of adjacent mines
     */
    static int countAdjacentMines(char[][] board, int mx, int my){
        return getMineNeighbors(board,mx,my).size();
    }

    /**
     * extends the path until there is an adjacent mine
     * @param board board space given
     * @param mx x axis of input
     * @param my y axis of input
     */
    static void extPath(char[][] board, int mx, int my){
        if (!isValidMove(board,mx,my))
            return;
        if (board[mx][my]!='-')
            return;
        else{
            if (countAdjacentMines(board,mx,my)!=0)
                board[mx][my]= (char) ((' ' + countAdjacentMines(board,mx,my)+16));
            else{
                board[mx][my]='0';
                List<int[]> ls = new ArrayList<>(getValidNeighbors(board, mx, my));
                for (int[] i : ls){
                    extPath(board, i[0], i[1]);
                }
            }
        }
    }

    /**
     * creates the minesweeper board
     * @param N number of rows
     * @param M number of columns
     * @param B number of mines present on the board
     * @return board with mines randomly scattered
     */
    static char[][] makeBoard(int N, int M, int B){
        char[][] board = new char[N][M];
        int counter=0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = '-';
            }
        }
        while (B>=counter){
            for (int k = 0; k < N; k++) {
                for (int p = 0; p < M; p++) {
                    if ((int) (Math.random()*101)%2==1 && board[k][p]!='B'){
                        board[k][p]='B';
                        counter++;
                        if (B>=counter)
                            break;
                    }
                }
            }
        }
        return board;
    }

    /**
     * attempts to play the given move inputted by the player
     * @param board board space
     * @param mx x axis chosen
     * @param my y axis chosen
     * @return new board with the space updated based on the player's input
     */
    static char[][] play(char[][] board, int mx, int my){
        if (isValidMove(board, mx, my)) {
            if (board[mx][my] == 'B')
                return null;
            else
                extPath(board, mx, my);
            return board;
        }
        return board;
    }
}

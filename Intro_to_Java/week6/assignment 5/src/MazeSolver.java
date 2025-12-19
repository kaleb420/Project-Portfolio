import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MazeSolver {

    char[][] arr;
    int rows;
    int cols;
    int currentX;
    int currentY;

    /**
     * constructor to initialize the maze file into the class
     * @param fileName file name given
     */
    MazeSolver(String fileName) {
        currentX=0;
        currentY=0;
        boolean first=true;
        char[][] temp=null;
        int tempRows = 0;
        int tempColumns = 0;
        String line="";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line=br.readLine())!=null){
                String[] spliced=line.split(" ");
                if (first) {
                    rows=Integer.parseInt(spliced[0]);
                    cols=Integer.parseInt(spliced[1]);
                    temp = new char[rows][cols];
                    first=false;
                }
                else {
                    for (int i = 0; i < spliced.length; i++) {
                        tempColumns=i;
                        temp[tempColumns][tempRows]=spliced[i].charAt(0);
                    }
                    tempRows++;
                }
            }
            arr=temp;
        }
        catch (IOException ex) {
            throw new RuntimeException();
        }
    }
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
     * attempts to solve the maze, marks the path used with *, while walls are denoted with #
     * @return char array with solution, or if there is no solution return null
     */
    char[][] solve() {
        int counter=0;
        while (true){
            if (counter>100)
                break;
            if (currentY<cols && arr[currentX][currentY+1]!='#')
                currentY++;
            else if (currentX<rows && arr[currentX+1][currentY]!='#')
                currentX++;
            else
                currentY--;
            arr[currentX][currentY]='*';
        }
        if (arr[rows][cols]!='*')
            return null;
        return arr;
    }

    /**
     * outputs the solution of the maze to the given file
     * @param fileName filename given to store solution
     * @param soln solution to the maze, if one exists
     */
    void output(String fileName, char[][] soln){
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))){
            if (soln==null)
                pw.print("no solution");
            else {
                pw.print(soln.length + " " + soln[0].length);
                pw.println();
                for (int i = 0; i < soln.length; i++) {
                    for (int j = 0; j < soln[0].length; j++) {
                        pw.print(soln[i][j] + " ");
                    }
                    pw.println();
                }
            }
        }
        catch (IOException ex){
            throw new RuntimeException();
        }
    }
}

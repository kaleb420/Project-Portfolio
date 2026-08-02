package Setup;

import Helpers.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * The map of the board. It is stored as a 2d array and uses strings as a key. Strings with a length of 1
 * are resources, strings with a length of 2 are leaders, and strings with a length of 3 are monuments.
 * Also contains the function to print the current state of the map.
 */
public class Map {

    public int rowLength=11;
    public int columnLength=16;
    public String[][] board=new String[rowLength][columnLength];
    public HashMap<String, int[]> monuments=new HashMap<>();
    public int totalTreasures=10;
    public String templeWithTreasure="O";
    public String river="R";
    public String temple="T";
    public String market="M";
    public String farm="F";
    public String settlement="S";
    public String empty="-";
    public String catastrophe="C";
    public String conflict="J";
    public String lions="Lions";
    public String archers="Archers";
    public String pots="Pots";
    public String bulls="Bulls";

    /**
     * Fills in all tiles at the beginning of the game as - if unoccupied, O if temple with treasure, and
     * R for river
     */
    public Map(){
        for (String[] characters : board) {
            Arrays.fill(characters, empty);
        }
        board[0][4]=river;
        board[0][5]=river;
        board[0][6]=river;
        board[0][7]=river;
        board[0][8]=river;
        board[0][12]=river;
        board[1][4]=river;
        board[1][12]=river;
        board[2][3]=river;
        board[2][4]=river;
        board[2][12]=river;
        board[2][13]=river;
        board[3][0]=river;
        board[3][1]=river;
        board[3][2]=river;
        board[3][3]=river;
        board[3][13]=river;
        board[3][14]=river;
        board[3][15]=river;
        board[4][14]=river;
        board[4][15]=river;
        board[5][14]=river;
        board[6][0]=river;
        board[6][1]=river;
        board[6][2]=river;
        board[6][3]=river;
        board[6][12]=river;
        board[6][13]=river;
        board[6][14]=river;
        board[7][3]=river;
        board[7][4]=river;
        board[7][5]=river;
        board[7][6]=river;
        board[7][12]=river;
        board[8][6]=river;
        board[8][7]=river;
        board[8][8]=river;
        board[8][9]=river;
        board[8][10]=river;
        board[8][11]=river;
        board[8][12]=river;
        board[0][10]=templeWithTreasure;
        board[1][1]=templeWithTreasure;
        board[1][15]=templeWithTreasure;
        board[2][5]=templeWithTreasure;
        board[4][13]=templeWithTreasure;
        board[6][8]=templeWithTreasure;
        board[7][1]=templeWithTreasure;
        board[8][14]=templeWithTreasure;
        board[9][5]=templeWithTreasure;
        board[10][10]=templeWithTreasure;
        monuments.put("SM", new int[]{-1, -1});
        monuments.put("ST", new int[]{-1, -1});
        monuments.put("SF", new int[]{-1, -1});
        monuments.put("TF", new int[]{-1, -1});
        monuments.put("FM", new int[]{-1, -1});
        monuments.put("MT", new int[]{-1, -1});
    }

    /**
     * Prints the map with capital letters representing the columns, and numbers representing the rows
     */
    public void printMap(){
        char startChar='A';
        System.out.print("   "); // buffer to make it look nicer
        for (int i=0; i < board.length+1; i++) { // one greater than the length to have a buffer row to print numbers
            for (int j = 0; j < board[0].length+1; j++) { // same as above except to print letters
                char printNumber= (char) ('0' + i-1);
                char currChar=(char) (startChar+j-1);
                if (i==0 && j==0)
                    continue;
                else if (i == 0)
                    System.out.print(" " + currChar + " ");
                else if (j == 0)
                    System.out.print(" " + printNumber + " ");
                else {
                    String print=board[i-1][j-1];
                    if (print.length()==3 || print.length()==2)
                        System.out.print(" " + print.substring(0, 2)); // substring if a monument is being printed so it doesn't take up as much space in the print
                    else
                        System.out.print(" " + print + " ");
                }
            }
            System.out.println();
        }
    }
}

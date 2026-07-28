package Midgame;

import Helpers.Helper;
import Helpers.Search_Algorithms;
import Setup.Map;
import Setup.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

/**
 * This class keeps track of the amount of cubes each player has.
 */
public class Cubes {
    public int redCubes=0;
    public int blueCubes=0;
    public int greenCubes=0;
    public int blackCubes=0;
    public int wildCubes=0;
    HashMap<String, Player> players;
    Search_Algorithms searchAlgorithms;

    /**
     * Initialization to have the same variables as other classes
     */
    public Cubes(Map map, HashMap<String, Player> players){
        this.players=players;
        this.searchAlgorithms=new Search_Algorithms(map);
    }

    public char cubeGetLeader(Map map, char color, int[] location){
        ArrayList<int[]> kingdom=searchAlgorithms.BFSSearchKingdom(location);
        for (int[] space : kingdom){
            int row=space[0];
            int column=space[1];
            String tile=map.board[row][column];
            if (tile.length()==2 && tile.charAt(1)==color)
                return tile.charAt(0);
        }
        return 'z';
    }

    /**
     * Function designed to search the kingdom a tile was placed, and if it has a same color leader that
     * player gets a point, same with all other similar functions in this class
     * @param map current board state
     * @param location red tile was placed
     */
    public void getRedCube(Map map, int[] location){
        char ret=cubeGetLeader(map, 'T', location);
        if (ret==map.bulls.charAt(0))
            players.get(map.bulls).cubes.redCubes++;
        else if (ret==map.lions.charAt(0))
            players.get(map.lions).cubes.redCubes++;
        else if (ret==map.archers.charAt(0))
            players.get(map.archers).cubes.redCubes++;
        else if (ret==map.pots.charAt(0))
            players.get(map.pots).cubes.redCubes++;
    }

    /**
     * Function designed to search the kingdom a tile was placed, and if it has a same color leader that
     * player gets a point, same with all other similar functions in this class
     * @param map current board state
     * @param location red tile was placed
     */
    public void getBlueCube(Map map, int[] location){
        char ret=cubeGetLeader(map, 'F', location);
        if (ret==map.bulls.charAt(0))
            players.get(map.bulls).cubes.blueCubes++;
        else if (ret==map.lions.charAt(0))
            players.get(map.lions).cubes.blueCubes++;
        else if (ret==map.archers.charAt(0))
            players.get(map.archers).cubes.blueCubes++;
        else if (ret==map.pots.charAt(0))
            players.get(map.pots).cubes.blueCubes++;
    }

    /**
     * Function designed to search the kingdom a tile was placed, and if it has a same color leader that
     * player gets a point, same with all other similar functions in this class
     * @param map current board state
     * @param location red tile was placed
     */
    public void getGreenCube(Map map, int[] location){
        char ret=cubeGetLeader(map, 'M', location);
        if (ret==map.bulls.charAt(0))
            players.get(map.bulls).cubes.greenCubes++;
        else if (ret==map.lions.charAt(0))
            players.get(map.lions).cubes.greenCubes++;
        else if (ret==map.archers.charAt(0))
            players.get(map.archers).cubes.greenCubes++;
        else if (ret==map.pots.charAt(0))
            players.get(map.pots).cubes.greenCubes++;
    }

    /**
     * Function designed to search the kingdom a tile was placed, and if it has a same color leader that
     * player gets a point, same with all other similar functions in this class
     * @param map current board state
     * @param location red tile was placed
     */
    public void getBlackCube(Map map, int[] location){
        char ret=cubeGetLeader(map, 'S', location);
        if (ret==map.bulls.charAt(0))
            players.get(map.bulls).cubes.blackCubes++;
        else if (ret==map.lions.charAt(0))
            players.get(map.lions).cubes.blackCubes++;
        else if (ret==map.archers.charAt(0))
            players.get(map.archers).cubes.blackCubes++;
        else if (ret==map.pots.charAt(0))
            players.get(map.pots).cubes.blackCubes++;
    }
}

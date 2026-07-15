package Midgame;

import Helpers.Helper;
import Helpers.Search_Algorithms;
import Setup.Map;
import Setup.Player;

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
    HashMap<String, Player> players;
    Search_Algorithms searchAlgorithms;

    /**
     * Initialization to have the same variables as other classes
     */
    public Cubes(Map map, HashMap<String, Player> players){
        this.players=players;
        this.searchAlgorithms=new Search_Algorithms(map);
    }

    /**
     * Function designed to search the kingdom a tile was placed, and if it has a same color leader that
     * player gets a point, same with all other similar functions in this class
     * @param map current board state
     * @param location red tile was placed
     */
    public void getRedCube(Map map, int[] location){
        Optional<Character> ret=searchAlgorithms.searchKingdomLeaders(map, location, 'R');
        if (ret.isEmpty())
            return;
        else if (ret.get()==map.bulls.charAt(0))
            players.get(map.bulls).cubes.redCubes++;
        else if (ret.get()==map.lions.charAt(0))
            players.get(map.lions).cubes.redCubes++;
        else if (ret.get()==map.archers.charAt(0))
            players.get(map.archers).cubes.redCubes++;
        else if (ret.get()==map.pots.charAt(0))
            players.get(map.pots).cubes.redCubes++;
    }

    /**
     *
     * @param map current board state
     * @param location blue tile was placed
     */
    public void getBlueCube(Map map, int[] location){
        Optional<Character> ret=searchAlgorithms.searchKingdomLeaders(map, location, 'B');
        if (ret.isEmpty())
            return;
        else if (ret.get()==map.bulls.charAt(0))
            players.get(map.bulls).cubes.blueCubes++;
        else if (ret.get()==map.lions.charAt(0))
            players.get(map.lions).cubes.blueCubes++;
        else if (ret.get()==map.archers.charAt(0))
            players.get(map.archers).cubes.blueCubes++;
        else if (ret.get()==map.pots.charAt(0))
            players.get(map.pots).cubes.blueCubes++;
    }

    /**
     *
     * @param map current board state
     * @param location green tile was placed
     */
    public void getGreenCube(Map map, int[] location){
        Optional<Character> ret=searchAlgorithms.searchKingdomLeaders(map, location, 'G');
        if (ret.isEmpty())
            return;
        else if (ret.get()==map.bulls.charAt(0))
            players.get(map.bulls).cubes.greenCubes++;
        else if (ret.get()==map.lions.charAt(0))
            players.get(map.lions).cubes.greenCubes++;
        else if (ret.get()==map.archers.charAt(0))
            players.get(map.archers).cubes.greenCubes++;
        else if (ret.get()==map.pots.charAt(0))
            players.get(map.pots).cubes.greenCubes++;
    }

    /**
     *
     * @param map current board state
     * @param location black tile was placed
     */
    public void getBlackCube(Map map, int[] location){
        Optional<Character> ret=searchAlgorithms.searchKingdomLeaders(map, location, 'K');
        if (ret.isEmpty())
            return;
        else if (ret.get()==map.bulls.charAt(0))
            players.get(map.bulls).cubes.blackCubes++;
        else if (ret.get()==map.lions.charAt(0))
            players.get(map.lions).cubes.blackCubes++;
        else if (ret.get()==map.archers.charAt(0))
            players.get(map.archers).cubes.blackCubes++;
        else if (ret.get()==map.pots.charAt(0))
            players.get(map.pots).cubes.blackCubes++;
    }
}

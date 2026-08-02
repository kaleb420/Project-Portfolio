package Midgame;

import Helpers.Helper;
import Setup.Map;
import Setup.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * An internal conflict can only start when a leader has been placed, therefore, it only needs to be checked
 * after a leader has been placed. Call internalConflictCheck and if getSameColorLeaders returns true, a
 * conflict has been started and internal conflict manager is called. After an internal conflict has ended,
 * the loser removes the associated leader from the board, and the winner gains one red cube.
 */
public class InternalConflicts extends Conflicts{

    public InternalConflicts(Map map, Adjust_Map adjustMap, HashMap<String, Player> players, Helper helper) {
        super(map, adjustMap, players, helper);
    }

    /**
     * The winner gets one red cube for winning an internal conflict
     * @param winner who won the conflict
     * @param loser irrelevant
     * @param tile irrelevant
     * @param points irrelevant
     */
    @Override
    public void endConflict(Player winner, Player loser, char tile, int points){
        winner.cubes.redCubes++;
    }

    /**
     * Calculates the strength, strength is equal to number of adjacent temples to the leader
     * for internal conflicts
     * @param location of the leader
     * @param color irrelevant
     * @return strength a leader has
     */
    @Override
    public int getStrength(Player player, int[] location, char color){
        int temples=0;
        ArrayList<int[]> adjacentSpaces=searchAlgorithms.getAdjacent(location);
        for (int[] adjacentSpace : adjacentSpaces){
            String tile=helper.getTile(adjacentSpace);
            if (tile.equals(map.temple) || tile.equals(map.templeWithTreasure))
                temples++;
        }
        return temples;
    }

    /**
     * No tiles are removed from the end of an internal conflict, so doesn't do anything.
     * @param player who loss the conflict
     */
    @Override
    public void removeTilesFromKingdom(Player player){
    }

    /**
     * For internal conflicts only the conflict manager needs to be called
     */
    public void internalConflictManager(){
        System.out.println("An internal conflict has started.");
        conflictManager();
    }

    /**
     * Determines if there is an internal conflict, calls the startConflict function,
     * if that is true call internalConflictManager, regardless if a conflict started call clearConflict
     * @param location of the leader just placed
     */
    public void internalConflictCheck(int[] location){
        if (getSameColorLeaders(location))
            internalConflictManager();
        clearConflict();
    }
}

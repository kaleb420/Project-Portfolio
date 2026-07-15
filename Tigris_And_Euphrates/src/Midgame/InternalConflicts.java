package Midgame;

import Helpers.Helper;
import Setup.Map;
import Setup.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class InternalConflicts extends Conflicts{

    public InternalConflicts(Map map, HashMap<String, Player> players, Helper helper) {
        super(map, players, helper);
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
        ArrayList<int[]> adjacentSpaces=searchAlgorithms.adjacency(location);
        for (int[] adjacentSpace : adjacentSpaces){
            int row=adjacentSpace[0];
            int column=adjacentSpace[1];
            if (map.board[row][column].equals(map.temple) || map.board[row][column].equals(map.templeWithTreasure))
                temples++;
        }
        searchAlgorithms.clearVisited();
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
     * This function calls the conflictInformation function from the superclass, then determines if the attacker
     * or defender won the conflict, then call end conflict with the appropriate variables
     */
    public void internalConflictManager(){
        conflictInformation();
        checkWinner();
    }

    /**
     * Determines if there is an internal conflict, calls the startConflict function,
     * if that is true call internalConflictManager, regardless if a conflict started call clearConflict
     * @param location of the leader just placed
     */
    public void internalConflictCheck(int[] location){
        if (startConflict(location))
            internalConflictManager();
    }
}

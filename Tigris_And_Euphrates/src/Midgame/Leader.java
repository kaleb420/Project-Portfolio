package Midgame;

import Helpers.Helper;
import Helpers.Search_Algorithms;
import Setup.Map;
import Setup.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Leader {

    Map map;
    Helper helper;
    Adjust_Map adjustMap;
    InternalConflicts internalConflicts;
    Search_Algorithms searchAlgorithms;

    public Leader(Map map, Helper helper, HashMap<String, Player> players){
        this.map=map;
        this.helper=helper;
        adjustMap=new Adjust_Map(map, players, helper);
        internalConflicts=new InternalConflicts(map, players, helper);
        this.searchAlgorithms=new Search_Algorithms(map);
    }

    /**
     * Ensures that the leader tile being placed is adjacent to a temple, and not being placed
     * on a river or catastrophe
     * @return true if it is a valid placement, otherwise false
     */
    public boolean leaderPlacedNextToTemple(int[] location){
        ArrayList<int[]> adjacentSpaces=searchAlgorithms.adjacency(location);
        for (int[] adjacentSpace : adjacentSpaces) {
            int row=adjacentSpace[0];
            int column=adjacentSpace[1];
            if (map.board[row][column].equals(map.temple) || map.board[row][column].equals(map.templeWithTreasure))
                return true;
        }
        searchAlgorithms.clearVisited();
        System.out.println("That leader is not placed next to a temple.");
        return false;
    }

    /**
     * Searches the region to determine if it has a leader, if it does then that region is a kingdom
     * @param location a square adjacent to the leader being placed
     * @return true if that region is a kingdom, false otherwise
     */
    public boolean kingdomDetector(int[] location){
        int row=location[0];
        int column=location[1];
        if (map.board[row][column].length()==2)
            return true;
        ArrayList<int[]> adjacentSpaces=searchAlgorithms.adjacency(location);
        for (int[] adjacentSpace : adjacentSpaces){
            System.out.println(Arrays.toString(adjacentSpace));
            return kingdomDetector(adjacentSpace);
        }
        return false;
    }

    /**
     * This is the function to determine if a leader is uniting two kingdoms, if there is only one viable
     * adjacent square then there is no risk of the leader uniting two kingdoms, if there is two viable
     * adjacent squares then a helper function must be called to determine if there is a leader in that
     * region
     * @param location the leader was placed
     * @return true if it does unite two kingdoms, false otherwise
     */
    public boolean uniteTwoKingdomsStart(int[] location){
        ArrayList<int[]> adjacentSpaces=searchAlgorithms.adjacency(location);
        if (adjacentSpaces.size()==1 || adjacentSpaces.isEmpty())
            return false;
        boolean isKingdom=true;
        for (int[] adjacentSpace : adjacentSpaces){
            isKingdom=isKingdom && kingdomDetector(adjacentSpace);
        }
        searchAlgorithms.clearVisited();
        return isKingdom;
    }

    /**
     * Checks to make sure that the input is valid, the leader is placed next to a temple, and doesn't
     * unite two kingdoms
     * @param input color the player chose
     * @return true if valid based off above conditions, false otherwise
     */
    public boolean leaderErrorCheck(int[] location, char input){
        return switch (input) {
            case 'T', 'M', 'F', 'S' -> {
                if (!helper.isEmpty(location[0], location[1]) || !leaderPlacedNextToTemple(location)) {
                    System.out.println("That tile is not an empty space.");
                    yield false;
                }
                else if (uniteTwoKingdomsStart(location)) {
                    System.out.println("That location would cause the leader to unite two kingdoms.");
                    yield false;
                }
                yield true;
            }
            default -> {
                System.out.println("That is not a valid color.");
                yield false;
            }
        };
    }

    /**
     * Function responsible for choosing the leader color, and location, calls appropriate error check
     * functions
     * @param player placing a leader
     */
    public int[] leader(Player player){
        System.out.println("What color leader would you like to choose. (Temple/Market/Farm/Settlement)");
        char color=helper.scan.nextLine().charAt(0);
        int[] location=helper.chooseLocation();
        if (!leaderErrorCheck(location, color)) {
            return leader(player);
        }
        adjustMap.placeLeader(player, color, location);
        return location;
    }
}
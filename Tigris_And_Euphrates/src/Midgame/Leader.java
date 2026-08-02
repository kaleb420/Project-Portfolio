package Midgame;

import Helpers.Helper;
import Helpers.Search_Algorithms;
import Setup.Map;
import Setup.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class handles placing a leader on a location. Leaders must be placed next to a temple (with or without
 * treasure), cannot unite two kingdoms, and cannot be placed on a river. The injection point is the leader
 * function, and all the others are the associated checks needed to be performed to ensure the placement
 * follows the rules above.
 */
public class Leader {

    Map map;
    Helper helper;
    Adjust_Map adjustMap;
    Search_Algorithms searchAlgorithms;

    public Leader(Map map, Helper helper, HashMap<String, Player> players){
        this.map=map;
        this.helper=helper;
        adjustMap=new Adjust_Map(map, players, helper);
        this.searchAlgorithms=new Search_Algorithms(map);
    }

    /**
     * Ensures that the leader tile being placed is adjacent to a temple, and not being placed
     * on a river or catastrophe
     * @return true if it is a valid placement, otherwise false
     */
    public boolean leaderPlacedNextToTemple(int[] location){
        ArrayList<int[]> adjacentSpaces=searchAlgorithms.getAdjacent(location);
        for (int[] adjacentSpace : adjacentSpaces) {
            String tile=helper.getTile(adjacentSpace);
            if (tile.equals(map.temple) || tile.equals(map.templeWithTreasure))
                return true;
        }
        System.out.println("That leader is not placed next to a temple.");
        return false;
    }

    /**
     * Searches the region to determine if it has a leader, if it does then that region is a kingdom
     * @param kingdom the kingdom as if the leader were placed
     * @return true if that region is a kingdom, false otherwise
     */
    public boolean kingdomDetector(ArrayList<int[]> kingdom){
        for (int[] space : kingdom){
            String tile=helper.getTile(space);
            if (helper.isLeader(tile))
                return true;
        }
        return false;
    }

    /**
     * This is the function to determine if a leader is uniting two kingdoms, if there is only one viable
     * adjacent square then there is no risk of the leader uniting two kingdoms, if there is two viable
     * adjacent squares then a helper function must be called to determine if there is a leader in that
     * region
     * @param adjacentSpaces to the leader
     * @return true if it does unite two kingdoms, false otherwise
     */
    public boolean uniteTwoKingdomsStart(ArrayList<int[]> adjacentSpaces){
        if (adjacentSpaces.size()==1 || adjacentSpaces.isEmpty())
            return false;
        boolean isKingdom=true;
        for (int[] adjacentSpace : adjacentSpaces){
            isKingdom=isKingdom && kingdomDetector(searchAlgorithms.BFSSearchKingdom(adjacentSpace));
        }
        return isKingdom;
    }

    /**
     * Helper function to compare if a tile from a kingdom has the same tile as the coordinate
     * @param kingdom being analyzed
     * @param coordinate being compared to
     * @return true if it does contain the tile, false otherwise
     */
    public boolean containsCoordinate(ArrayList<int[]> kingdom, int[] coordinate){
        for (int[] space : kingdom){
            if (Arrays.equals(space, coordinate))
                return true;
        }
        return false;
    }

    /**
     * Function that determines if a leader has two adjacent spaces to it, but those spaces are a part of the
     * same kingdom, therefore, it is not uniting two kingdoms
     * @param adjacentSpaces to the leader
     * @return true if they are the same kingdoms, false otherwise
     */
    public boolean isSameKingdom(ArrayList<int[]> adjacentSpaces){
        if (adjacentSpaces.size()<2) {
            return false;
        }
        ArrayList<int[]> kingdom=searchAlgorithms.BFSSearchKingdom(adjacentSpaces.getFirst());
        boolean different=true;
        for (int i = 1; i < adjacentSpaces.size(); i++) {
            different=different && containsCoordinate(kingdom, adjacentSpaces.get(i));
        }
        return different;
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
                ArrayList<int[]> adjacentSpaces=searchAlgorithms.getAdjacent(location);
                if (!helper.isEmpty(location[0], location[1])) {
                    System.out.println("That tile is not an empty space.");
                    yield false;
                }
                else if (!leaderPlacedNextToTemple(location))
                    yield false;
                else if (uniteTwoKingdomsStart(adjacentSpaces) && !isSameKingdom(adjacentSpaces)) {
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
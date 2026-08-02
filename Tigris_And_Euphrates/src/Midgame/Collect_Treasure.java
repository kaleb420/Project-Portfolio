package Midgame;

import Helpers.Helper;
import Helpers.Search_Algorithms;
import Setup.Map;
import Setup.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * If two temples with a treasure are a part of the same kingdom, and the player who's turn just ended has a
 * market leader, then they can collect one of the treasures.
 */
public class Collect_Treasure {

    HashMap<String, Player> players;
    Map map;
    Search_Algorithms searchAlgorithms;
    Helper helper;
    ArrayList<int[]> treasureLocations=new ArrayList<>(); // locations of treasure within the kingdom

    public Collect_Treasure(HashMap<String, Player> players, Map map, Helper helper){
        this.players=players;
        this.map=map;
        searchAlgorithms=new Search_Algorithms(map);
        this.helper=helper;
    }

    /**
     * Determines if the player who placed a tile has a market leader in that kingdom
     * @param player who placed the tile
     * @return true if that player's market leader in the kingdom, false otherwise
     */
    public boolean containsMarketLeader(Player player, ArrayList<int[]> kingdom){
        int greenRow=player.greenLocation[0];
        int greenColumn=player.greenLocation[1];
        for (int[] space : kingdom){
            int row=space[0];
            int column=space[1];
            if (greenRow==row && greenColumn==column)
                return true;
        }
        return false;
    }

    /**
     * Determines if there is at least two temples with treasures in the new kingdom
     * @param kingdom being analyzed
     * @return true if there is at least two temples with treasures, false otherwise
     */
    public boolean containsMultipleTemplesWithTreasure(ArrayList<int[]> kingdom){
        for (int[] space : kingdom){
            String tile=helper.getTile(space);
            if (tile.equals(map.templeWithTreasure))
                treasureLocations.add(space);
        }
        return treasureLocations.size()>=2;
    }

    /**
     * Sets the treasure location to just a regular temple. Prioritize taking treasures on the border of the
     * map, if there is none that fit that criteria just pick a random one.
     */
    public void removeTreasure(Player player){
        System.out.println(treasureLocations.size());
        while (treasureLocations.size()>1){
            int[] toRemove=new int[2];
            for (int[] space : treasureLocations){
                int row=space[0];
                int column=space[1];
                if (row==map.rowLength-1 || column==map.columnLength-1) {
                    toRemove=space;
                    treasureLocations.remove(space);
                }
            }
            if (toRemove[0]==0)
                toRemove=treasureLocations.removeFirst();
            int row=toRemove[0];
            int column=toRemove[1];
            map.board[row][column]=map.temple;
            collectTreasure(player);
        }
    }

    /**
     * Adds a wildCube to the given player's cube
     * @param player who placed the tile and has a market leader
     */
    public void collectTreasure(Player player){
        player.cubes.wildCubes++;
    }

    /**
     * If there is a market leader in that kingdom and there are at least two temples with treasures, a
     * treasure may be collected, and collectTreasure may be called
     * @param player who placed the tile
     * @param location the tile was placed
     */
    public void collectTreasureCheck(Player player, int[] location){
        ArrayList<int[]> kingdom=searchAlgorithms.BFSSearchKingdom(location);
        if (containsMarketLeader(player, kingdom) && containsMultipleTemplesWithTreasure(kingdom)) {
            removeTreasure(player);
        }
    }
}

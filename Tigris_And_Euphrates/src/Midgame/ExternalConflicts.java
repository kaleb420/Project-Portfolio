package Midgame;

import Helpers.Helper;
import Setup.Map;
import Setup.Player;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This acts as an injection point to the conflicts class, an external conflict can only be started by a
 * tile placement, therefore, it only needs to be checked after a tile has been placed. externalConflictCheck
 * is this injection point, if that is true a conflict has started and externalConflictManger is called. For
 * external conflicts, strength is calculated by how many same color tiles are in the kingdom (excluding
 * monuments), whoever has more strength wins. The losers original strength (same color tiles in their
 * kingdom) gets added to the winners points in the form of the that amount of cubes, of the color of the
 * conflict. Then, the loser removes his leader from the board, and removes all same color tiles. Then normal
 * play resumes.
 *
 */
public class ExternalConflicts extends Conflicts{

    public HashMap<Player, ArrayList<int[]>> tilesInvolved=new HashMap<>();

    public ExternalConflicts(Map map, Adjust_Map adjustMap, HashMap<String, Player> players, Helper helper) {
        super(map, adjustMap, players, helper);
    }

    /**
     * This is the end of the conflict, the winner gets x amount of points to the associated color cube,
     * the tiles are removed from the kingdom, causing all locations on the map needing to be checked again,
     * and become empty
     * @param winner player who won the conflict
     * @param loser player who lost the conflict
     * @param tile the color of the leaders fighting
     * @param points how many points the winner will get
     */
    @Override
    public void endConflict(Player winner, Player loser, char tile, int points){
        if (tile=='T')
            winner.cubes.redCubes+=points+1; // +1 for removing the leader
        else if (tile=='M')
            winner.cubes.greenCubes+=points+1;
        else if (tile=='F')
            winner.cubes.blueCubes+=points+1;
        else if (tile=='S')
            winner.cubes.blackCubes+=points+1;
        removeTilesFromKingdom(loser);
        clearConflict();
    }

    /**
     * Removes all the associated colors from that kingdom after losing a conflict, if the color being
     * removed is a temple, remove all leaders from the associated kingdom, unless that leader is
     * next to a temple with a treasure on it denoted by templeWithTreasure
     * @param loser player who lost and who will lose all the tiles
     */
    @Override
    public void removeTilesFromKingdom(Player loser){
        for (int i=0; i<tilesInvolved.get(loser).size(); i++){
            int row=tilesInvolved.get(loser).get(i)[0];
            int column=tilesInvolved.get(loser).get(i)[1];
            if (map.board[row][column].equals(map.temple)){
                helper.removeLeaderCheck(new int[]{row, column});
            }
            if (!map.board[row][column].equals(map.templeWithTreasure))
                map.board[row][column]=map.empty;
        }

    }

    /**
     * Store the original tile placed, sets the tile location placed to a conflict tile, meaning it will
     * not count in the upcoming conflict. Set the tilesInvolved variable so it is initiated. Then call
     * the conflictManager() function to determine who won the conflict. Set the tile back to the original
     * one.
     * @param location the tile that started the conflict was placed
     */
    public void externalConflictManager(int[] location){
        System.out.println("An external conflict has started.");
        int row=location[0];
        int column=location[1];
        String originalTile=helper.getTile(location);
        map.board[row][column]=map.conflict;
        for (String leader : sameColorLeadersInSameKingdom.keySet()){
            Player player=players.get(helper.translateCharToLeader(leader.charAt(0)));
            tilesInvolved.put(player, new ArrayList<>());
        }
        conflictManager();
        map.board[row][column]=String.valueOf(originalTile.charAt(0));
    }

    /**
     * Determines if an external conflict has started, an external conflict has started when a tile unites
     * two kingdoms with leaders of the same color. Regardless if there was a conflict or not, clear all
     * global variables aside from the arrays.
     * @param location of the tile placed
     * @return true if a conflict started, false if one didn't start
     */
    public boolean externalConflictCheck(int[] location){
        getLeadersInKingdom(location);
        if (getSameColorLeaders(location))
            externalConflictManager(location);
        boolean conflictStarted=sameColorLeadersInSameKingdom.size()>=2;
        clearConflict();
        tilesInvolved.clear();
        return conflictStarted;
    }

    /**
     * Counts how many tiles are in a kingdom of the specific leader color, red leader counts red tiles,
     * blue leader counts blue tiles, etc.
     * @param player on that side of the kingdom
     * @param location the leader is
     * @param color being included in the conflict
     * @return how many corresponding color tiles they have
     */
    @Override
    public int getStrength(Player player, int[] location, char color){
        ArrayList<int[]> kingdom=searchAlgorithms.BFSSearchKingdom(location);
        for (int[] space : kingdom) {
            String tile=helper.getTile(space);
            if (tile.charAt(0) == color || (tile.equals(map.templeWithTreasure) && color == map.temple.charAt(0))) // temple with treasures count for temple conflicts
                tilesInvolved.get(player).add(space);
        }
        return tilesInvolved.get(player).size();
    }
}
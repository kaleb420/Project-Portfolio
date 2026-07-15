package Midgame;

import Helpers.Helper;
import Setup.Map;
import Setup.Player;
import java.util.ArrayList;
import java.util.HashMap;

public class ExternalConflicts extends Conflicts{

    public HashMap<Player, ArrayList<int[]>> tilesInvolved=new HashMap<>();

    public ExternalConflicts(Map map, HashMap<String, Player> players, Helper helper) {
        super(map, players, helper);
    }

    /**
     * In the case temples were removed, every leader in the kingdom needs to be checked to ensure it's
     * still adjacent to a temple
     * @param location of the tile being removed
     */
    public void removeLeaderCheck(int[] location){
        ArrayList<int[]> adjacentSpaces=searchAlgorithms.adjacency(location);
        boolean adjacentToTempleWithTreasure=false;
        for (int[] adjacentSpace : adjacentSpaces){
            int row=adjacentSpace[0];
            int column=adjacentSpace[1];
            if (map.board[row][column].length()==2) { // tile with leader on it
                for (int[] adj : searchAlgorithms.adjacency(adjacentSpace)){ // check the adjacent tiles to check if there is a temple with treasure, if there is the leader stays
                    row=adj[0];
                    column=adj[1];
                    if (map.board[row][column].equals(map.templeWithTreasure)) {
                        adjacentToTempleWithTreasure=true;
                    }
                }
                if (!adjacentToTempleWithTreasure) { // if it isn't next to a temple with treasure the leader gets removed
                    map.board[row][column] = map.empty; // set it to empty but doesn't update leader information, may cause a bug later but realistically shouldn't
                    break;
                }
            }
        }
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
                removeLeaderCheck(new int[]{row, column});
            }
            if (!map.board[row][column].equals(map.templeWithTreasure))
                map.board[row][column]=map.empty;
        }
    }

    /**
     * Sets the tile location placed to a conflict tile, meaning it will not count in the upcoming conflict
     * then call the checkWinner() function
     * @param location the tile that started the conflict was placed
     */
    public void externalConflictManager(int[] location){
        int row=location[0];
        int column=location[1];
        map.board[row][column]=map.conflict;
        for (String leader : sameColorLeadersInSameKingdom.keySet()){
            Player player=players.get(helper.translateCharToLeader(leader.charAt(0)));
            tilesInvolved.put(player, new ArrayList<>());
        }
        checkWinner();
    }

    /**
     * Determines if an external conflict has started, an external conflict has started when a tile unites
     * two kingdoms with leaders of the same color, regardless if there was a conflict or not, clear all
     * global variables aside from the arrays
     * @param location of the tile placed
     * @return true if a conflict started, false if one didn't start
     */
    public boolean externalConflictCheck(int[] location){
        int row=location[0];
        int column=location[1];
        String tile=map.board[row][column];
        getLeadersInKingdom(location);
        if (startConflict(location))
            externalConflictManager(location);
        tilesInvolved.clear();
        map.board[row][column]=String.valueOf(tile.charAt(0));
        return leadersInKingdom.size()>=2;
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
        int row=location[0];
        int column=location[1];
        if (map.board[row][column].charAt(0)==color || (map.board[row][column].equals(map.templeWithTreasure) && color==map.temple.charAt(0))) // temple with treasures count for temple conflicts
            tilesInvolved.get(player).add(location);
        ArrayList<int[]> adjacentSpaces=searchAlgorithms.adjacency(location);
        for (int[] adjacentSpace : adjacentSpaces){
            getStrength(player, adjacentSpace, color);
        }
        return tilesInvolved.get(player).size();
    }
}
package Midgame;

import Helpers.Helper;
import Helpers.Search_Algorithms;
import Setup.Map;
import Setup.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Monument {

    Map map;
    Helper helper;
    HashMap<String, Player> players;
    Search_Algorithms searchAlgorithms;

    public Monument(Map map, HashMap<String, Player> players, Helper helper){
        this.map=map;
        this.players=players;
        this.helper=helper;
        this.searchAlgorithms=new Search_Algorithms(map);
    }

    public void incrementCubes(String faction, char color){
        Player player=players.get(faction);
        if (color==map.temple.charAt(0))
            player.cubes.redCubes++;
        else if (color==map.market.charAt(0))
            player.cubes.greenCubes++;
        else if (color==map.farm.charAt(0))
            player.cubes.blueCubes++;
        else if (color==map.settlement.charAt(0))
            player.cubes.blackCubes++;
        else
            System.out.println("Error: Function called when no color was valid.");
    }

    /**
     * Searches the kingdom for leaders, if it encounters a leader, then get the faction string of that leader,
     * then determine if the color of the leader matches the colors of the monument, if so, call the increment
     * cubes function with the faction string and appropriate color as parameters
     * @param location of the space being analyzed
     * @param colors of the monument
     */
    public void searchKingdomLeaders(int[] location, String colors){
        ArrayList<int[]> kingdom=searchAlgorithms.BFSSearchKingdom(location);
        for (int[] space : kingdom) {
            String tile=helper.getTile(space);
            if (tile.length() == 2) {
                char factionCharacter=tile.charAt(0);
                char factionColor=tile.charAt(1);
                String faction = helper.translateCharToLeader(factionCharacter);
                if (colors.charAt(0) == factionColor)
                    incrementCubes(faction, colors.charAt(0));
                else if (colors.charAt(1) == factionColor)
                    incrementCubes(faction, colors.charAt(1));
            }
        }
    }

    /**
     * Called at the end of each player's turn, analyzes all the monuments on the board and examines if there
     * is a same color leader in the same kingdom as the monument
     */
    public void endOfTurn(){
        for (String monument : map.monuments.keySet()){
            if (map.monuments.get(monument)[0]!=-1) {
                searchKingdomLeaders(map.monuments.get(monument), monument);
            }
        }
    }

    /**
     * Gives the user a list of available monuments to choose from, and asks them to pick one. Ensures the
     * monument is valid (contains the color of the tiles being flipped) and not already placed. Then sets
     * the location on the map to be that monument.
     * @param square 2d array of tiles that form a monument
     */
    public void placeMonument(int[][] square){
        for (String monument : map.monuments.keySet()){
            if (map.monuments.get(monument)[0]==-1)
                System.out.println(monument);
        }
        System.out.println("Type in the appropriate name of the monument you would like to place.");
        String input=helper.scan.nextLine();
        if (!map.monuments.containsKey(input)){
            System.out.println("That is not a valid input.");
            placeMonument(square);
            return;
        }
        if (map.monuments.get(input)[0]!=-1){
            System.out.println("That monument is already placed. Choose a different one.");
            placeMonument(square);
            return;
        }
        int row=square[0][0];
        int column=square[0][1];
        char color=map.board[row][column].charAt(0);
        if (input.charAt(0)!=color && input.charAt(1)!=color){
            System.out.println("That is not a valid color monument, one of the colors in the monument must be the same as the tiles you are flipping.");
            placeMonument(square);
            return;
        }
        map.monuments.put(input, new int[]{row, column});
        for (int[] tile : square){
            row=tile[0];
            column=tile[1];
            map.board[row][column]=input + " "; // add space so the length of the tile is 3, and doesn't interfere with any conflict function
        }
    }

    /**
     * Helper function to determine if a tile given is the same as the tile on the new location
     * @param square new location being analyzed to determine if a monument is able to be placed
     * @param tile of the original piece placed
     * @return true if it is the same, false otherwise
     */
    public boolean sameTile(int[][] square, String tile){
        for (int[] location : square){
            String currentTile=helper.getTile(location);
            if (!searchAlgorithms.inBounds(location) || !currentTile.equals(tile))
                return false;
        }
        return true;
    }

    /**
     * Checks if the recently placed tile causes four tiles to form a square if so, placeMonument is called
     * @param center the tile was placed
     */
    public void monumentCheck(int[] center){
        int row=center[0];
        int column=center[1];
        String tile=helper.getTile(center);
        int[] topLeft=new int[]{row-1, column-1};
        int[] top=new int[]{row-1, column};
        int[] topRight=new int[]{row-1, column+1};
        int[] left=new int[]{row, column-1};
        int[] right=new int[]{row, column+1};
        int[] bottomLeft=new int[]{row+1, column-1};
        int[] bottom=new int[]{row+1, column};
        int[] bottomRight=new int[]{row+1, column+1};
        int[][] topLeftSquare=new int[4][2];
        topLeftSquare[0]=topLeft;
        topLeftSquare[1]=top;
        topLeftSquare[2]=left;
        topLeftSquare[3]=center;
        if (sameTile(topLeftSquare, tile)) {
            placeMonument(topLeftSquare);
            return;
        }
        int[][] topRightSquare=new int[4][2];
        topRightSquare[0]=top;
        topRightSquare[1]=topRight;
        topRightSquare[2]=right;
        topRightSquare[3]=center;
        if (sameTile(topRightSquare, tile)) {
            placeMonument(topRightSquare);
            return;
        }
        int[][] bottomLeftSquare=new int[4][2];
        bottomLeftSquare[0]=left;
        bottomLeftSquare[1]=center;
        bottomLeftSquare[2]=bottomLeft;
        bottomLeftSquare[3]=bottom;
        if (sameTile(bottomLeftSquare, tile)) {
            placeMonument(bottomLeftSquare);
            return;
        }
        int[][] bottomRightSquare=new int[4][2];
        bottomRightSquare[0]=center;
        bottomRightSquare[1]=right;
        bottomRightSquare[2]=bottom;
        bottomRightSquare[3]=bottomRight;
        if (sameTile(bottomRightSquare, tile)) {
            placeMonument(bottomRightSquare);
            return;
        }
    }
}

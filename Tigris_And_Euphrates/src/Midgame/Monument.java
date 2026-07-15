package Midgame;

import Helpers.Helper;
import Helpers.Search_Algorithms;
import Setup.Map;
import Setup.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Monument {

    Map map;
    Helper helper;
    HashMap<String, Player> players;
    List<String> checkedMonuments;
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

    public void searchKingdomLeaders(int[] location, String colors){
        int row=location[0];
        int column=location[1];
        if (map.board[row][column].length()==2) {
            char factionCharacter=map.board[row][column].charAt(0);
            char factionColor=map.board[row][column].charAt(1);
            String faction=helper.translateCharToLeader(factionCharacter);
            if (colors.charAt(0)==factionColor)
                incrementCubes(faction, colors.charAt(0));
            else if (colors.charAt(1)==factionColor)
                incrementCubes(faction, colors.charAt(1));
        }
        ArrayList<int[]> adjacentSpaces = searchAlgorithms.adjacency(location);
        for (int[] adjacentSpace : adjacentSpaces){
            searchKingdomLeaders(adjacentSpace, colors);
        }
        searchAlgorithms.clearVisited();
    }

    public void endOfTurn(){
        for (int i = 0; i < map.unavailableMonuments.size(); i++) {
            if (checkedMonuments.contains(map.unavailableMonuments.get(i)))
                break;
            for (int row = 0; row < map.board.length; row++) {
                for (int column = 0; column < map.board[0].length; column++) {
                    if (map.board[row][column].equals(map.unavailableMonuments.get(i))){
                        searchKingdomLeaders(new int[]{row, column}, map.unavailableMonuments.get(i));
                    }
                }
            }
        }
    }

    /**
     * Gives the user a list of available monuments to choose from, and asks them to pick one. Then adjusts
     * the map to have that monument.
     * @param square 2d array of tiles that form a monument
     */
    public void placeMonument(int[][] square){
        for (int i = 1; i < map.availableMonuments.size()+1; i++) {
            System.out.println(i + " " + map.availableMonuments.get(i-1));
        }
        System.out.println("Select the number to get the appropriate monument you would like to place.");
        int number=helper.tryParseInt();
        String monument="";
        try {
            monument=map.availableMonuments.get(number - 1).substring(0,3);
            map.unavailableMonuments.add(monument);
            map.availableMonuments.remove(number-1);
        }
        catch (Exception e) {
            System.out.println("That is not one of the numbers listed.");
            placeMonument(square);
        }
        for (int[] tile : square){
            int row=tile[0];
            int column=tile[1];
            map.board[row][column]=monument;
        }
    }

    /**
     * Checks if the recently placed tile turned into a temple, if so, an appropriate function in the
     * temple class will be called
     * @param location the tile was placed
     */
    public void monumentCheck(int[] location){
        int row=location[0];
        int column=location[1];
        String tile = map.board[row][column];

        // Top-left corner
        if (row < map.board.length - 1 && column < map.board[0].length - 1) {
            if (tile.equals(map.board[row][column + 1]) &&
                    tile.equals(map.board[row + 1][column]) &&
                    tile.equals(map.board[row + 1][column + 1])) {
                placeMonument(new int[][]{
                        {row, column},
                        {row, column + 1},
                        {row + 1, column},
                        {row + 1, column + 1}
                });
            }
        }

        // Top-right corner
        else if (row < map.board.length - 1 && column > 0) {
            if (tile.equals(map.board[row][column - 1]) &&
                    tile.equals(map.board[row + 1][column]) &&
                    tile.equals(map.board[row + 1][column - 1])) {
                placeMonument(new int[][]{
                        {row, column - 1},
                        {row, column},
                        {row + 1, column - 1},
                        {row + 1, column}
                });
            }
        }
        // Bottom-left corner
        else if (row > 0 && column < map.board[0].length - 1) {
            if (tile.equals(map.board[row][column + 1]) &&
                    tile.equals(map.board[row - 1][column]) &&
                    tile.equals(map.board[row - 1][column + 1])) {
                placeMonument(new int[][]{
                        {row - 1, column},
                        {row - 1, column + 1},
                        {row, column},
                        {row, column + 1}
                });
            }
        }
        // Bottom-right corner
        else if (row > 0 && column > 0) {
            if (tile.equals(map.board[row][column - 1]) &&
                    tile.equals(map.board[row - 1][column]) &&
                    tile.equals(map.board[row - 1][column - 1])) {
                placeMonument(new int[][]{
                        {row - 1, column - 1},
                        {row - 1, column},
                        {row, column - 1},
                        {row, column}
                });
            }
        }
    }
}

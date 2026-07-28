package Midgame;

import Helpers.Helper;
import Setup.Map;
import Setup.Player;

import java.util.HashMap;

public class Adjust_Map {

    Helper helper;
    Map map;
    Monument monument;
    HashMap<String, Player> players;

    /**
     *
     */
    public Adjust_Map(Map map, HashMap<String, Player> players, Helper helper){
        this.map=map;
        this.helper=helper;
        this.players=players;
        this.monument=new Monument(map, players, helper);
    }

    /**
     * Puts the inputted tile at the inputted location, then calls the monumentCheck and ConflictCheck functions
     * @param location to place tile
     * @param tile type being placed
     */
    public void placePiece(int[] location, String tile){
        int row=location[0];
        int column=location[1];
        map.board[row][column]=String.valueOf(tile.charAt(0));
    }

    /**
     * If a leader is already on the board, remove its old position and replace it with a -
     * @param player placing the leader
     * @param color of the leader being placed
     */
    public void removeLeader(Player player, char color){
        if (color==map.temple.charAt(0) && player.redLocation[0]!=-1){
            int oldRow=player.redLocation[0];
            int oldColumn=player.redLocation[1];
            map.board[oldRow][oldColumn]=map.empty;
        }
        else if (color==map.farm.charAt(0) && player.blueLocation[0]!=-1){
            int oldRow=player.blueLocation[0];
            int oldColumn=player.blueLocation[1];
            map.board[oldRow][oldColumn]=map.empty;
        }
        else if (color==map.market.charAt(0) && player.greenLocation[0]!=-1){
            int oldRow=player.greenLocation[0];
            int oldColumn=player.greenLocation[1];
            map.board[oldRow][oldColumn]=map.empty;
        }
        else if (color==map.settlement.charAt(0) && player.blackLocation[0]!=-1){
            int oldRow=player.blackLocation[0];
            int oldColumn=player.blackLocation[1];
            map.board[oldRow][oldColumn]=map.empty;
        }
        updateLeaderPosition(player, color, new int[]{-1, -1});
    }

    /**
     * Puts the new leader on the board and updates the location variables
     * @param player placing the leader
     * @param color of the leader
     * @param location of the leader
     */
    public void updateLeaderPosition(Player player, char color, int[] location){
        switch (color) {
            case 'T' -> {
                player.redLocation[0] = location[0];
                player.redLocation[1] = location[1];
            }
            case 'F' -> {
                player.blueLocation[0] = location[0];
                player.blueLocation[1] = location[1];
            }
            case 'M' -> {
                player.greenLocation[0] = location[0];
                player.greenLocation[1] = location[1];
            }
            case 'S' -> {
                player.blackLocation[0] = location[0];
                player.blackLocation[1] = location[1];
            }
        }
    }

    /**
     * Gets the row and column from location, gets the player by getting the value associated with faction
     * in the players variable, call removeLeader, call updateLeaderPosition, and update the spot on the board
     * to be correlated to the faction's first character and the color's first character
     * @param player that is placing the tile
     * @param color of the faction that is being placed, T for red, M for green, F for blue, S for black
     * @param location player wants to put a piece
     */
    public void placeLeader(Player player, char color, int[] location){
        int row=location[0];
        int column=location[1];
        removeLeader(player, color);
        updateLeaderPosition(player, color, location);
        map.board[row][column]=player.faction.charAt(0) + "" + color;
    }
}
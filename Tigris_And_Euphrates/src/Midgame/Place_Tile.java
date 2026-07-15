package Midgame;

import Helpers.Helper;
import Setup.Map;
import Setup.Player;

import java.util.HashMap;

public class Place_Tile {

    Map map;
    Helper helper;
    Adjust_Map adjustMap;

    public Place_Tile(Map map, Helper helper, HashMap<String, Player> players){
        this.map=map;
        this.helper=helper;
        this.adjustMap=new Adjust_Map(map, players, helper);
    }

    /**
     * Checks to ensure that if a tile is being placed on a river, that the tile is a farm.
     * @param tile location player entered
     * @return true if the tile is not a river, or is a river and a farm, false otherwise.
     */
    public boolean riverCheck(int[] location, String tile){
        int row=location[0];
        int column=location[1];
        if (map.board[row][column].equals(map.river) && !tile.equals(map.farm)){
            System.out.println("Only farms can be placed on river tiles.");
            return false;
        }
        else if (!map.board[row][column].equals(map.river) && tile.equals(map.farm)){
            System.out.println("You can only place farms on river tiles.");
            return false;
        }
        return true;
    }

    /**
     * Responsible for giving the player tiles on a map to choose from, calls chooseLocation to ask the
     * player where they would like to put a tile, and then what tile they want to place.
     */
    public int[] placeTile(Player player){
        int[] location=helper.chooseLocation();
        int i=1;
        for (String availableTile : player.pieces){
            System.out.println(i + ": " + availableTile);
            i++;
        }
        System.out.println("Choose the number corresponding to the tile you'd like to use.");
        String tile=player.pieces.get(helper.tryParseInt()-1);
        if (!riverCheck(location, tile) && !helper.isEmpty(location[0], location[1])){
            placeTile(player);
        }
        adjustMap.placePiece(location, tile);
        return location;
    }
}
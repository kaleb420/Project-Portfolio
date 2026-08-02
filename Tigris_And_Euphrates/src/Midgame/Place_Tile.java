package Midgame;

import Helpers.Helper;
import Setup.Map;
import Setup.Player;

import java.util.HashMap;

/**
 * This class adds a resource tile to the map. The only restriction is the tile must be empty (or a river),
 * and if a farm is being placed it is being placed on a river, or if any other resource is placed it cannot
 * be placed on a river.
 */
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
        String currentTile=helper.getTile(location);
        if (currentTile.equals(map.river) && !tile.equals(map.farm)){
            System.out.println("Only farms can be placed on river tiles.");
            return false;
        }
        else if (!currentTile.equals(map.river) && tile.equals(map.farm)){
            System.out.println("You can only place farms on river tiles.");
            return false;
        }
        return true;
    }

    /**
     * Responsible for giving the player tiles on a map to choose from, calls chooseLocation to ask the
     * player where they would like to put a tile, what tile they want to place, ensuring that tile is
     * a valid tile to place in that location, removing that tile from the players.pieces, and returning
     * the location the new piece was placed
     */
    public int[] placeTile(Player player){
        int[] location=helper.chooseLocation();
        int i=1;
        for (String availableTile : player.pieces){
            System.out.println(i + ": " + availableTile);
            i++;
        }
        System.out.println("Choose the number corresponding to the tile you'd like to use.");
        int tileNumber=helper.tryParseInt()-1;
        String tile=player.pieces.get(tileNumber);
        if (!riverCheck(location, tile) || !helper.isEmpty(location[0], location[1])){
            return placeTile(player);
        }
        player.pieces.remove(tileNumber);
        adjustMap.placePiece(location, tile);
        return location;
    }
}
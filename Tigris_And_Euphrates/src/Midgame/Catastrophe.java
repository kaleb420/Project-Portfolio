package Midgame;

import Helpers.Helper;
import Setup.Map;
import Setup.Player;

public class Catastrophe {

    Map map;
    Helper helper;

    public Catastrophe(Map map, Helper helper){
        this.map=map;
        this.helper=helper;
    }

    /**
     * Function responsible for placing a catastrophe tile, calls the chooseLocation to identify which
     * location the player will pick
     */
    public void placeCatastrophe(Player player){
        if (player.catastrophe<=0){
            System.out.println("You do not have any catastrophe tiles left.");
            return;
        }
        int[] location=helper.chooseLocation();
        String tile=helper.getTile(location);
        if (tile.equals(map.templeWithTreasure)){
            System.out.println("You cannot place a catastrophe tile on a temple with treasure.");
            placeCatastrophe(player);
            return;
        }
        player.adjustMap.placePiece(location, map.catastrophe);
        player.catastrophe--;
        if (tile.equals(map.temple))
            helper.removeLeaderCheck(location);
    }
}

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
        }
        int[] location=helper.chooseLocation();
        player.adjustMap.placePiece(location, map.catastrophe);
        player.catastrophe--;
    }
}

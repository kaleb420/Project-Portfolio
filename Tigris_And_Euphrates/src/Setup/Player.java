package Setup;

import Helpers.Helper;
import Midgame.Adjust_Map;
import Midgame.ExternalConflicts;
import Midgame.InternalConflicts;
import Midgame.Cubes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This serves as a one-stop shop to ask players questions relating to their move, while all adjustments of
 * the map or bag will be handled in the correlated class. This also contains all relevant information
 * to that specific player
 */
public class Player {

    public ArrayList<String> pieces=new ArrayList<>();
    public int catastrophe=2;
    public int[] redLocation=new int[2];
    public int[] blueLocation=new int[2];
    public int[] greenLocation=new int[2];
    public int[] blackLocation=new int[2];
    public String faction;
    public Cubes cubes;
    public Bag bag;
    Map map;
    Helper helper;
    HashMap<String, Player> players;
    public Adjust_Map adjustMap;
    InternalConflicts internalConflicts;
    ExternalConflicts externalConflicts;

    public Player(Bag bag, Map map, String faction){
        this.bag=bag;
        this.map=map;
        for (int i = 0; i < 6; i++) {
            pieces.add(bag.draw());
        }
        redLocation[0]=-1;
        redLocation[1]=-1;
        blueLocation[0]=-1;
        blueLocation[1]=-1;
        greenLocation[0]=-1;
        greenLocation[1]=-1;
        blackLocation[0]=-1;
        blackLocation[1]=-1;
        this.faction=faction;
        helper=new Helper(map);
        internalConflicts=new InternalConflicts(map, players, helper);
        externalConflicts=new ExternalConflicts(map, players, helper);
    }

    /**
     * Called afterward because all players needed to be added to the variable in main before calling this
     * function, also initializes adjustMap
     * @param players faction used as a key, and the player class used as a value
     */
    public void setPlayers(HashMap<String, Player> players){
        this.players=players;
        adjustMap=new Adjust_Map(map, players, helper);
        cubes=new Cubes(map, players);
    }
}
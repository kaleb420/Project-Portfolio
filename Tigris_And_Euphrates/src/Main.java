import Helpers.Helper;
import Midgame.Adjust_Map;
import Midgame.Make_Move;
import Midgame.Monument;
import Setup.Bag;
import Setup.Map;
import Setup.Player;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    static Bag bag=new Bag();
    static HashMap<String, Player> players=new HashMap<>();
    static Map map=new Map();
    static Helper helper=new Helper(map);
    static Adjust_Map adjustMap;
    static Monument monument;
    static Make_Move makeMove;

    /**
     * Initializes the players in the game and adjust map
     * @param playerCount how many players are playing
     */
    public static void initialization(int playerCount){
        Queue<String> factions=new LinkedList<>(List.of(map.lions, map.archers, map.pots, map.bulls));
        for (int i = 0; i < playerCount; i++) {
            String faction=factions.poll();
            Player player=new Player(bag, map, faction);
            players.put(faction, player);
        }
        adjustMap=new Adjust_Map(map, players, helper);
        monument=new Monument(map, players, helper);
        for (String faction : players.keySet()){
            players.get(faction).setPlayers(players);
        }
        makeMove=new Make_Move(map, players, helper);
    }

    /**
     * Calculates the scores and declares the winner of the game after scoring. The score is the
     * least amount of cubes a player has, and highest score wins.
     */
    public static void scoreGame(){
        HashMap<String, Integer> scores=new HashMap<>();
        for (String faction : players.keySet()){
            Player player=players.get(faction);
            int min=Math.min(player.cubes.redCubes, player.cubes.greenCubes);
            min=Math.min(min, player.cubes.blueCubes);
            min=Math.min(min, player.cubes.blackCubes);
            scores.put(player.faction, min);
        }
        String winning="";
        double lowest=Double.POSITIVE_INFINITY;
        for (String faction : scores.keySet()){
            if (lowest>scores.get(faction)){
                winning=faction;
                lowest=scores.get(faction);
            }
            System.out.println("The score for the " + faction + " is " + scores.get(faction));
        }
        System.out.println(winning + " has won the game with " + lowest + " points.");
    }

    /**
     * This is the main function for the game, it runs the game itself until the end condition is reached
     * and continuously calls the associated player to make a move,
     * @param args useless
     */
    public static void main(String[] args) {
        System.out.println("How many players are playing? (1-4)");
        int playerCount=helper.tryParseInt();
        if (playerCount<0 || playerCount>4){
            System.out.println("Too many or too few players, pick a number between 1-4.");
            return;
        }
        System.out.println("Discuss among yourselves who will be the lion, bull, pot, or archer.");
        initialization(playerCount);
        makeMove.gameEngine(playerCount);
        scoreGame();
    }

}

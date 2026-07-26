import Information.Action_Card_Descriptions;
import Information.Dog_Cards;
import Moves.Action_Cards;
import Moves.Make_Move;
import Player_Classes.Player;
import Print.Print;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<Player> players=new ArrayList<>();
    public static List<Player> turnOrder=new ArrayList<>();
    public static int turn;
    static Dog_Cards dc=new Dog_Cards();
    static Action_Cards ac=new Action_Cards();
    static Make_Move mm=new Make_Move(ac);

    /**
     * Checks to see if any player has won the game
     * @return the name of the player who won the game
     */
    public static String winCheck() {
        for (Player p : players){
            if (p.dogsFlipped==6)
                return p.name;
        }
        return null;
    }

    /**
     * This function chooses the player with the highest number dice in their yard to go first,
     * also for simplicity purposes it adds the list of players to each player class
     * @return the player object, the one with the highest number dice in their yard
     */
    static public Player turn_order() {
        int highestYard=0;
        Player firstPlayer=null;
        for (Player p : players) {
            p.addPlayers(players);
            if (highestYard < p.yard.spots.getFirst()){
                highestYard = p.yard.spots.getFirst();
                firstPlayer=p;
            }
        }
        return firstPlayer;
    }

    /**
     * Calls all initialize functions found in the associated files and also sets the first player
     * @param playerCount the number of players in the game
     */
    public static void initialization(int playerCount){
        for (int i=0; i<playerCount; i++) {
            Player p=new Player(dc, mm);
            players.add(p);
        }
        Player firstPlayer=turn_order();
        turnOrder.add(firstPlayer);
        turn=0;
        while (players.size()!=playerCount){
            turnOrder.add(players.removeFirst());
        }
    }

    /**
     * This is the function that runs the game, it is responsible for everything
     * (expand upon this later)
     * @param args is the number of players playing the game, can be up to 4
     */
    public static void main(String[] args) {
        System.out.println("How many players are playing?");
        int playerCount=ac.tryParseInt();
        if (playerCount>=5 || playerCount<=0){
            System.out.println("Too many or too few players, please pick a number from 1-4.");
            return;
        }
        initialization(playerCount);
        String winner=null;
        while (winCheck()==null){
            System.out.println("\nIt is now " + players.get(turn).name + "'s turn.");
            mm.chooseCard(players.get(turn), turn);
            winner=winCheck();
            turn=(turn+1)%playerCount;
        }
        System.out.println(winner + " has won!");
    }
}

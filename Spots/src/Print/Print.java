package Print;

import Information.Action_Card_Descriptions;
import Information.Dog_Cards;
import Moves.Action_Cards;
import Moves.Make_Move;
import Player_Classes.Player;

import java.util.ArrayList;

public class Print {

    public Action_Card_Descriptions acd=new Action_Card_Descriptions();

    public Print(){
    }

    public void printSpotsRemaining(Player player){
        System.out.println("These are the filled spots on your dog cards. | These are the unfilled spots on your dog cards.");
        for (String dogCard : player.filledSpaces.keySet()){
            System.out.print(dogCard + ": ");
            for (int i = 0; i < player.filledSpaces.get(dogCard).size(); i++) {
                System.out.print(player.filledSpaces.get(dogCard).get(i) + " ");
            }
            System.out.print(" | ");
            for (int i = 0; i < player.unfilledSpaces.get(dogCard).size(); i++) {
                System.out.print(player.unfilledSpaces.get(dogCard).get(i) + " ");
            }
            System.out.println();
        }
    }

    /**
     * Simple function to print out all integers in an arrayList, this is to simplify the printing process
     * for the user
     * @param list of integers
     */
    public void printInt(ArrayList<Integer> list){
        for (int element : list){
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public void printDice(Player player, ArrayList<Integer> dice){
        System.out.print("These are the dice you rolled: ");
        printInt(dice);
    }

    public void printPlayerInfo(Player player){
        System.out.println("Player info: ");
        System.out.print("Paws: " + player.paws + " | " + "Yard: ");
        printInt(player.yard.spots);
        System.out.println("Treats: " + player.treats + " | " + "Flipped Dogs Total: "  + player.dogsFlipped);
    }

    public void printCards(Player player){
        System.out.println("These are the used cards.");
        for (String usedCard : player.makeMove.usedCards) {
            System.out.print(usedCard + ": ");
            System.out.println(acd.descriptions.get(usedCard));
        }
        System.out.println("These are the available cards.");
        int i=1;
        for (String actionCard : player.makeMove.availableCards){
            System.out.print(i + " ");
            System.out.print(actionCard + ": ");
            if (actionCard.equals("Retrieve"))
                System.out.println(acd.descriptions.get(actionCard) + "(" + player.dogCards.spots(player.dogCards.nextDog()) + ")");
            else if (player.makeMove.actionCardTreats.get(actionCard)!=0)
                System.out.print("Worth " + player.makeMove.actionCardTreats.get(actionCard) + " treat! ");
            System.out.println(acd.descriptions.get(actionCard));
            i++;
        }
    }

    public void printYard(Player player){
        for (Player p : player.players){
            System.out.print(p.name + " has the following yard: ");
            printInt(p.yard.spots);
        }
    }
}

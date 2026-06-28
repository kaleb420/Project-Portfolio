package Moves;

import Information.Action_Card_Descriptions;
import Player_Classes.Player;
import Print.Print;

import java.util.*;

public class Make_Move {

    public List<String> availableCards=new ArrayList<>();
    public List<String> usedCards=new ArrayList<>();
    public HashMap<String, Integer> actionCardTreats=new HashMap<>();
    public Action_Cards actionCards;
    public Print print=new Print();
    Scanner scan=new Scanner(System.in);

    /**
     * Adds howl and roll over to the available card list, then randomly chooses a green, purple, blue,
     * and orange card to add to the available card list.
     */
    public Make_Move(Action_Cards ac){
        availableCards.add("Howl");
        availableCards.add("Roll Over");
        actionCards=ac;
        String greenCard=actionCards.green.get(new Random().nextInt(actionCards.green.size()));
        String purpleCard=actionCards.purple.get(new Random().nextInt(actionCards.purple.size()));
        String blueCard=actionCards.blue.get(new Random().nextInt(actionCards.blue.size()));
        String orangeCard=actionCards.orange.get(new Random().nextInt(actionCards.orange.size()));
        availableCards.add(greenCard);
        availableCards.add(purpleCard);
        availableCards.add(blueCard);
        availableCards.add(orangeCard);
        for (String card : availableCards){
            actionCardTreats.put(card, 0);
        }
    }

    /**
     * Determines if there is only 1 action card left, causing all the used cards to be able to be used
     * again and the card that was not used getting a treat for whoever uses it.
     */
    public void lastCardCheck(){
        if (availableCards.size()==1){
            actionCardTreats.put(availableCards.getFirst(), actionCardTreats.get(availableCards.getFirst())+1);
            availableCards.addAll(usedCards);
            usedCards.clear();
        }
    }

    public void updateCardInformation(Player p, String pickedCard){
        availableCards.remove(pickedCard);
        usedCards.addLast(pickedCard);
        if (actionCardTreats.containsKey(pickedCard)) {
            p.treats+=actionCardTreats.get(pickedCard);
            actionCardTreats.put(pickedCard, 0);
        }
    }

    public boolean canFlip(Player p){
        boolean flipCheck=false;
        for (String dogCard : p.unfilledSpaces.keySet()) {
            if (p.unfilledSpaces.get(dogCard).isEmpty()) {
                flipCheck = true;
                break;
            }
        }
        return flipCheck;
    }

    /**
     * The player will choose the card they want to pick, or flip completed cards. After choosing a card
     * this function will call the moveController function with the associated card.
     * @param p player currently choosing a card
     */
    public void chooseCard(Player p, int turn) {
        turn+=1;
        print.printCards(p);
        print.printPlayerInfo(p);
        print.printSpotsRemaining(p);
        System.out.println(p.players.get(turn-1).name + " choose the number corresponding to the card you would like to play. Or if you would like to flip your already filled dog cards, please type 0.");
        int number=actionCards.tryParseInt();
        boolean flipCheck=canFlip(p);
        if (number==0 && flipCheck) {
            p.flipCompletedCards(p);
            return;
        }
        else if (number==0){
            System.out.println("You have no cards to flip, choose an action card.");
            chooseCard(p, turn-1);
        }
        if (number>availableCards.size()+1 || number<=0) {
            System.out.println("Choose a number listed.");
            chooseCard(p, turn-1);
        }
        String pickedCard=availableCards.get(number-1);
        updateCardInformation(p, pickedCard);
        lastCardCheck();
        moveController(p, pickedCard);
    }

    /**
     * This function is responsible for removing a die from the dice list, and filling in the associated
     * number on the asssociated dog card
     * @param player whose dog card is being modified
     * @param dice list of dice rolled
     * @param dogName dog card being modified
     * @param input number being removed from dice and added onto dog card
     */
    void putSpot(Player player, ArrayList<Integer> dice, String dogName, int input){
        dice.remove(dice.indexOf(input));
        ArrayList<Integer> filled=player.filledSpaces.get(dogName);
        filled.add(input);
        player.filledSpaces.put(dogName, filled);
        ArrayList<Integer> unfilled=player.unfilledSpaces.get(dogName);
        unfilled.remove(unfilled.indexOf(input));
        player.unfilledSpaces.put(dogName, unfilled);
    }

    /**
     * This function takes care of the placing dice mechanism in the program, while the user wants to continue
     * placing dice they can do so, then they choose what dice from the given roll they want to use on the
     * dog card they chose.
     * @param player who rolled the dice
     * @param dogName the dog card the player wants to add spots to
     * @param dice dice rolled from the most recent action card
     */
    void placeSpots(Player player, String dogName, ArrayList<Integer> dice){
        while (true) {
            boolean usableDice=false; // does the user have any usable dice?
            for (Integer die : dice) { // if the user has no usable dice for this dog card, take them back to moveController
                if (player.unfilledSpaces.get(dogName).contains(die))
                    usableDice = true;
            }
            if (dice.isEmpty() || player.unfilledSpaces.get(dogName).isEmpty() || !usableDice) {
                System.out.println("You are out of dice to place.");
                return;
            }
            System.out.print("These are the available dice options: ");
            print.printInt(dice);
            System.out.print("And these are the spots that " + dogName + " has left: ");
            print.printInt(player.unfilledSpaces.get(dogName));
            System.out.println("Put in the number that you would like to place on the dog. Once you are done placing dice on this dog card please type 0.");
            int input=actionCards.tryParseInt();
            if (input==0)
                return;
            if (dice.contains(input)){
                putSpot(player, dice, dogName, input);
            }
            else
                System.out.println("You do not have that dice or that dice does not exist on this dog card, please pick one from the available options.");
        }
    }

    /**
     * The current player chooses the dog they want to put spots on, after the dog is chosen the place
     * spots function is called
     * @param player choosing the dog card
     * @param spots dice rolled from action card
     */
    void chooseDog(Player player, ArrayList<Integer> spots){
        String dogName;
        while (true) {
            if (spots.isEmpty())
                break;
            print.printSpotsRemaining(player);
            System.out.print("These are the dice you have left to place: ");
            print.printInt(spots);
            System.out.println("Type in the name of the dog card you would like to fill in spots. To stop placing dice, or if you cannot place the dice on dog cards, type in d.");
            dogName=scan.nextLine();
            if (dogName.equals("d"))
                break;
            if (player.unfilledSpaces.containsKey(dogName))
                placeSpots(player, dogName, spots);
            else
                System.out.println("That dog is not a part of your unflipped dog cards. Please select one that is.");
        }
        player.yard.add(spots, player);
    }

    /**
     * This is the function responsible for calling all other relevant functions for moving, as well as
     * query the user if they would like to use a treat, and what dog card they would like to place dice on
     * after typing in a name of the correlated dog card they are taken to the place spots function to place
     * the spots on the dog card
     * @param player placing dice
     * @param actionCard the action card the player previously chose
     */
    void moveController(Player player, String actionCard){
        ArrayList<Integer> spots=new ArrayList<>(List.of());
        actionCards.functionCall.get(actionCard).accept(player, spots);
        chooseDog(player, spots);
        player.autoFlip(player);
    }
}

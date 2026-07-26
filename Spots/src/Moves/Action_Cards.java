package Moves;

import Player_Classes.Player;
import Print.Print;

import java.util.*;
import java.util.function.BiConsumer;

public class Action_Cards {

    public Scanner scan=new Scanner(System.in);
    public List<String> green=new ArrayList<>();;
    public List<String> purple=new ArrayList<>();;
    public List<String> blue=new ArrayList<>();;
    public List<String> orange=new ArrayList<>();
    public HashMap<String, BiConsumer<Player, ArrayList<Integer>>> functionCall= new HashMap<>();
    public Print print=new Print();

    public Action_Cards(){
        functionCall.put("Howl", this::howl);
        functionCall.put("Roll Over", this::rollOver);
        functionCall.put("Dig", this::dig);
        functionCall.put("Retrieve", this::retrieve);
        functionCall.put("Trot", this::trot);
        functionCall.put("Walk", this::walk);
        functionCall.put("Track", this::track);
        functionCall.put("Chase", this::chase);
        functionCall.put("Run", this::run);
        functionCall.put("Bolt", this::bolt);
        functionCall.put("Dogpile", this::dogpile);
        functionCall.put("Sprint", this::sprint);
        functionCall.put("Chew", this::chew);
        functionCall.put("Play Dead", this::playDead);
        functionCall.put("Beg", this::beg);
        functionCall.put("Stay", this::stay);
        functionCall.put("Gobble", this::gobble);
        functionCall.put("Guard", this::guard);
        functionCall.put("Scavenge", this::scavenge);
        functionCall.put("Hunt", this::hunt);
        functionCall.put("Fetch", this::fetch);
        functionCall.put("Search", this::search);
        orange.add("Dig");
        orange.add("Retrieve");
        orange.add("Trot");
        orange.add("Walk");
        orange.add("Track");
        purple.add("Chase");
        purple.add("Run");
        purple.add("Bolt");
        purple.add("Dogpile");
        purple.add("Sprint");
        green.add("Chew");
        green.add("Play Dead");
        green.add("Beg");
        green.add("Stay");
        green.add("Gobble");
        blue.add("Guard");
        blue.add("Scavenge");
        blue.add("Hunt");
        blue.add("Fetch");
        blue.add("Search");
        scan=new Scanner(System.in);
    }

    public int tryParseInt(){
        String input=scan.nextLine();
        try {
            return Integer.parseInt(input);
        }
        catch (NumberFormatException ex){
            System.out.println("Please enter a number.");
            return tryParseInt();
        }
    }

    /**
     * A simple function that picks a random number between 1-6
     * @return a random number between 1-6
     */
    int rollDice(){
        return new Random().nextInt(6)+1;
    }

    void rollXDice(ArrayList<Integer> dice, int limit){
        for (int i = 0; i < limit; i++) {
            dice.add(rollDice());
        }
    }

    /**
     * Determines if the player has a treat, if they do they are given the option to use a treat then
     * asks the player if they would like to use a treat to reroll the last roll, and removes a treat
     * repeats until the user says no
     */
    void useTreat(Player player, ArrayList<Integer> dice, int start, int end){
        if (player.treats<=0)
            return;
        print.printSpotsRemaining(player);
        print.printDice(player, dice);
        System.out.print("y or n, would you like to use a treat to reroll these dice: ");
        ArrayList<Integer> printList=new ArrayList<>();
        for (int i = start; i < end; i++) {
            printList.add(dice.get(i));
        }
        print.printInt(printList);
        String input=yesOrNo();
        if (input.equals("y")){
            player.treats--;
            for (int i = start; i < end; i++) {
                dice.set(i, rollDice());
            }
            print.printDice(player, dice);
            useTreat(player, dice, start, end);
        }
    }

    /**
     * A helper function used to roll one more dice at a time a certain number of times
     * @param dice currently rolled
     * @param limit how many more dice can be rolled
     */
    void rollMoreDice(Player player, ArrayList<Integer> dice, int limit){
        print.printDice(player, dice);
        print.printSpotsRemaining(player);
        for (int i = 0; i < limit; i++) {
            System.out.println("Would you like to roll an additional dice? (y/n)");
            String input=yesOrNo();
            if (input.equals("n"))
                break;
            else if (input.equals("y")) {
                rollXDice(dice, 1);
                useTreat(player, dice, dice.size()-1, dice.size());
            }
        }
    }

    /**
     * Simple function to tell the user to input y for yes or n for no. Safeguards against other inputs.
     * @return y for yes, n for no
     */
    String yesOrNo(){
        String input=scan.nextLine();
        if (input.equals("y") || input.equals("n"))
            return input;
        System.out.println("Please type in y for yes or n for no.");
        return yesOrNo();
    }

    /**
     * Add the top card of the dog deck to your pack (You can have 6 cards max). Then roll 1 die.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void howl(Player player, ArrayList<Integer> dice) {
        player.draw();
        rollXDice(dice, 1);
        useTreat(player, dice, 0, dice.size());
    }

    /**
     * Roll all your buried dice and then place or rebury them. Then you may roll 1 die.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void rollOver(Player player, ArrayList<Integer> dice) {
        rollXDice(dice, player.yard.spots.size());
        useTreat(player, dice, 0, dice.size());
        player.yard.spots.clear();
        rollMoreDice(player, dice, 1);
    }

    void decreaseYard(Player player, int amount){
        if (player.yard.spots.isEmpty()){
            System.out.println("You cannot decrease a yard that does not have any dice.");
            dig(player, new ArrayList<>());
            return;
        }
        while (amount>0){
            if (player.yard.sum(player)==0)
                break;
            player.yard.spots.set(0, player.yard.spots.getFirst()-1);
            amount--;
            if (player.yard.spots.getFirst()==0)
                player.yard.spots.removeFirst();
        }
        print.printInt(player.yard.spots);
    }

    void increaseYard(Player player, int amount){
        if (player.yard.spots.isEmpty()){
            System.out.println("You cannot increase a yard that does not have any dice.");
            dig(player, new ArrayList<>());
            return;
        }
        while (amount>0){
            if (player.yard.spots.getFirst()==6 && player.yard.spots.size()>=2)
                player.yard.spots.set(1, player.yard.spots.get(1)+1);
            else if (player.yard.spots.getFirst()!=6)
                player.yard.spots.set(0, player.yard.spots.getFirst()+1);
            amount--;
        }
        player.yard.checkBust(player);
    }

    /**
     * Checks to see if the name inputted is part of the collection.
     * @param player using the card, not relevant
     * @param name user inputted
     * @return index of player whose yard will be adjusted if it exists, otherwise -1
     */
    int playerCheck(Player player, String name){
        int i=0;
        for (Player p : player.players){
            if (p.name.equals(name))
                return i;
        }
        return -1;
    }

    /**
     * Add the top card of the dog deck to your pack (You can have 6 cards max). Then roll 1 die.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void dig(Player player, ArrayList<Integer> dice){
        System.out.println("Would you like to adjust your own or another player's yard? (y/n)");
        String input=yesOrNo();
        if (input.equals("y")) {
            System.out.println("What is the player's name who's yard you would like to adjust?");
            print.printYard(player);
            String name=scan.nextLine();
            int playerNumber=playerCheck(player, name);
            if (playerNumber==-1) {
                System.out.println("That is not a valid name.");
                dig(player, dice);
                return;
            }
            System.out.println("How much do you want to adjust by? You currently have " + player.paws + " paws.");
            int adjustAmount=tryParseInt();
            if (adjustAmount > player.paws) {
                System.out.println("You do not have that many paws.");
                dig(player, dice);
                return;
            }
            System.out.println("Would you like to increase or decrease their yard? (i/d)");
            String adjust = scan.nextLine();
            System.out.println(player.players.size());
            if (adjust.equals("i"))
                increaseYard(player.players.get(playerNumber), adjustAmount);
            else if (adjust.equals("d"))
                decreaseYard(player.players.get(playerNumber), adjustAmount);
            else {
                System.out.println("Enter i for increase or d for decrease.");
                dig(player, dice);
                return;
            }
        }
        rollXDice(dice, 2);
        useTreat(player, dice, 0, dice.size());
    }

    /**
     * Take 1 die and set it to any number shown on the top card of the dog deck. Then roll 2 dice.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void retrieve(Player player, ArrayList<Integer> dice){
        String nextDog=player.dogCards.nextDog();
        ArrayList<Integer> spots=player.dogCards.spots(nextDog);
        System.out.print(nextDog + " is the next dog, and these are the available spots: ");
        print.printInt(spots);
        print.printSpotsRemaining(player);
        System.out.println("Please select the dice number you would like to put on a dog card.");
        int input=tryParseInt();
        if (spots.contains(input)){
            dice.add(input);
        }
        else {
            System.out.println("That input does not exist, please input a number that is on the next dog card.");
            retrieve(player, dice);
            return;
        }
        rollXDice(dice, 2);
        useTreat(player, dice, 1, dice.size());
    }

    /**
     * You may move 1 die on your dog cards to any other space, changing the number if needed. Then roll 2 dice.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void trot(Player player, ArrayList<Integer> dice){
        System.out.println("Would you like to move any die currently on one of your dog cards to a different space? (y/n)");
        print.printSpotsRemaining(player);
        String removeCard=yesOrNo();
        if (removeCard.equals("y")){
            System.out.println("Please pick a dog card to remove a spot from.");
            removeCard=scan.nextLine();
            if (!player.filledSpaces.containsKey(removeCard)){
                System.out.println("Please pick a dog card you have.");
                trot(player, dice);
                return;
            }
            System.out.println("Please pick a spot to remove from the dog.");
            int removeSpot=tryParseInt();
            if (!player.filledSpaces.get(removeCard).contains(removeSpot)){
                System.out.println("The associated dog card does not have that spot, please try again.");
                trot(player, dice);
                return;
            }
            print.printSpotsRemaining(player);
            System.out.println("What is the name of the dog that you would like to add a spot to?");
            String addCard=scan.nextLine();
            if (!player.unfilledSpaces.containsKey(addCard)){
                System.out.println("You do not have that dog card in your hand.");
                trot(player, dice);
                return;
            }
            System.out.println("What number would you like to set the spot to?");
            int addSpot=tryParseInt();
            if (!player.unfilledSpaces.get(addCard).contains(addSpot)) {
                System.out.println("You cannot add that dice to this card, because the card does not need it or it already has it. Please try again.");
                trot(player, dice);
                return;
            }
            int removeIndex=player.unfilledSpaces.get(addCard).indexOf(addSpot);
            player.unfilledSpaces.get(addCard).remove(removeIndex);
            player.filledSpaces.get(addCard).add(addSpot); // adds the spot to the dog card
            removeIndex=player.filledSpaces.get(removeCard).indexOf(removeSpot);
            player.unfilledSpaces.get(removeCard).add(removeSpot);
            player.filledSpaces.get(removeCard).remove(removeIndex); // removes the spot from the dog card
        }
        rollXDice(dice, 2);
        useTreat(player, dice, 0, dice.size());
    }

    /**
     * Roll 2 dice. Then you may roll 1 die.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void walk(Player player, ArrayList<Integer> dice){
        rollXDice(dice, 2);
        useTreat(player, dice, 0, dice.size());
        rollMoreDice(player, dice, 1);
    }

    /**
     * Roll 3 dice. If you spend treats to reroll the results, you may choose to reroll only some of the dice.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void track(Player player, ArrayList<Integer> dice){
        ArrayList<Integer> roll =new ArrayList<>();
        if (dice.isEmpty()) {
            rollXDice(roll, 3);
        }
        print.printDice(player, roll);
        int input=0;
        if (player.treats>=1) {
            System.out.println("How many dice would you like to reroll? (0-3)");
            input=tryParseInt();
            if (input < 0 || input >= 4) {
                System.out.println("Please enter a number between 0-3.");
                track(player, dice);
                return;
            }
        }
        if (input>=1)
            player.treats--;
        while (true) {
            ArrayList<Integer> temp=new ArrayList<>();
            for (int i = 0; i < input; i++) {
                System.out.print("What number dice would you like to reroll? These are the options: ");
                print.printInt(roll);
                int rerollDice=tryParseInt();
                if (roll.contains(rerollDice)) {
                    temp.add(rollDice());
                    roll.remove(roll.indexOf(rerollDice));
                }
                else {
                    System.out.println("You did not roll that dice number originally, please pick one that you rolled.");
                    i--;
                }
            }
            roll.addAll(temp);
            temp.clear();
            print.printDice(player, roll);
            System.out.println("Would you like to reroll again?");
            String useTreat=yesOrNo();
            if (useTreat.equals("n"))
                break;
        }
        dice.addAll(roll);
    }

    /**
     * Roll 1 die. You may repeat this trick as many times as you want but each time roll 1 more die than you just did (2, 3, 4...).
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void chase(Player player, ArrayList<Integer> dice){
        String input;
        for (int i = 0; i < 10; i++) {
            rollXDice(dice, i+1);
            print.printDice(player, dice);
            useTreat(player, dice, 0, dice.size());
            System.out.println("Would you like to roll more dice? (y/n)");
            input=yesOrNo();
            if (input.equals("n"))
                break;
        }
    }

    /**
     * Roll 3 dice. Then you may roll 1 die. You may repeat this second step as many times as you want.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void run(Player player, ArrayList<Integer> dice){
        rollXDice(dice, 3);
        useTreat(player, dice, 0, dice.size());
        rollMoreDice(player, dice, 100);
    }

    /**
     * Set aside 3-12 dice. Then roll 1 of these at a time. You may stop early if you have at least as many paws as dice left to roll.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void bolt(Player player, ArrayList<Integer> dice){
        System.out.println("How many dice would you like to set aside, pick a number between 3-12.");
        int number=tryParseInt();;
        if (number>=3 && number<=12){
            while (number!=0){
                System.out.println("You have " + number + " left to roll, and you may choose to stop at " + player.paws + " dice left.");
                rollXDice(dice, 1);
                number--;
                print.printDice(player, dice);
                useTreat(player, dice, 0, dice.size());
                if (number<=player.paws && number!=0) {
                    System.out.println("Would you like to stop rolling? (y/n)");
                    String input=yesOrNo();
                    if (input.equals("y"))
                        break;
                }
            }
        }
        else {
            System.out.println("Pick a number between 3-12.");
            bolt(player, dice);
        }
    }

    void takeFromYard(Player player, ArrayList<Integer> dice){
        if (player.yard.spots.isEmpty()){
            System.out.println("You have no dice in your yard.");
            return;
        }
        System.out.print("Which die from your yard would you like to choose? Here are your options: ");
        print.printInt(player.yard.spots);
        int number=tryParseInt();
        if (!player.yard.spots.contains(number)){
            System.out.println("Your yard does not contain that number, please try again.");
            dogpile(player, dice);
            return;
        }
        player.yard.spots.remove(player.yard.spots.indexOf(number));
        rollXDice(dice, 3);
    }

    void takeFromDog(Player player, ArrayList<Integer> dice){
        print.printSpotsRemaining(player);
        System.out.println("Please type in the name of the dog card you would like to take a die from.");
        String dogCard=scan.nextLine();
        if (!player.filledSpaces.containsKey(dogCard)){
            System.out.println("You do not have that dog card, please try again.");
            dogpile(player, dice);
            return;
        }
        System.out.println("Which die on " + dogCard + " would you like to remove?");
        int die=tryParseInt();
        if (!player.filledSpaces.get(dogCard).contains(die)) {
            System.out.println("You do not have that die on that dog card, please try again.");
            dogpile(player, dice);
            return;
        }
        player.filledSpaces.get(dogCard).remove(player.filledSpaces.get(dogCard).indexOf(die));
        rollXDice(dice, 3);
    }

    /**
     * Take 2 dice, plus 1 more from your buried dice or dog cards (if you have any). Roll them all.
     * You may repeat this trick as many times as you want.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void dogpile(Player player, ArrayList<Integer> dice){
        int start=dice.size();
        System.out.println("Would you like to take a dice from your yard, dog card, or neither? (y/d/n)");
        String input=scan.nextLine();
        if (input.equals("y")){
            takeFromYard(player, dice);
        }
        else if (input.equals("d")){
            takeFromDog(player, dice);
        }
        else if (input.equals("n")){
            rollXDice(dice, 2);
        }
        else {
            System.out.println("Please type in y for yard, or d for dog cards.");
            dogpile(player, dice);
            return;
        }
        useTreat(player, dice, start, dice.size());
        print.printDice(player, dice);
        System.out.println("Would you like to repeat this trick? (y/n)");
        input=yesOrNo();
        if (input.equals("y"))
            dogpile(player, dice);
    }

    /**
     * Roll 1 die for each of your dog cards that has an unfilled space. You may repeat this trick as many times as you want.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void sprint(Player player, ArrayList<Integer> dice){
        while (true) {
            int unfilledCards = 0;
            for (String dogCard : player.unfilledSpaces.keySet()) {
                if (!player.unfilledSpaces.get(dogCard).isEmpty())
                    unfilledCards++;
            }
            int start=dice.size();
            rollXDice(dice, unfilledCards);
            useTreat(player, dice, start, dice.size());
            System.out.println("Would you like to repeat this trick? (y/n)");
            String input=yesOrNo();
            if (input.equals("n"))
                break;
        }
    }

    /**
     * Double your treats. Then take 1 more treat.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function (not needed for this function)
     */
    void chew(Player player, ArrayList<Integer> dice){
        player.treats=(player.treats*2)+1;
    }

    /**
     * Roll 1 die. Then if you buried the die without busting take 1 treat for each spot showing on the die.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void playDead(Player player, ArrayList<Integer> dice){
        if (dice.isEmpty())
            rollXDice(dice, 1);
        print.printDice(player, dice);
        useTreat(player, dice, 0, dice.size());
        System.out.println("Would you like to place or bury the die? (p/b)");
        String input=scan.nextLine();
        if (input.equals("b")){
            player.yard.add(dice, player);
            if (!player.yard.spots.isEmpty())
                player.treats+=dice.getFirst();
            dice.removeFirst();
        }
        else if (input.equals("p")) {
        }
        else {
            System.out.println("Please type in p to place, or b to bury.");
            playDead(player, dice);
        }
    }

    /**
     * Take 1 treat. Then take 1 more treat for each paw on your dog cards.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void beg(Player player, ArrayList<Integer> dice){
        player.treats+=1+player.paws;
    }

    /**
     * Take 1 treat for each facedown trick tile other than this one.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void stay(Player player, ArrayList<Integer> dice){
        player.treats+=player.makeMove.usedCards.size()-1;
    }

    /**
     * Take 7 treats. Then return 1 for each spot in your highest unfilled space.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void gobble(Player player, ArrayList<Integer> dice){
        int highest=0;
        for (String card : player.unfilledSpaces.keySet()) {
            for (int j = 0; j < player.unfilledSpaces.get(card).size(); j++) {
                if (player.unfilledSpaces.get(card).get(j)>highest)
                    highest=player.unfilledSpaces.get(card).get(j);
            }
        }
        player.treats+=(7-highest);
    }

    /**
     * Name a number and roll 4 dice. Discard any dice greater than the number you named. Place or bury the rest.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void guard(Player player, ArrayList<Integer> dice){
        System.out.println("What number (0-6) would you like to guard against?");
        int input=tryParseInt();
        if (input<0){
            System.out.println("Please select a number between 0-6.");
            guard(player, dice);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int roll=rollDice();
            if (roll<=input)
                dice.add(roll);
        }
        useTreat(player, dice, 0, dice.size());
    }

    /**
     * Roll 4 dice. You may discard any dice lower than the total number of spots on all your buried dice. Place or bury the rest.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void scavenge(Player player, ArrayList<Integer> dice){
        rollXDice(dice, 4);
        int discard=0;
        useTreat(player, dice, 0, dice.size());
        while (discard<player.yard.sum(player)){
            System.out.println("So far you have discarded a total of: " + discard);
            System.out.println("Would you like to discard any die? (y/n)");
            print.printDice(player, dice);
            String input=yesOrNo();
            if (input.equals("y")){
                System.out.println("You can discard up to a total of " + player.yard.sum(player) + ". What die would you like to discard?");
                int number=tryParseInt();
                if (number+discard>=player.yard.sum(player)) {
                    System.out.println("You cannot remove this die because you would be discarding more than your current total of buried die.");
                }
                else if (dice.contains(number)) {
                    discard+=number;
                    dice.remove(dice.indexOf(number));
                }
            }
            if (input.equals("n"))
                break;
        }
    }

    /**
     * Roll 2 dice along with 1 more die for each paw on your dog cards. Place or bury 2 of the dice. Discard the rest.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void hunt(Player player, ArrayList<Integer> dice){
        ArrayList<Integer> temp =new ArrayList<>();
        rollXDice(temp, 2+player.paws);
        useTreat(player, temp, 0, temp.size());
        int i=1;
        while (i<3){
            print.printDice(player, temp);
            System.out.println("Please select two dice to either place or bury, this is dice number " + i);
            int number=tryParseInt();
            if (temp.contains(number)) {
                dice.add(number);
                temp.remove(temp.indexOf(number));
                i++;
            }
            else {
                System.out.println("Number was not rolled, please pick a number that was rolled.");
            }
        }
    }

    /**
     * Roll 8 dice. Choose a number you rolled, and place or bury all dice of that number. Discard the rest.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void fetch(Player player, ArrayList<Integer> dice){
        if (dice.isEmpty())
            rollXDice(dice, 8);
        useTreat(player, dice, 0, dice.size());
        System.out.println("Choose a number.");
        int number=tryParseInt();
        if (!dice.contains(number)){
            System.out.println("Please enter a number you rolled.");
            fetch(player, dice);
            return;
        }
        dice.removeIf(d -> d!=number);
    }

    /**
     * A recursive function that calculates what dice can be used to create a set of 8
     * @param dice rolled from the search function
     * @param sum of the combination in that recursive iteration
     * @param index that recursive iteration is on
     * @param possibleSum a set of all possible sums leading to a total of 8
     */
    void searchRecursionHelper(ArrayList<Integer> dice, int sum, int index, HashSet<ArrayList<Integer>> possibleSum, ArrayList<Integer> set){
        if (sum==0) {
            possibleSum.add(new ArrayList<>(set));
            return;
        }
        if (sum<0 || index==dice.size())
            return;
        set.add(dice.get(index));
        searchRecursionHelper(dice, sum-dice.get(index), index+1, possibleSum, set);
        set.removeLast();
        searchRecursionHelper(dice, sum, index+1, possibleSum, set);
    }

    /**
     * Roll 7 dice. If you can, place or bury a set of dice showing a total of exactly 8 spots. Discard the rest.
     * @param player using the card
     * @param dice numbers rolled put into an array list and used in the makeController function
     */
    void search(Player player, ArrayList<Integer> dice){
        rollXDice(dice, 7);
        useTreat(player, dice, 0, dice.size());
        HashSet<ArrayList<Integer>> possibleSum=new HashSet<>();
        searchRecursionHelper(dice, 8, 0, possibleSum, new ArrayList<>());
        ArrayList<ArrayList<Integer>> ret=new ArrayList<>();
        System.out.println("Please pick a number correlated to the dice you would like to pick.");
        int i=0;
        for (ArrayList<Integer> set : possibleSum) { // doesn't work
            i++;
            ret.add(set);
            System.out.print(i + " ");
            System.out.println(set.toString());
        }
        int input=tryParseInt()-1;
        dice.clear();
        if (ret.isEmpty())
            return;
        dice.addAll(ret.get(input));
    }
}

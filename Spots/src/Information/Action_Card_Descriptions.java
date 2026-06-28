package Information;

import java.util.HashMap;

public class Action_Card_Descriptions {

    public HashMap<String, String> descriptions=new HashMap<>();

    public Action_Card_Descriptions(){
        descriptions.put("Howl", "Add the top card of the dog deck to your pack (You can have 6 cards max). Then roll 1 die.");
        descriptions.put("Roll Over", "Roll all your buried dice and then place or rebury them. Then you may roll 1 die.");
        descriptions.put("Dig", "For each paw on your dog cards, you may raise or lower any player's buried die by 1 (Lowering a 1 discards the die). Then roll 2 dice.");
        descriptions.put("Retrieve", "Take 1 die and set it to any number shown on the top card of the dog deck. Then roll 2 dice.");
        descriptions.put("Trot", "You may move 1 die on your dog cards to any other space, changing the number if needed. Then roll 2 dice.");
        descriptions.put("Walk", "Roll 2 dice. Then you may roll 1 die.");
        descriptions.put("Track", "Roll 3 dice. If you spend treats to reroll the results, you may choose to reroll only some of the dice.");
        descriptions.put("Chase", "Roll 1 die. You may repeat this trick as many times as you want but each time roll 1 more die than you just did (2, 3, 4...).");
        descriptions.put("Run", "Roll 3 dice. Then you may roll 1 die. You may repeat this second step as many times as you want.");
        descriptions.put("Bolt", "Set aside 3-12 dice. Then roll 1 of these at a time. You may stop early if you have at least as many paws as dice left to roll.");
        descriptions.put("Dogpile", "Take 2 dice, plus 1 more from your buried dice or dog cards (if you have any). Roll them all. You may repeat this trick as many times as you want.");
        descriptions.put("Sprint", "Roll 1 die for each of your dog cards that has an unfilled space. You may repeat this trick as many times as you want.");
        descriptions.put("Chew", "Double your treats. Then take 1 more treat.");
        descriptions.put("Play Dead", "Roll 1 die. Then if you buried the die without busting take 1 treat for each spot showing on the die.");
        descriptions.put("Beg", "Take 1 treat. Then take 1 more treat for each paw on your dog cards.");
        descriptions.put("Stay", "Take 1 treat for each facedown trick tile other than this one.");
        descriptions.put("Gobble", "Take 7 treats. Then return 1 for each spot in your highest unfilled space.");
        descriptions.put("Guard", "Name a number and roll 4 dice. Discard any dice greater than the number you named. Place or bury the rest.");
        descriptions.put("Scavenge", "Roll 4 dice. You may discard any dice lower than the total number of spots on all your buried dice. Place or bury the rest.");
        descriptions.put("Hunt", "Roll 2 dice along with 1 more die for each paw on your dog cards. Place or bury 2 of the dice. Discard the rest.");
        descriptions.put("Fetch", "Roll 8 dice. Choose a number you rolled, and place or bury all dice of that number. Discard the rest.");
        descriptions.put("Search", "Roll 7 dice. If you can, place or bury a set of dice showing a total of exactly 8 spots. Discard the rest.");
    }
}

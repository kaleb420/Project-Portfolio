package Player_Classes;

import Information.Dog_Cards;
import Moves.Make_Move;

import java.util.*;

public class Player {

    public Yard yard;
    public HashMap<String, ArrayList<Integer>> filledSpaces=new HashMap<>();
    public HashMap<String, ArrayList<Integer>> unfilledSpaces=new HashMap<>();
    public int dogsFlipped;
    public int dogsUnflipped;
    public int treats;
    public int paws;
    public Make_Move makeMove;
    public Dog_Cards dogCards;
    public List<Player> players=new ArrayList<>();
    public String name="";

    /**
     * Initializes all the data necessary for a player, including name, number of treats, dog cards, and
     * dice in the yard
     */
    public Player(Dog_Cards dc, Make_Move mm){
        treats=1;
        yard=new Yard();
        yard.spots.add(new Random().nextInt(6)+1);
        makeMove=mm;
        dogCards=dc;
        draw();
        draw();
        getName();
        dogsUnflipped=unfilledSpaces.size();
    }

    /**
     * Adds a list of players to the player class, which will be used for a few action cards
     * @param players a list of players playing the game
     */
    public void addPlayers(List<Player> players){
        this.players=players;
    }

    /**
     * Asks the user what name they would like to be called
     */
    public void getName(){
        Scanner scan=new Scanner(System.in);
        System.out.println("What name would you like to be called?");
        name=scan.nextLine();
    }

    /**
     * Draws a dog card and adds it to the players hand, removing it from the shuffled deck
     */
    public void draw(){
        if (dogsFlipped+dogsUnflipped>=6)
            return;
        String name=dogCards.draw();
        unfilledSpaces.put(name, dogCards.spots(name));
        filledSpaces.put(name, new ArrayList<>(List.of()));
        dogsUnflipped++;
        getPaws();
    }

    /**
     * Calculates how many paws the player has, for each dog card with 3 spots, they get one paw, and
     * for each dog card with 4 spots, they get two paws
     */
    public void getPaws(){
        int paws=0;
        for (String dogName : unfilledSpaces.keySet()){
            if (dogCards.dogCards.get(dogName).size()==3)
                paws++;
            else if (dogCards.dogCards.get(dogName).size()==4)
                paws+=2;
        }
        this.paws=paws;
    }

    /**
     * Flips all completed cards, a card is completed if there are no spots left on it.
     * @param p player flipping cards
     */
    public void flipCompletedCards(Player p){
        Iterator<String> us=p.unfilledSpaces.keySet().iterator();
        List<String> remove=new ArrayList<>();
        while (us.hasNext()){
            String dogName=us.next();
            if (p.unfilledSpaces.get(dogName).isEmpty()){
                p.dogsFlipped++;
                p.dogsUnflipped--;
                p.filledSpaces.remove(dogName);
                us.remove();
                remove.add(dogName);
            }
        }
        for (String dogName : remove) {
            p.unfilledSpaces.remove(dogName);
            p.draw();
        }
    }

    /**
     * If a player has all spots on all of their dog cards filled in, they get to automatically
     * flip all of their dog cards over without using a turn, unless they bust at the end of the round
     * @param p player
     */
    public void autoFlip(Player p){
        boolean filled=true;
        for (String dogCard : p.unfilledSpaces.keySet()){
            if (!p.unfilledSpaces.get(dogCard).isEmpty())
                filled=false;
        }
        if (filled)
            flipCompletedCards(p);
    }
}

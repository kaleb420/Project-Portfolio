package Information;

import java.util.*;

public class Dog_Cards {

    public int numCards;
    public List<String> dogNames=new ArrayList<>();
    public Queue<String> shuffledDeck=new LinkedList<>();
    public HashMap<String, ArrayList<Integer>> dogCards=new HashMap<>();

    void fillStructures(){
        dogCards.put("Cabbage", new ArrayList<>(List.of(1, 4, 5)));
        dogCards.put("Ursula", new ArrayList<>(List.of(1, 5)));
        dogCards.put("Frankie", new ArrayList<>(List.of(4, 5)));
        dogCards.put("Doog", new ArrayList<>(List.of(2, 3, 5, 6)));
        dogCards.put("Lief", new ArrayList<>(List.of(1, 2, 3)));
        dogCards.put("Jolene", new ArrayList<>(List.of(2, 4)));
        dogCards.put("Mayor", new ArrayList<>(List.of(1, 6)));
        dogCards.put("Momo", new ArrayList<>(List.of(3, 5, 6)));
        dogCards.put("Rupert", new ArrayList<>(List.of(2, 5, 6)));
        dogCards.put("Bristles", new ArrayList<>(List.of(1, 2)));
        dogCards.put("Xerxes", new ArrayList<>(List.of(2, 5)));
        dogCards.put("Sundrop", new ArrayList<>(List.of(2, 3)));
        dogCards.put("Wilbur", new ArrayList<>(List.of(1, 3, 6)));
        dogCards.put("Missy", new ArrayList<>(List.of(3, 4)));
        dogCards.put("Rogue", new ArrayList<>(List.of(2, 4, 5)));
        dogCards.put("Gretchen", new ArrayList<>(List.of(5, 6)));
        dogCards.put("Lil Beth", new ArrayList<>(List.of(3, 5)));
        dogCards.put("Honcho", new ArrayList<>(List.of(1, 4, 6)));
        dogCards.put("Bongo", new ArrayList<>(List.of(1, 3)));
        dogCards.put("Nutmeg", new ArrayList<>(List.of(2, 4, 6)));
        dogCards.put("Handsome", new ArrayList<>(List.of(3, 6)));
        dogCards.put("Louise", new ArrayList<>(List.of(2, 6)));
        dogCards.put("Pebble", new ArrayList<>(List.of(1, 3, 4)));
        dogCards.put("Gumbo", new ArrayList<>(List.of(1, 2, 6)));
        dogCards.put("Goliath", new ArrayList<>(List.of(1, 2, 5)));
        dogCards.put("Raya", new ArrayList<>(List.of(2, 3, 5)));
        dogCards.put("Burt", new ArrayList<>(List.of(1)));
        dogCards.put("Wolfgang", new ArrayList<>(List.of(2, 3, 4)));
        dogCards.put("Eloise", new ArrayList<>(List.of(1, 4)));
        dogCards.put("Zipper", new ArrayList<>(List.of(4, 5, 6)));
        dogCards.put("Mirabelle", new ArrayList<>(List.of(3, 4, 5)));
        dogCards.put("Bitsy", new ArrayList<>(List.of(4, 6)));
        dogNames.addAll(dogCards.keySet());
        numCards=dogCards.size();
    }

    /**
     * Randomizes the order the dog cards appear in to simulate shuffling
     */
    public Dog_Cards() {
        fillStructures();
        Random r=new Random();
        String name="";
        while (numCards>0) {
            int number=r.nextInt(numCards);
            name=dogNames.remove(number);
            shuffledDeck.add(name);
            numCards--;
        }
    }

    public String nextDog(){
        return shuffledDeck.peek();
    }

    /**
     * Draws a dog card from the shuffled card deck
     * @return the name of the dog card drawn
     *
     */
    public String draw(){
        return shuffledDeck.poll();
    }

    /**
     * Gets the spots associated with the given card name
     * @param name given card name
     * @return associated spots
     */
    public ArrayList<Integer> spots(String name) {
        return dogCards.get(name);
    }
}

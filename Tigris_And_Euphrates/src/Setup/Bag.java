package Setup;

import java.util.*;

public class Bag {

    public int temples=57;
    public int farms=36;
    public int markets=30;
    public int settlements=30;
    public Queue<String> shuffledBag=new LinkedList<>();

    /**
     * Constructor for the class, will shuffle all components, and place them in a queue
     */
    public Bag(){
        while (temples>0 || farms>0 || markets>0 || settlements>0){
            ArrayList<String> options=new ArrayList<>();
            piecesLeft(options);
            int add=new Random().nextInt(options.size());
            shuffledBag.add(options.get(add));
            remove(options.get(add));
        }
    }

    /**
     * Removes one of the chosen elements from the tile choices list
     * @param rm string of tile to remove one element of
     */
    private void remove(String rm){
        switch (rm) {
            case "Temple" -> temples--;
            case "Farm" -> farms--;
            case "Market" -> markets--;
            case "Settlement" -> settlements--;
        }
    }

    /**
     * Helper function to the constructor to determine what pieces have yet to be placed in the bag
     * @param options ArrayList that conveys if there is still pieces of a tile needing to be placed in the bag
     */
    private void piecesLeft(ArrayList<String> options){
        if (temples!=0)
            options.add("Temple");
        if (farms!=0)
            options.add("Farm");
        if (markets!=0)
            options.add("Market");
        if (settlements!=0)
            options.add("Settlement");
    }

    /**
     * Picks the next element from the bag
     */
    public String draw(){
        if (shuffledBag.isEmpty()) {
            System.out.println("There are no pieces left in the bag.");
            return null;
        }
        return shuffledBag.poll();
    }
}

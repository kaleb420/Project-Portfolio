package Player_Classes;

import java.util.ArrayList;
import java.util.Collections;

public class Yard {

    public ArrayList<Integer> spots=new ArrayList<>();
    public boolean bust=false;

    public int sum(Player player){
        int total=0;
        for (int i = 0; i < player.yard.spots.size(); i++) {
            total+=player.yard.spots.get(i);
        }
        return total;
    }

    /**
     * This function imitates the bust action in game, it removes all the dice from their dog cards and
     * from their yard
     * @param player the associated player
     */
    void bust(Player player){
        for (String name : player.filledSpaces.keySet()){
            player.unfilledSpaces.get(name).addAll(player.filledSpaces.get(name));
            player.filledSpaces.get(name).clear();
        }
        player.yard.spots.clear();
        bust=true;
        System.out.println("You just busted!!!\n");
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks to see if the associated player is over the spot limit, causing them to bust
     * @param player the associated player
     */
    public void checkBust(Player player){
        int sum=sum(player);
        if (sum>=8)
            bust(player);
    }

    /**
     * Adds the unused dice from the given roll to the yard
     * @param unused unused dice from the given roll
     * @param player the associated player
     */
    public void add(ArrayList<Integer> unused, Player player) {
        player.yard.spots.addAll(unused);
        checkBust(player);
    }
}

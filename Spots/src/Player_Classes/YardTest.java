package Player_Classes;

import Information.Dog_Cards;
import Moves.Action_Cards;
import Moves.Make_Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class YardTest {

    Dog_Cards dogCards=new Dog_Cards();
    Action_Cards actionCards=new Action_Cards();
    Make_Move makeMove=new Make_Move(actionCards);
    Player player=new Player(dogCards, makeMove);

    @Test
    void sum() {
        player.yard.spots=new ArrayList<>(List.of(2, 4, 2));
        assertEquals(8, player.yard.sum(player));
        player.yard.spots=new ArrayList<>(List.of(2, 4));
        assertEquals(6, player.yard.sum(player));
    }

    @Test
    void bust() { // test with spots filled in the yard and on a card
        player.yard.spots=new ArrayList<>(List.of(2, 4, 2));
        player.unfilledSpaces.put("Doog", new ArrayList<>(List.of(2, 3)));
        player.filledSpaces.put("Doog", new ArrayList<>(List.of(4, 5)));
        player.yard.bust(player);
        assertEquals(0, player.filledSpaces.get("Doog").size());
        assertEquals(4, player.unfilledSpaces.get("Doog").size());
        assertEquals(new ArrayList<>(List.of(2, 3, 4, 5)), player.unfilledSpaces.get("Doog"));
        assertEquals(0, player.yard.spots.size());
        player.yard.bust(player); // after bust everything should be removed, test again with everything removed
        assertEquals(0, player.filledSpaces.get("Doog").size());
        assertEquals(4, player.unfilledSpaces.get("Doog").size());
        assertEquals(new ArrayList<>(List.of(2, 3, 4, 5)), player.unfilledSpaces.get("Doog"));
        assertEquals(0, player.yard.spots.size());
    }

    @Test
    void checkBust() {
        player.yard.spots=new ArrayList<>(List.of(2, 4, 1));
        player.unfilledSpaces.put("Doog", new ArrayList<>(List.of(2, 3)));
        player.filledSpaces.put("Doog", new ArrayList<>(List.of(4, 5)));
        player.yard.checkBust(player);
        assertEquals(2, player.filledSpaces.get("Doog").size());
        assertEquals(2, player.unfilledSpaces.get("Doog").size());
        assertEquals(3, player.yard.spots.size());
        player.yard.spots.add(1);
        player.yard.checkBust(player);
        assertEquals(0, player.filledSpaces.get("Doog").size());
        assertEquals(4, player.unfilledSpaces.get("Doog").size());
        assertEquals(0, player.yard.spots.size());
    }

    @Test
    void add() {
        player.yard.spots=new ArrayList<>(List.of(2, 4, 1));
        player.unfilledSpaces.put("Doog", new ArrayList<>(List.of(2, 3)));
        player.filledSpaces.put("Doog", new ArrayList<>(List.of(4, 5)));
        player.yard.add(new ArrayList<>(List.of(1)), player);
        assertEquals(0, player.filledSpaces.get("Doog").size());
        assertEquals(4, player.unfilledSpaces.get("Doog").size());
        assertEquals(0, player.yard.spots.size());
    }
}
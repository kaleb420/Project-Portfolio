package Moves;

import Information.Dog_Cards;
import Player_Classes.Player;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Make_MoveTest {

    Dog_Cards dogCards=new Dog_Cards();
    Action_Cards actionCards=new Action_Cards();
    Make_Move makeMove=new Make_Move(actionCards);
    Player player=new Player(dogCards, makeMove);

    @Test
    void flipCompletedCards() {
        player.unfilledSpaces.put("Doog", new ArrayList<>(List.of(2)));
        player.filledSpaces.put("Doog", new ArrayList<>(List.of(3, 4, 5)));
        player.flipCompletedCards(player);
        assertEquals(2, player.dogsUnflipped); // realistically the player has 3 dog cards, but due to wanting predictability in how the test functions, we manually add doog without adjusting the class variables
        assertEquals(0, player.dogsFlipped);
        player.unfilledSpaces.put("Doog", new ArrayList<>(List.of()));
        player.filledSpaces.put("Doog", new ArrayList<>(List.of(2, 3, 4, 5)));
        player.flipCompletedCards(player);
        assertEquals(4, player.dogsUnflipped+player.dogsFlipped);
        assertEquals(3, player.dogsUnflipped); // same with last issue, manually adding the card causes a draw which then updates the class variables
        assertEquals(1, player.dogsFlipped);
        assertFalse(player.filledSpaces.containsKey("Doog"));
    }

    @Test
    void chooseCard() { // not done cause I don't know how to do input for test functions
        player.unfilledSpaces.put("Doog", new ArrayList<>(List.of()));
        player.filledSpaces.put("Doog", new ArrayList<>(List.of(2, 3, 4, 5)));
        //makeMove.chooseCard(player, 1);
    }

    @Test
    void placeSpots() {
        ArrayList<Integer> dice =new ArrayList<>(List.of(2, 3, 4));
        player.unfilledSpaces.put("Doog", new ArrayList<>(List.of(2, 3, 4, 5)));
        player.filledSpaces.put("Doog", new ArrayList<>(List.of()));
        String input="2\n" +"3\n" + "b\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        makeMove.placeSpots(player, "Doog", dice); // tests leaving a die out
        assertEquals(new ArrayList<>(List.of(2, 3)), player.filledSpaces.get("Doog"));
        assertEquals(new ArrayList<>(List.of(4, 5)), player.unfilledSpaces.get("Doog"));
        assertTrue(dice.contains(4));
        assertFalse(dice.contains(2));
        input="3\n" + "4\n" + "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        makeMove.placeSpots(player, "Doog", new ArrayList<>(List.of(4, 5))); // tests a die that can be used, and a die that has already been placed
        assertEquals(new ArrayList<>(List.of(2, 3, 4, 5)), player.filledSpaces.get("Doog"));
        assertEquals(new ArrayList<>(), player.unfilledSpaces.get("Doog"));
        ArrayList<Integer> test= new ArrayList<>(List.of(3));
        makeMove.placeSpots(player, "Doog", test); // tests a user entering a filled dog card, and getting the dice back
        assertTrue(test.contains(3));
    }

    @Test
    void moveController() {
    }


    @Test
    void printInt() {
    }
}
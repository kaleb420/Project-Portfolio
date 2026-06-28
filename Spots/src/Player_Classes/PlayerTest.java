package Player_Classes;

import Information.Dog_Cards;
import Moves.Action_Cards;
import Moves.Make_Move;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Dog_Cards dogCards=new Dog_Cards();
    Action_Cards actionCards=new Action_Cards();
    Make_Move makeMove=new Make_Move(actionCards);
    Player player=null;

    PlayerTest(){
        String input="Tom\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        player=new Player(dogCards, makeMove);
    }

    @Test
    void initialization(){
        player.players.addFirst(player);
        assertEquals(1, player.yard.spots.size());
        assertEquals(2, player.dogsUnflipped);
        assertEquals(1, player.treats);
        assertEquals(2, player.unfilledSpaces.size());
        assertEquals("Tom", player.players.getFirst().name);
        assertEquals(1, player.players.size());
    }

    @Test
    void draw() { // will need to test flip mechanics after adding it
        player.draw();
        assertEquals(3, player.unfilledSpaces.size());
        assertEquals(3, player.dogsUnflipped);
        player.draw();
        player.draw();
        player.draw();
        player.draw();
        player.draw();
        assertEquals(6, player.dogsUnflipped+player.dogsFlipped);
    }

    @Test
    void flipCompletedCards(){
        player.draw();
        for (String dogName : player.unfilledSpaces.keySet()){
            player.unfilledSpaces.get(dogName).clear();
        }
        player.flipCompletedCards(player);
        assertEquals(3, player.dogsFlipped);
        assertEquals(3, player.dogsUnflipped);
    }
}
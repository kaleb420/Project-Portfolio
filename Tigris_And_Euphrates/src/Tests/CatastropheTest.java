package Tests;

import Helpers.Helper;
import Helpers.Search_Algorithms;
import Midgame.Catastrophe;
import Setup.Bag;
import Setup.Map;
import Setup.Player;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CatastropheTest {

    Map map=new Map();
    Bag bag=new Bag();
    Player player=new Player(bag, map, "Lions");
    Catastrophe catastrophe=new Catastrophe(map, new Helper(map));
    Search_Algorithms searchAlgorithms=new Search_Algorithms(map);

    @Test
    void placeCatastrophe() {
        String input = "A0\n";  // location
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        catastrophe.placeCatastrophe(player);
        assertEquals(1, player.catastrophe);
        ArrayList<int[]> adjacentSpaces=searchAlgorithms.getAdjacent(new int[]{4,5}); // next to catastrophe space
        assertTrue(adjacentSpaces.isEmpty()); // adjacency should not include catastrophe tiles
    }

    @Test
    void rejectPlacement(){
        String input="A0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        catastrophe.placeCatastrophe(player);
        input="F5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        catastrophe.placeCatastrophe(player);
        assertEquals(0, player.catastrophe);
        input="A0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        catastrophe.placeCatastrophe(player);
        assertSame(map.board[0][0], map.empty);
    }
}
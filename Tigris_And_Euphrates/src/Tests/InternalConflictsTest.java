package Tests;

import Helpers.Helper;
import Helpers.Search_Algorithms;
import Midgame.Adjust_Map;
import Midgame.InternalConflicts;
import Setup.Bag;
import Setup.Map;
import Setup.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class InternalConflictsTest {

    Map map=new Map();
    Helper helper=new Helper(map);
    Bag bag=new Bag();
    Player lion =new Player(bag, map, "Lion");
    Player bull =new Player(bag, map, "Bull");
    HashMap<String, Player> players=new HashMap<>();
    Adjust_Map adjustMap=new Adjust_Map(map, players, helper);
    Search_Algorithms searchAlgorithms=new Search_Algorithms(map);
    InternalConflicts internalConflict;

    @BeforeEach
    void setUp(){
        players.put(map.lions, lion);
        players.put(map.bulls, bull);
        lion.setPlayers(players);
        bull.setPlayers(players);
        this.adjustMap=new Adjust_Map(map, players, helper);
        this.internalConflict=new InternalConflicts(map, adjustMap, players, helper);
        adjustMap.placeLeader(lion, 'T', new int[]{2, 6});
        adjustMap.placeLeader(bull, 'T', new int[]{4, 6});
        map.board[3][4]=map.temple;
        map.board[4][4]=map.temple;
        map.board[3][5]=map.temple;
        map.board[3][6]=map.temple;
        map.board[3][8]=map.temple;
        map.board[3][9]=map.temple;
        map.board[3][10]=map.temple;
        map.board[4][10]=map.temple;
    }

    @Test
    void getStrength() {
        assertEquals(2, internalConflict.getStrength(lion, lion.redLocation, 'T'));
        assertEquals(1, internalConflict.getStrength(bull, bull.redLocation, 'T'));
    }

    @Test
    void internalConflictCheck() { // need to comment out clearConflict in endConflict for this to work
        String input="0\n" + // how many tiles to add to conflict
                "0\n"; // how many tiles to add to conflict
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        helper.scan=new Scanner(System.in);
        internalConflict.internalConflictCheck(lion.redLocation);
        assertEquals(2, internalConflict.leadersInKingdom.size());
        assertTrue(internalConflict.leadersInKingdom.containsKey("BT"));
        assertArrayEquals(new int[]{4, 6}, internalConflict.leadersInKingdom.get("BT"));
        assertTrue(internalConflict.leadersInKingdom.containsKey("LT"));
        assertArrayEquals(new int[]{2, 6}, internalConflict.leadersInKingdom.get("LT"));
        assertEquals(2, internalConflict.leadersFighting.size());
        assertTrue(internalConflict.leadersFighting.containsKey("BT"));
        assertArrayEquals(new int[]{4, 6}, internalConflict.leadersFighting.get("BT"));
        assertTrue(internalConflict.leadersFighting.containsKey("LT"));
        assertArrayEquals(new int[]{2, 6}, internalConflict.leadersFighting.get("LT"));
        assertEquals(2, internalConflict.leadersInKingdom.size());
    }
}
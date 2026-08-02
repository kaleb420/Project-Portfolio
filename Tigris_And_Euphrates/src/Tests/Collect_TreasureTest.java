package Tests;

import Helpers.Helper;
import Helpers.Search_Algorithms;
import Midgame.Collect_Treasure;
import Midgame.Leader;
import Setup.Bag;
import Setup.Map;
import Setup.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class Collect_TreasureTest {

    private Map map=new Map();
    private Bag bag=new Bag();
    private Player lion;
    private Leader leader;
    private Helper helper;
    private HashMap<String, Player> players=new HashMap<>();
    private Search_Algorithms searchAlgorithms;
    private Collect_Treasure collectTreasure;
    private ArrayList<int[]> kingdom;

    @BeforeEach
    void setUp(){
        map.board[0][2]=map.templeWithTreasure;
        lion=new Player(bag, map, map.lions);
        helper=new Helper(map);
        leader=new Leader(map, helper, players);
        players.put(map.lions, lion);
        searchAlgorithms=new Search_Algorithms(map);
        collectTreasure=new Collect_Treasure(players, map, helper);
        String input="Market\n" + // what leader
                "A1\n"; // tile location
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        helper.scan=new Scanner(System.in);
        leader.leader(lion);
        map.board[1][2]=map.temple;
        map.printMap();
        kingdom=searchAlgorithms.BFSSearchKingdom(new int[]{1, 2});
    }

    @Test
    void containsMarketLeaderTrue() {
        ArrayList<int[]> kingdom=searchAlgorithms.BFSSearchKingdom(new int[]{1, 2});
        assertTrue(collectTreasure.containsMarketLeader(lion, kingdom));
    }

    @Test
    void containsMarketLeaderFalse() {
        String input="Market\n" + // what leader
                "I5\n"; // tile location
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        helper.scan=new Scanner(System.in);
        leader.leader(lion);
        assertFalse(collectTreasure.containsMarketLeader(lion, kingdom));
    }

    @Test
    void containsMultipleTemplesWithTreasureTrue() {
        assertTrue(collectTreasure.containsMultipleTemplesWithTreasure(kingdom));
    }

    @Test
    void containsMultipleTemplesWithTreasureFalse() {
        map.board[1][1]=map.temple;
        assertFalse(collectTreasure.containsMultipleTemplesWithTreasure(kingdom));
    }

    @Test
    void removeTreasureFromOuterEdge() {
        collectTreasure.containsMultipleTemplesWithTreasure(kingdom);
        collectTreasure.removeTreasure(lion);
        assertEquals(map.temple, map.board[0][2]);
        assertEquals(map.templeWithTreasure, map.board[1][1]);
    }

    @Test
    void removeTreasureFromRandom() {
        collectTreasure.containsMultipleTemplesWithTreasure(kingdom);
        collectTreasure.removeTreasure(lion);
        assertTrue(map.board[0][2].equals(map.temple) || map.board[1][1].equals(map.temple));
        assertTrue(map.board[0][2].equals(map.templeWithTreasure) || map.board[1][1].equals(map.templeWithTreasure));
    }

    @Test
    void collectTreasure() {
        collectTreasure.collectTreasure(lion);
        assertEquals(1, lion.cubes.wildCubes);
    }

    @Test
    void collectTreasureCheck() {
        int[] location=new int[]{1, 2};
        collectTreasure.collectTreasureCheck(lion, location);
        assertEquals(1, lion.cubes.wildCubes);
    }
}
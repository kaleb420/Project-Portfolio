package Tests;

import Helpers.Helper;
import Helpers.Search_Algorithms;
import Midgame.Adjust_Map;
import Midgame.ExternalConflicts;
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

class ExternalConflictsTest {

    Map map=new Map();
    Helper helper=new Helper(map);
    Bag bag=new Bag();
    Player lion =new Player(bag, map, "Lion");
    Player bull =new Player(bag, map, "Bull");
    HashMap<String, Player> players=new HashMap<>();
    Adjust_Map adjustMap=new Adjust_Map(map, players, helper);
    Search_Algorithms searchAlgorithms=new Search_Algorithms(map);
    ExternalConflicts externalConflicts;

    @BeforeEach
    void setUp(){
        players.put(map.lions, lion);
        players.put(map.bulls, bull);
        lion.setPlayers(players);
        bull.setPlayers(players);
        this.adjustMap=new Adjust_Map(map, players, helper);
        this.externalConflicts=new ExternalConflicts(map, players, helper);
        adjustMap.placeLeader(lion, 'T', new int[]{4, 3});
        adjustMap.placeLeader(bull, 'T', new int[]{4, 9});
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
    void removeLeaderCheck() {
        map.printMap();
        externalConflicts.removeLeaderCheck(new int[]{4, 4});
        assertEquals(map.empty, map.board[4][3]); // there is no adjacent temples to this leader
        adjustMap.placeLeader(bull, 'T', new int[]{2, 6});
        int row=bull.redLocation[0];
        int column=bull.redLocation[1];
        externalConflicts.removeLeaderCheck(new int[]{3, 6}); // adjacent to a temple with treasure, so the leader stays
        assertEquals("BT", map.board[row][column]);
    }

    @Test
    void removeTilesFromKingdom() {
        externalConflicts.tilesInvolved.put(lion, new ArrayList<>());
        externalConflicts.tilesInvolved.put(bull, new ArrayList<>());
        externalConflicts.getStrength(lion, lion.redLocation, 'T');
        externalConflicts.getStrength(bull, bull.redLocation, 'T');
        ArrayList<int[]> expectedLion=new ArrayList<>();
        expectedLion.add(new int[]{3, 4});
        expectedLion.add(new int[]{4, 4});
        expectedLion.add(new int[]{3, 5});
        expectedLion.add(new int[]{3, 6});
        expectedLion.add(new int[]{2, 5});
        assertEquals(expectedLion.size(), externalConflicts.tilesInvolved.get(lion).size());
        boolean pass=false;
        for (int[] test1 : expectedLion){ // tiles in global variable are the ones that were expected
            for (int[] test2 : externalConflicts.tilesInvolved.get(lion)){
                if (test1[0]==test2[0] && test1[1]==test2[1]) {
                    pass=true;
                    break;
                }
            }
            assertTrue(pass);
            pass=false;
        }
        externalConflicts.removeTilesFromKingdom(lion); // with how this test is constructed the lion leader will still be on the board, to check if its actually removed look at externalConflictManager
        assertEquals(map.empty, map.board[3][4]);
        assertEquals(map.empty, map.board[4][4]);
        assertEquals(map.empty, map.board[3][5]);
        assertEquals(map.empty, map.board[3][6]);
        assertEquals(map.templeWithTreasure, map.board[2][5]);
    }

    @Test
    void externalConflictCheck() {
        adjustMap.placePiece(new int[]{3, 7}, map.temple);
        String input="0\n" + // add strength to conflict
                "0\n";  // add strength to conflict
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        helper.scan=new Scanner(System.in);
        externalConflicts.externalConflictCheck(new int[]{3, 7});
        assertTrue(externalConflicts.leadersInKingdom.containsKey("BT"));
        assertTrue(externalConflicts.leadersInKingdom.containsKey("LT"));
        assertEquals(map.empty, map.board[4][9]);
    }

    @Test
    void strength() {
        adjustMap.placeLeader(lion, 'T', new int[]{4, 3});
        adjustMap.placeLeader(bull, 'T', new int[]{4, 9});
        map.board[3][4]=map.temple;
        map.board[4][4]=map.temple;
        map.board[3][5]=map.temple;
        map.board[3][6]=map.temple;
        map.board[3][8]=map.temple;
        map.board[3][9]=map.temple;
        map.board[3][10]=map.temple;
        map.board[4][10]=map.temple;
        externalConflicts.tilesInvolved.put(lion, new ArrayList<>());
        externalConflicts.tilesInvolved.put(bull, new ArrayList<>());
        assertEquals(5,  externalConflicts.getStrength(lion, lion.redLocation, 'T'));
        assertEquals(4,  externalConflicts.getStrength(bull, bull.redLocation, 'T'));
    }
}
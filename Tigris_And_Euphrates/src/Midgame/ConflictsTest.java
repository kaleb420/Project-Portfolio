package Midgame;

import Helpers.Helper;
import Setup.Bag;
import Setup.Map;
import Setup.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ConflictsTest {

    private Map map;
    private Bag bag;
    private Player attacker;
    private Player defender;
    private HashMap<String, Player> players;
    private TestConflict conflict;

    /**
     * Simple implementation so Conflicts can be instantiated.
     */
    private static class TestConflict extends Conflicts {

        public TestConflict(Map map, HashMap<String, Player> players, Helper helper) {
            super(map, players, helper);
        }

        @Override
        int getStrength(Player player, int[] location, char color) {
            return 0;
        }

        @Override
        void removeTilesFromKingdom(Player loser) {}
    }

    @BeforeEach
    void setUp() {

        map = new Map();
        bag = new Bag();

        attacker = new Player(bag, map, "Attacker");
        defender = new Player(bag, map, "Defender");

        players = new HashMap<>();
        players.put(attacker.faction, attacker);
        players.put(defender.faction, defender);

        attacker.setPlayers(players);
        defender.setPlayers(players);

        conflict = new TestConflict(map, players, new Helper(map));

        attacker.pieces.clear();
        defender.pieces.clear();
    }

    @Test
    void testGetColorsFighting() {

        conflict.leadersFighting.put("BT", new int[]{3,3});

        assertEquals('T', conflict.getColorsFighting());
    }

    @Test
    void testPiecesAvailableNone() {

        attacker.pieces.clear();

        assertEquals(0,
                conflict.piecesAvailable(attacker,'T'));
    }

    @Test
    void testPiecesAvailableOneType() {

        attacker.pieces.add("Temple");
        attacker.pieces.add("Temple");
        attacker.pieces.add("Farm");
        attacker.pieces.add("Market");

        assertEquals(2,
                conflict.piecesAvailable(attacker,'T'));

        assertEquals(1,
                conflict.piecesAvailable(attacker,'F'));

        assertEquals(1,
                conflict.piecesAvailable(attacker,'M'));

        assertEquals(0,
                conflict.piecesAvailable(attacker,'S'));
    }

    @Test
    void testRemoveTilesRemovesCorrectNumber() {

        attacker.pieces.add("Temple");
        attacker.pieces.add("Temple");
        attacker.pieces.add("Temple");
        attacker.pieces.add("Farm");

        conflict.removeTiles(attacker,2,'T');

        assertEquals(2, attacker.pieces.size());

        assertEquals(1,
                conflict.piecesAvailable(attacker,'T'));

        assertEquals(1,
                conflict.piecesAvailable(attacker,'F'));
    }

    @Test
    void testRemoveTilesRemoveMoreThanExists() {

        attacker.pieces.add("Temple");
        attacker.pieces.add("Farm");

        conflict.removeTiles(attacker,5,'T');

        assertEquals(1,
                attacker.pieces.size());

        assertEquals("Farm",
                attacker.pieces.get(0));
    }

    @Test
    void testExceedsLimitFalse() {

        assertFalse(conflict.exceedsLimitCheck(2,2));

        assertFalse(conflict.exceedsLimitCheck(1,2));

        assertFalse(conflict.exceedsLimitCheck(0,5));
    }

    @Test
    void testExceedsLimitTrue() {

        assertTrue(conflict.exceedsLimitCheck(3,2));

        assertTrue(conflict.exceedsLimitCheck(10,0));
    }

    @Test
    void testClearConflict() {

        conflict.leadersInKingdom.put("BT",
                new int[]{2,2});

        conflict.leadersFighting.put("BT",
                new int[]{2,2});

        conflict.clearConflict();

        assertTrue(conflict.leadersInKingdom.isEmpty());

        assertTrue(conflict.leadersFighting.isEmpty());
    }

    @Test
    void testStartConflictFalse() {

        map.board[5][5] = "BT";

        assertFalse(
                conflict.startConflict(new int[]{5,5})
        );
    }

    @Test
    void testStartConflictTrue() {

        map.board[5][5] = "BT";
        map.board[5][6] = "LT";

        assertTrue(
                conflict.startConflict(new int[]{5,5})
        );

        assertEquals(2,
                conflict.leadersFighting.size());
    }

    @Test
    void testUpdateVariablesSingleLeader() {

        map.board[5][5] = "BT";

        conflict.getLeadersInKingdom(
                new int[]{5,5}
        );

        assertEquals(1,
                conflict.leadersInKingdom.size());

        assertTrue(
                conflict.leadersInKingdom.containsKey("BT")
        );
    }

    @Test
    void testEndConflictTemplePoints() {

        attacker.cubes.redCubes = 0;

        attacker.redLocation = new int[]{2,2};
        map.board[2][2] = "BT";

        conflict.endConflict(attacker,
                attacker,
                'T',
                3);

        assertEquals(4,
                attacker.cubes.redCubes);

        assertEquals(map.empty,
                map.board[2][2]);
    }

    @Test
    void testEndConflictFarmPoints() {

        attacker.cubes.blueCubes = 0;

        attacker.blueLocation = new int[]{2,2};
        map.board[2][2] = "BF";

        conflict.endConflict(attacker,
                attacker,
                'F',
                2);

        assertEquals(3,
                attacker.cubes.blueCubes);
    }

    @Test
    void testEndConflictMarketPoints() {

        attacker.cubes.greenCubes = 0;

        attacker.greenLocation = new int[]{2,2};
        map.board[2][2] = "BM";

        conflict.endConflict(attacker,
                attacker,
                'M',
                1);

        assertEquals(2,
                attacker.cubes.greenCubes);
    }

    @Test
    void testEndConflictSettlementPoints() {

        attacker.cubes.blackCubes = 0;

        attacker.blackLocation = new int[]{2,2};
        map.board[2][2] = "BS";

        conflict.endConflict(attacker,
                attacker,
                'S',
                5);

        assertEquals(6,
                attacker.cubes.blackCubes);
    }
}
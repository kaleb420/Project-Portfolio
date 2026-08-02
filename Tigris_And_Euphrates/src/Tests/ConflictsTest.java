package Tests;

import Helpers.Helper;
import Midgame.Adjust_Map;
import Midgame.Conflicts;
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
    public static class TestConflict extends Conflicts {

        public TestConflict(Map map, HashMap<String, Player> players, Helper helper) {
            super(map, new Adjust_Map(map, players, helper), players, helper);
        }

        @Override
        public int getStrength(Player player, int[] location, char color) {
            return 0;
        }

        @Override
        public void removeTilesFromKingdom(Player loser) {}

        @Override
        public void endConflict(Player winner, Player loser, char tile, int points) {

        }
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
                conflict.getSameColorLeaders(new int[]{5,5})
        );
    }

    @Test
    void testStartConflictTrue() {

        map.board[5][5] = "BT";
        map.board[5][6] = "LT";

        assertTrue(
                conflict.getSameColorLeaders(new int[]{5,5})
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
}
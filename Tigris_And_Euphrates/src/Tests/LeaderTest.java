package Tests;

import Helpers.Helper;
import Midgame.Leader;
import Setup.Bag;
import Setup.Map;
import Setup.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

    class LeaderTest {

        Map map;
        Bag bag=new Bag();
        Helper helper;
        Leader leader;
        HashMap<String, Player> players;

        @BeforeEach
        void setup() {
            map = new Map();
            helper = new Helper(map);

            players = new HashMap<>();
            players.put("Lion", new Player(bag, map, "Lion"));

            leader = new Leader(map, helper, players);
        }

        //-------------------------------------------------
        // leaderTileCheck()
        //-------------------------------------------------

        @Test
        void leaderTileCheck_AdjacentTemple_ReturnsTrue() {

            map.board[5][5] = map.empty;
            map.board[5][6] = map.temple;

            assertTrue(leader.leaderPlacedNextToTemple(new int[]{5,5}));
        }

        @Test
        void leaderTileCheck_AdjacentTempleWithTreasure_ReturnsTrue() {

            map.board[5][5] = map.empty;
            map.board[4][5] = map.templeWithTreasure;

            assertTrue(leader.leaderPlacedNextToTemple(new int[]{5,5}));
        }

        @Test
        void leaderTileCheck_NoAdjacentTemple_ReturnsFalse() {

            map.board[5][5] = map.empty;
            map.board[5][6] = map.market;

            assertFalse(leader.leaderPlacedNextToTemple(new int[]{5,5}));
        }

        //-------------------------------------------------
        // kingdomDetector()
        //-------------------------------------------------

        @Test
        void kingdomDetector_LeaderTile_ReturnsTrue() {

            map.board[5][5] = "BT";

            assertTrue(leader.kingdomDetector(new int[]{5,5}));
        }

        @Test
        void kingdomDetector_NoLeader_ReturnsFalse() {

            map.board[5][5] = map.temple;

            assertFalse(leader.kingdomDetector(new int[]{5,5}));
        }

        @Test
        void kingdomDetector_FindsLeaderThroughRecursion_ReturnsTrue() {

            map.board[5][5] = map.temple;
            map.board[5][6] = "LT";

            assertTrue(leader.kingdomDetector(new int[]{5,5}));
        }

        //-------------------------------------------------
        // uniteTwoKingdomsStart()
        //-------------------------------------------------

        @Test
        void uniteTwoKingdomsStart_OneAdjacentTile_ReturnsFalse() {

            map.board[5][5] = map.empty;
            map.board[5][6] = map.temple;

            assertFalse(leader.uniteTwoKingdomsStart(new int[]{5,5}));
        }

        @Test
        void uniteTwoKingdomsStart_TwoKingdoms_ReturnsTrue() {

            map.board[5][5] = map.empty;

            map.board[5][4] = "BT";
            map.board[5][6] = "LT";

            assertTrue(leader.uniteTwoKingdomsStart(new int[]{5,5}));
        }

        @Test
        void uniteTwoKingdomsStart_OnlyOneKingdom_ReturnsFalse() {

            map.board[5][5] = map.empty;

            map.board[5][4] = "BT";
            map.board[5][6] = map.temple;

            assertFalse(leader.uniteTwoKingdomsStart(new int[]{5,5}));
        }

        //-------------------------------------------------
        // leaderErrorCheck()
        //-------------------------------------------------

        @Test
        void leaderErrorCheck_InvalidColor_ReturnsFalse() {

            map.board[5][5] = map.empty;

            assertFalse(leader.leaderErrorCheck(
                    new int[]{5,5},
                    'X'
            ));
        }

        @Test
        void leaderErrorCheck_OccupiedSpace_ReturnsFalse() {

            map.board[5][5] = map.temple;

            assertFalse(leader.leaderErrorCheck(
                    new int[]{5,5},
                    'T'
            ));
        }

        @Test
        void leaderErrorCheck_NotAdjacentToTemple_ReturnsFalse() {

            map.board[5][5] = map.empty;

            assertFalse(leader.leaderErrorCheck(
                    new int[]{5,5},
                    'M'
            ));
        }

        @Test
        void leaderErrorCheck_UnitesKingdoms_ReturnsTrue() {

            map.board[5][5] = map.empty;

            map.board[5][4] = map.temple;
            map.board[5][3] = "BT";

            map.board[5][6] = map.temple;
            map.board[5][7] = "LT";

            assertTrue(leader.uniteTwoKingdomsStart(
                    new int[]{5,5}));
        }

}
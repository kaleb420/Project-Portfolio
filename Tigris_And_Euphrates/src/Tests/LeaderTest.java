package Tests;

import Helpers.Helper;
import Helpers.Search_Algorithms;
import Midgame.Leader;
import Setup.Bag;
import Setup.Map;
import Setup.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

    class LeaderTest {

        Map map;
        Bag bag=new Bag();
        Helper helper;
        Leader leader;
        HashMap<String, Player> players;
        Search_Algorithms searchAlgorithms;

        @BeforeEach
        void setup() {
            map = new Map();
            helper = new Helper(map);

            players = new HashMap<>();
            players.put("Lion", new Player(bag, map, "Lion"));

            leader = new Leader(map, helper, players);
            searchAlgorithms=new Search_Algorithms(map);
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
            ArrayList<int[]> arrayList=new ArrayList<>();
            arrayList.add(new int[]{5,5});

            assertTrue(leader.kingdomDetector(arrayList));
        }

        @Test
        void kingdomDetector_NoLeader_ReturnsFalse() {

            map.board[5][5] = map.temple;
            ArrayList<int[]> arrayList=new ArrayList<>();
            arrayList.add(new int[]{5,5});

            assertFalse(leader.kingdomDetector(arrayList));
        }

        @Test
        void kingdomDetector_FindsLeaderThroughRecursion_ReturnsTrue() {

            map.board[5][5] = map.temple;
            map.board[5][6] = map.temple;
            map.board[5][7] = "LT";
            ArrayList<int[]> arrayList=new ArrayList<>();
            arrayList.add(new int[]{5,7});

            assertTrue(leader.kingdomDetector(arrayList));
        }

        //-------------------------------------------------
        // uniteTwoKingdomsStart()
        //-------------------------------------------------

        @Test
        void uniteTwoKingdomsStart_OneAdjacentTile_ReturnsFalse() {

            map.board[5][5] = "BT";
            map.board[5][6] = map.temple;
            ArrayList<int[]> arrayList=new ArrayList<>();
            arrayList.add(new int[]{5,5});

            assertFalse(leader.uniteTwoKingdomsStart(arrayList));
        }

        @Test
        void uniteTwoKingdomsStart_TwoKingdoms_ReturnsTrue() {

            map.board[5][4] = "BT";
            map.board[5][5] = map.temple;
            map.board[5][7] = map.temple;
            map.board[5][8] = "LT";

            assertTrue(leader.uniteTwoKingdomsStart(searchAlgorithms.getAdjacent(new int[]{5,6})));
        }

        @Test
        void uniteTwoKingdomsStart_OnlyOneKingdom_ReturnsFalse() {

            map.board[5][5] = map.empty;

            map.board[5][4] = "BT";
            map.board[5][6] = map.temple;
            ArrayList<int[]> arrayList=new ArrayList<>();
            arrayList.add(new int[]{5,4});

            assertFalse(leader.uniteTwoKingdomsStart(arrayList));
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

            map.board[5][5] = map.temple;

            map.board[5][4] = map.temple;
            map.board[5][3] = "BT";

            map.board[5][6] = map.temple;
            map.board[5][7] = "LT";

            assertTrue(leader.uniteTwoKingdomsStart(searchAlgorithms.BFSSearchKingdom(new int[]{5, 5})));
        }

        @Test
        void leaderPlacedNextToTempleTrue() {
            map.board[5][5] = map.temple;

            assertTrue(leader.leaderPlacedNextToTemple(new int[]{5, 6}));
        }

        @Test
        void leaderPlacedNextToTempleTreasureTemple() {
            map.board[5][5] = map.templeWithTreasure;

            assertTrue(leader.leaderPlacedNextToTemple(new int[]{5, 6}));
        }

        @Test
        void leaderPlacedNextToTempleFalse() {
            map.board[5][5] = map.market;

            assertFalse(leader.leaderPlacedNextToTemple(new int[]{5, 6}));
        }

        @Test
        void kingdomDetectorTrue() {
            map.board[4][4] = "BT";

            ArrayList<int[]> kingdom = new ArrayList<>();
            kingdom.add(new int[]{4,4});

            assertTrue(leader.kingdomDetector(kingdom));
        }

        @Test
        void kingdomDetectorFalse() {
            map.board[4][4] = map.market;

            ArrayList<int[]> kingdom = new ArrayList<>();
            kingdom.add(new int[]{4,4});

            assertFalse(leader.kingdomDetector(kingdom));
        }

        @Test
        void containsCoordinateTrue() {
            ArrayList<int[]> kingdom = new ArrayList<>();
            kingdom.add(new int[]{1,1});
            kingdom.add(new int[]{2,2});
            kingdom.add(new int[]{3,3});

            assertTrue(leader.containsCoordinate(kingdom, new int[]{2,2}));
        }

        @Test
        void containsCoordinateFalse() {
            ArrayList<int[]> kingdom = new ArrayList<>();
            kingdom.add(new int[]{1,1});
            kingdom.add(new int[]{2,2});

            assertFalse(leader.containsCoordinate(kingdom, new int[]{3,3}));
        }

        @Test
        void uniteTwoKingdomsStartEmpty() {
            assertFalse(leader.uniteTwoKingdomsStart(new ArrayList<>()));
        }

        @Test
        void uniteTwoKingdomsStartOneAdjacent() {
            ArrayList<int[]> adjacent = new ArrayList<>();
            adjacent.add(new int[]{5,5});

            assertFalse(leader.uniteTwoKingdomsStart(adjacent));
        }

        @Test
        void uniteTwoKingdomsStartFalseBecauseOneRegionHasNoLeader() {

            map.board[5][5] = map.temple;
            map.board[5][6] = map.temple;

            map.board[5][4] = "BT";

            ArrayList<int[]> adjacent = new ArrayList<>();
            adjacent.add(new int[]{5,4});

            assertFalse(leader.uniteTwoKingdomsStart(adjacent));
        }

        @Test
        void uniteTwoKingdomsStartTrue() {

            map.board[5][4] = "BT";
            map.board[5][6] = "RT";

            ArrayList<int[]> adjacent = new ArrayList<>();
            adjacent.add(new int[]{5,4});
            adjacent.add(new int[]{5,6});

            assertTrue(leader.uniteTwoKingdomsStart(adjacent));
        }

        @Test
        void isDifferentKingdomLessThanTwoAdjacent() {
            ArrayList<int[]> adjacent = new ArrayList<>();
            adjacent.add(new int[]{5,5});

            assertFalse(leader.isSameKingdom(adjacent));
        }

        @Test
        void isDifferentKingdomSameKingdom() {

            map.board[5][5] = map.temple;
            map.board[5][6] = map.market;

            ArrayList<int[]> adjacent = new ArrayList<>();
            adjacent.add(new int[]{5,5});
            adjacent.add(new int[]{5,6});

            assertTrue(leader.isSameKingdom(adjacent));
        }

        @Test
        void isDifferentKingdomDifferentKingdoms() {

            map.board[5][5] = map.temple;
            map.board[8][8] = map.market;

            ArrayList<int[]> adjacent = new ArrayList<>();
            adjacent.add(new int[]{5,5});
            adjacent.add(new int[]{8,8});

            assertFalse(leader.isSameKingdom(adjacent));
        }

        @Test
        void leaderErrorCheckInvalidColor() {
            assertFalse(leader.leaderErrorCheck(new int[]{5,5}, 'X'));
        }

        @Test
        void leaderErrorCheckOccupiedSpace() {

            map.board[5][5] = map.temple;

            assertFalse(leader.leaderErrorCheck(new int[]{5,5}, 'T'));
        }

        @Test
        void leaderErrorCheckNotNextToTemple() {

            assertFalse(leader.leaderErrorCheck(new int[]{5,5}, 'T'));
        }

        @Test
        void leaderErrorCheckValidPlacement() {

            map.board[5][4] = map.temple;

            assertTrue(leader.leaderErrorCheck(new int[]{5,5}, 'T'));
        }

}
package Tests;

import Helpers.Helper;
import Helpers.Search_Algorithms;
import Setup.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Search_AlgorithmsTest {

    Map map = new Map();
    Helper helper = new Helper(map);
    Search_Algorithms searchAlgorithms =new Search_Algorithms(map);

    @BeforeEach
    void setup() {
        map = new Map();
        searchAlgorithms = new Search_Algorithms(map);
    }

    //-----------------------------------------
    // clearVisited()
    //-----------------------------------------

    @Test
    void clearVisited_EmptiesVisitedSet() {
        searchAlgorithms.visited.add("5 5");
        searchAlgorithms.visited.add("4 4");

        searchAlgorithms.clearVisited();

        assertTrue(searchAlgorithms.visited.isEmpty());
    }

    //-----------------------------------------
    // inBounds()
    //-----------------------------------------

    @Test
    void inBounds_ValidLocation_ReturnsTrue() {
        assertTrue(searchAlgorithms.inBounds(new int[]{5, 5}));
    }

    @Test
    void inBounds_NegativeRow_ReturnsFalse() {
        assertFalse(searchAlgorithms.inBounds(new int[]{-1, 5}));
    }

    @Test
    void inBounds_NegativeColumn_ReturnsFalse() {
        assertFalse(searchAlgorithms.inBounds(new int[]{5, -1}));
    }

    @Test
    void inBounds_RowTooLarge_ReturnsFalse() {
        assertFalse(searchAlgorithms.inBounds(new int[]{map.board.length, 5}));
    }

    @Test
    void inBounds_ColumnTooLarge_ReturnsFalse() {
        assertFalse(searchAlgorithms.inBounds(new int[]{5, map.board[0].length}));
    }

    //-----------------------------------------
    // getUp()
    //-----------------------------------------

    @Test
    void getUp_ValidTile_ReturnsCoordinates() {

        map.board[6][5] = map.temple;

        assertArrayEquals(
                new int[]{6,5},
                searchAlgorithms.getUp(new int[]{5,5})
        );
    }

    @Test
    void getUp_EmptyTile_ReturnsNull() {

        map.board[6][5] = map.empty;

        assertNull(searchAlgorithms.getUp(new int[]{5,5}));
    }

    //-----------------------------------------
    // getDown()
    //-----------------------------------------

    @Test
    void getDown_ValidTile_ReturnsCoordinates() {

        map.board[4][5] = map.temple;

        assertArrayEquals(
                new int[]{4,5},
                searchAlgorithms.getDown(new int[]{5,5})
        );
    }

    @Test
    void getDown_ConflictTile_ReturnsNull() {

        map.board[4][5] = map.conflict;

        assertNull(searchAlgorithms.getDown(new int[]{5,5}));
    }

    //-----------------------------------------
    // getRight()
    //-----------------------------------------

    @Test
    void getRight_ValidTile_ReturnsCoordinates() {

        map.board[5][6] = map.market;

        assertArrayEquals(
                new int[]{5,6},
                searchAlgorithms.getRight(new int[]{5,5})
        );
    }

    @Test
    void getRight_River_ReturnsNull() {

        map.board[5][6] = map.river;

        assertNull(searchAlgorithms.getRight(new int[]{5,5}));
    }

    //-----------------------------------------
    // getLeft()
    //-----------------------------------------

    @Test
    void getLeft_ValidTile_ReturnsCoordinates() {

        map.board[5][4] = map.market;

        assertArrayEquals(
                new int[]{5,4},
                searchAlgorithms.getLeft(new int[]{5,5})
        );
    }

    @Test
    void getLeft_Catastrophe_ReturnsNull() {

        map.board[5][4] = map.catastrophe;

        assertNull(searchAlgorithms.getLeft(new int[]{5,5}));
    }

    //-----------------------------------------
    // getAdjacent()
    //-----------------------------------------

    @Test
    void getAdjacent_AllDirectionsValid() {

        map.board[6][5] = map.temple;
        map.board[4][5] = map.temple;
        map.board[5][6] = map.temple;
        map.board[5][4] = map.temple;

        ArrayList<int[]> adjacent = searchAlgorithms.getAdjacent(new int[]{5,5});

        assertEquals(4, adjacent.size());

        assertArrayEquals(new int[]{6,5}, adjacent.get(0));
        assertArrayEquals(new int[]{4,5}, adjacent.get(1));
        assertArrayEquals(new int[]{5,6}, adjacent.get(2));
        assertArrayEquals(new int[]{5,4}, adjacent.get(3));
    }

    @Test
    void getAdjacent_NoAdjacentTiles() {

        map.board[6][5] = map.empty;
        map.board[4][5] = map.empty;
        map.board[5][6] = map.empty;
        map.board[5][4] = map.empty;

        ArrayList<int[]> adjacent = searchAlgorithms.getAdjacent(new int[]{5,5});

        assertEquals(0, adjacent.size());
    }

    //-----------------------------------------
    // BFSSearchKingdom()
    //-----------------------------------------

    @Test
    void BFSSearchKingdom_SingleTile() {

        map.board[5][5] = map.temple;

        ArrayList<int[]> kingdom =
                searchAlgorithms.BFSSearchKingdom(new int[]{5,5});

        assertEquals(1, kingdom.size());
        assertArrayEquals(new int[]{5,5}, kingdom.get(0));
    }

    @Test
    void BFSSearchKingdom_LineOfThreeTiles() {

        map.board[5][5] = map.temple;
        map.board[5][6] = map.temple;
        map.board[5][7] = map.temple;

        ArrayList<int[]> kingdom =
                searchAlgorithms.BFSSearchKingdom(new int[]{5,5});

        assertEquals(3, kingdom.size());
    }

    @Test
    void BFSSearchKingdom_SquareKingdom() {

        map.board[5][5] = map.temple;
        map.board[5][6] = map.temple;
        map.board[6][5] = map.temple;
        map.board[6][6] = map.temple;

        ArrayList<int[]> kingdom =
                searchAlgorithms.BFSSearchKingdom(new int[]{5,5});

        assertEquals(4, kingdom.size());
    }
    // --------------------------
    // inBounds()
    // --------------------------

    @Test
    void inBounds_ValidLocation() {
        assertTrue(searchAlgorithms.inBounds(new int[]{0,0}));
    }

    @Test
    void inBounds_NegativeRow() {
        assertFalse(searchAlgorithms.inBounds(new int[]{-1,0}));
    }

    @Test
    void inBounds_NegativeColumn() {
        assertFalse(searchAlgorithms.inBounds(new int[]{0,-1}));
    }

    @Test
    void inBounds_RowTooLarge() {
        assertFalse(searchAlgorithms.inBounds(new int[]{map.board.length + 1,0}));
    }

    @Test
    void inBounds_ColumnTooLarge() {
        assertFalse(searchAlgorithms.inBounds(new int[]{0,map.board[0].length + 1}));
    }

    // --------------------------
    // adjacency()
    // --------------------------

    /*@Test
    void adjacency_NoNeighbors() {

        map.board[3][3] = "F";

        ArrayList<int[]> adj = searchAlgorithms.adjacency(new int[]{3,3});

        for (int[] location : adj) {
            assertArrayEquals(new int[]{0,0}, location);
        }
    }

    @Test
    void adjacency_AllNeighborsPresent() {

        map.board[3][3] = "F";

        map.board[4][3] = "F";
        map.board[2][3] = "F";
        map.board[3][4] = "F";
        map.board[3][2] = "F";

        ArrayList<int[]> adj = searchAlgorithms.adjacency(new int[]{3,3});

        assertArrayEquals(new int[]{4,3}, adj.get(0)); // up
        assertArrayEquals(new int[]{2,3}, adj.get(1)); // down
        assertArrayEquals(new int[]{3,4}, adj.get(2)); // right
        assertArrayEquals(new int[]{3,2}, adj.get(3)); // left
    }

    @Test
    void adjacency_IgnoresEmptyTiles() {

        map.board[3][3] = "F";
        map.board[4][3] = "-";

        ArrayList<int[]> adj = searchAlgorithms.adjacency(new int[]{3,3});

        assertTrue(adj.isEmpty());
    }

    @Test
    void adjacency_IgnoresRiverTiles() {

        map.board[3][3] = "F";
        map.board[4][3] = "R";

        ArrayList<int[]> adj = searchAlgorithms.adjacency(new int[]{3,3});

        assertTrue(adj.isEmpty());
    }

    @Test
    void adjacency_OnlyOneNeighbor() {

        map.board[3][3] = "F";
        map.board[3][4] = "W";

        ArrayList<int[]> adj = searchAlgorithms.adjacency(new int[]{3,3});

        assertArrayEquals(new int[]{3,4}, adj.get(0));
        assertEquals(1, adj.size());
    }

    @Test
    void adjacency_OnBoardEdge() {

        map.board[0][0] = "F";
        map.board[0][1] = "F";
        map.board[1][0] = "F";
        map.printMap();
        ArrayList<int[]> adj = searchAlgorithms.adjacency(new int[]{0,0});

        assertArrayEquals(new int[]{1,0}, adj.get(0));
        assertArrayEquals(new int[]{0,1}, adj.get(1));
    }
    @Test
    void searchKingdomLeaders_leaderAdjacent() {

        map.board[5][5] = "T";
        map.board[5][6] = "BR";

        assertEquals('B', searchAlgorithms.searchKingdomLeaders(map, new int[]{5,5}, 'R').get()
        );
    }

    @Test
    void searchKingdomLeaders_leaderTwoTilesAway() {

        map.board[5][5] = "T";
        map.board[5][6] = "T";
        map.board[5][7] = "BR";

        assertEquals('B', searchAlgorithms.searchKingdomLeaders(map, new int[]{5,5}, 'R').get());
    }

    @Test
    void searchKingdomLeaders_longPath() {

        map.board[5][5] = "T";
        map.board[5][6] = "T";
        map.board[5][7] = "T";
        map.board[4][7] = "T";
        map.board[3][7] = "BR";
        assertEquals('B', searchAlgorithms.searchKingdomLeaders(map, new int[]{5,5}, 'R').get()
        );
    }

    @Test
    void searchKingdomLeaders_searchesAllBranches() {

        map.board[5][5] = "T";
        map.board[5][6] = "T";
        map.board[5][7] = "T";
        map.board[4][6] = "BR";

        assertEquals(
                'B',
                searchAlgorithms.searchKingdomLeaders(map, new int[]{5,5}, 'R').get()
        );
    }

    @Test
    void searchKingdomLeaders_wrongLeaderColor() {

        map.board[5][5] = "T";
        map.board[5][6] = "BB";

        assertTrue(
                searchAlgorithms.searchKingdomLeaders(map, new int[]{5,5}, 'R').isEmpty()
        );
    }

    @Test
    void searchKingdomLeaders_findsLion() {

        map.board[5][5] = "B";
        map.board[5][6] = "LB";

        assertEquals(
                'L',
                searchAlgorithms.searchKingdomLeaders(map, new int[]{5,5}, 'B').get()
        );
    }

    @Test
    void searchKingdomLeaders_findsArcher() {

        map.board[5][5] = "G";
        map.board[5][6] = "AG";

        assertEquals('A', searchAlgorithms.searchKingdomLeaders(map, new int[]{5,5}, 'G').get()
        );
    }

    @Test
    void searchKingdomLeaders_findsPot() {

        map.board[5][5] = "K";
        map.board[5][6] = "PK";

        assertEquals(
                'P',
                searchAlgorithms.searchKingdomLeaders(map, new int[]{5,5}, 'K').get()
        );
    }

    @Test
    void searchKingdomLeaders_noLeaderAnywhere() {

        map.board[5][5] = "T";
        map.board[5][6] = "T";
        map.board[5][7] = "T";

        assertTrue(
                searchAlgorithms.searchKingdomLeaders(map, new int[]{5,5}, 'R').isEmpty()
        );
    }

    @Test
    void searchKingdomLeaders_handlesCycle() {

        map.board[5][5] = "T";
        map.board[5][6] = "T";
        map.board[6][5] = "T";
        map.board[6][6] = "BR";

        assertEquals(
                'B',
                searchAlgorithms.searchKingdomLeaders(map, new int[]{5,5}, 'R').get()
        );
    }

    @Test
    void searchKingdomLeaders_findsLionLeader() {

        map.board[4][4] = "LB";

        assertEquals('L',
                searchAlgorithms.searchKingdomLeaders(map, new int[]{4,4}, 'B').get());
    }

    @Test
    void searchKingdomLeaders_returnsEmptyWhenNoLeaderPresent() {

        map.board[5][5] = "T";

        assertTrue(
                searchAlgorithms.searchKingdomLeaders(map, new int[]{5,5}, 'R').isEmpty()
        );
    }

    @Test
    void searchKingdomLeaders_findsBullLeader() {

        map.board[3][3] = "BR";

        assertEquals('B',
                searchAlgorithms.searchKingdomLeaders(map, new int[]{3,3}, 'R').get());
    }

    @Test
    void goesThroughLeaders(){
        map.board[5][5]="T";
        map.board[5][6]="LM";
        map.board[5][7]="T";
        map.board[5][8]="BT";

        assertEquals('B', searchAlgorithms.searchKingdomLeaders(map, new int[]{5, 5}, 'T').get());
    } */
}
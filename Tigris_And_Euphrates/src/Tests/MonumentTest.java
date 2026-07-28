package Tests;

import Helpers.Helper;
import Midgame.Monument;
import Setup.Bag;
import Setup.Map;
import Setup.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MonumentTest {

    Map map;
    Helper helper;
    Monument monument;
    HashMap<String, Player> players;
    Bag bag=new Bag();

    Player bulls;
    Player lions;
    Player archers;
    Player pots;

    @BeforeEach
    void setup() {

        map = new Map();
        helper = new Helper(map);

        players = new HashMap<>();

        bulls = new Player(bag, map, map.bulls);
        lions = new Player(bag, map, map.lions);
        archers = new Player(bag, map, map.archers);
        pots = new Player(bag, map, map.pots);

        players.put("Bulls", bulls);
        players.put("Lions", lions);
        players.put("Archers", archers);
        players.put("Pots", pots);

        monument = new Monument(map, players, helper);
    }

    //---------------------------------------------------
    // incrementCubes()
    //---------------------------------------------------

    @Test
    void incrementCubes_Red() {

        monument.incrementCubes("Bulls", map.temple.charAt(0));

        assertEquals(1, bulls.cubes.redCubes);
    }

    @Test
    void incrementCubes_Green() {

        monument.incrementCubes("Lions", map.market.charAt(0));

        assertEquals(1, lions.cubes.greenCubes);
    }

    @Test
    void incrementCubes_Blue() {

        monument.incrementCubes("Archers", map.farm.charAt(0));

        assertEquals(1, archers.cubes.blueCubes);
    }

    @Test
    void incrementCubes_Black() {

        monument.incrementCubes("Pots", map.settlement.charAt(0));

        assertEquals(1, pots.cubes.blackCubes);
    }

    //---------------------------------------------------
    // searchKingdomLeaders()
    //---------------------------------------------------

    @Test
    void searchKingdomLeaders_MatchingLeaderGetsCube() {

        map.monuments.put("ST", new int[]{5, 5});
        map.board[5][5] = "ST ";
        map.board[5][6] = "S";
        map.board[5][7] = "BT";

        monument.searchKingdomLeaders(
                new int[]{5,5},
                "ST"
        );

        assertEquals(1, bulls.cubes.redCubes);
    }

    @Test
    void searchKingdomLeaders_NonMatchingLeaderGetsNothing() {

        map.board[5][5] = "ST";
        map.board[5][6]= "BF";

        monument.searchKingdomLeaders(
                new int[]{5,5},
                "ST"
        );

        assertEquals(0, bulls.cubes.blueCubes);
    }

    //---------------------------------------------------
    // placeMonument()
    //---------------------------------------------------

    @Test
    void placeMonument_UpdatesBoard() {

        String input = "SM\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        helper = new Helper(map);
        monument = new Monument(map, players, helper);
        map.board[5][5]="M";
        map.board[5][6]="M";
        map.board[6][5]="M";
        map.board[6][6]="M";

        int[][] square = {
                {5,5},
                {5,6},
                {6,5},
                {6,6}
        };

        monument.placeMonument(square);

        assertEquals("SM ", map.board[5][5]);
        assertEquals("SM ", map.board[5][6]);
        assertEquals("SM ", map.board[6][5]);
        assertEquals("SM ", map.board[6][6]);
    }

    @Test
    void placeMonument_UpdatesBoardFails() {

        String input = "SF\n" + // doesn't work because not the same color as the tiles being flipped
                "ST\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        helper = new Helper(map);
        monument = new Monument(map, players, helper);
        map.board[5][5]="T";
        map.board[5][6]="T";
        map.board[6][5]="T";
        map.board[6][6]="T";

        int[][] square = {
                {5,5},
                {5,6},
                {6,5},
                {6,6}
        };

        monument.placeMonument(square);

        assertEquals("ST ", map.board[5][5]);
        assertEquals("ST ", map.board[5][6]);
        assertEquals("ST ", map.board[6][5]);
        assertEquals("ST ", map.board[6][6]);
    }

    @Test
    void placeMonument_RemovesAvailableMonument() {

        String input = "SM\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        helper = new Helper(map);
        monument = new Monument(map, players, helper);

        map.board[1][1]="M";
        map.board[1][2]="M";
        map.board[2][1]="M";
        map.board[2][2]="M";


        monument.placeMonument(new int[][]{
                {1,1},
                {1,2},
                {2,1},
                {2,2}
        });

        assertTrue(map.monuments.containsKey("SM"));
        assertArrayEquals(new int[]{1, 1}, map.monuments.get("SM"));
    }

    //---------------------------------------------------
    // monumentCheck()
    //---------------------------------------------------

    @Test
    void monumentCheck_TopLeftSquarePlacesMonument() {

        String input = "ST\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        helper = new Helper(map);
        monument = new Monument(map, players, helper);

        map.board[5][5] = "T";
        map.board[5][6] = "T";
        map.board[6][5] = "T";
        map.board[6][6] = "T";

        monument.monumentCheck(new int[]{5,5});

        assertEquals("ST ", map.board[5][5]);
    }

    @Test
    void monumentCheck_NoSquare_DoesNothing() {

        map.board[5][5] = "T";
        map.board[5][6] = "T";
        map.board[6][5] = "M";
        map.board[6][6] = "T";

        monument.monumentCheck(new int[]{5,5});

        assertEquals("T", map.board[5][5]);
    }

    //---------------------------------------------------
    // endOfTurn()
    //---------------------------------------------------

    @Test
    void endOfTurn_MonumentAwardsCube() {

        map.monuments.put("TM", new int[]{5, 5});

        map.board[5][5] = "TM";
        map.board[5][6] = "BT";

        monument.endOfTurn();

        assertEquals(1, bulls.cubes.redCubes);
    }

    @Test
    void endOfTurn_CheckedMonumentIgnored() {

        map.monuments.put("MT", new int[]{5, 5});

        map.board[5][5] = "MT";
        map.board[5][6] = "BT";

        monument.endOfTurn();

        assertEquals(1, bulls.cubes.redCubes);
    }
}
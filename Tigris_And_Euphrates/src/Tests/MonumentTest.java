package Midgame;

import Helpers.Helper;
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

        map.board[5][5] = "BT";

        monument.searchKingdomLeaders(
                new int[]{5,5},
                "TM"
        );

        assertEquals(1, bulls.cubes.redCubes);
    }

    @Test
    void searchKingdomLeaders_NonMatchingLeaderGetsNothing() {

        map.board[5][5] = "BF";

        monument.searchKingdomLeaders(
                new int[]{5,5},
                "TM"
        );

        assertEquals(0, bulls.cubes.redCubes);
    }

    //---------------------------------------------------
    // placeMonument()
    //---------------------------------------------------

    @Test
    void placeMonument_UpdatesBoard() {

        map.availableMonuments.add("TMF");

        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        helper = new Helper(map);
        monument = new Monument(map, players, helper);

        int[][] square = {
                {5,5},
                {5,6},
                {6,5},
                {6,6}
        };

        monument.placeMonument(square);

        assertEquals("TMF", map.board[5][5]);
        assertEquals("TMF", map.board[5][6]);
        assertEquals("TMF", map.board[6][5]);
        assertEquals("TMF", map.board[6][6]);
    }

    @Test
    void placeMonument_RemovesAvailableMonument() {

        map.availableMonuments.add("TMF");

        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        helper = new Helper(map);
        monument = new Monument(map, players, helper);

        monument.placeMonument(new int[][]{
                {1,1},
                {1,2},
                {2,1},
                {2,2}
        });

        assertTrue(map.availableMonuments.isEmpty());
        assertTrue(map.unavailableMonuments.contains("TMF"));
    }

    //---------------------------------------------------
    // monumentCheck()
    //---------------------------------------------------

    @Test
    void monumentCheck_TopLeftSquarePlacesMonument() {

        map.availableMonuments.add("TMF");

        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        helper = new Helper(map);
        monument = new Monument(map, players, helper);

        map.board[5][5] = "T";
        map.board[5][6] = "T";
        map.board[6][5] = "T";
        map.board[6][6] = "T";

        monument.monumentCheck(new int[]{5,5});

        assertEquals("TMF", map.board[5][5]);
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

        map.unavailableMonuments.add("TM");

        monument.checkedMonuments = new java.util.ArrayList<>();

        map.board[5][5] = "TM";
        map.board[5][6] = "BT";

        monument.endOfTurn();

        assertEquals(1, bulls.cubes.redCubes);
    }

    @Test
    void endOfTurn_CheckedMonumentIgnored() {

        map.unavailableMonuments.add("TM");

        monument.checkedMonuments = new java.util.ArrayList<>();
        monument.checkedMonuments.add("TM");

        map.board[5][5] = "TM";
        map.board[5][6] = "BT";

        monument.endOfTurn();

        assertEquals(0, bulls.cubes.redCubes);
    }
}
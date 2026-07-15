package Tests;

import Helpers.Helper;
import Midgame.Place_Tile;
import Setup.Bag;
import Setup.Map;
import Setup.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class Place_TileTest {

    Map map;
    Bag bag=new Bag();
    Helper helper;
    HashMap<String, Player> players;
    Player player;
    Place_Tile placeTile;

    @BeforeEach
    void setup() {
        map = new Map();
        helper = new Helper(map);

        players = new HashMap<>();
        player = new Player(bag, map, "Lion");

        players.put("Lion", player);
        player.setPlayers(players);

        placeTile = new Place_Tile(map, helper, players);
    }

    @Test
    void riverCheck_RiverFarm_ReturnsTrue() {
        map.board[5][5] = map.river;

        assertTrue(placeTile.riverCheck(
                new int[]{5,5},
                map.farm
        ));
    }

    @Test
    void riverCheck_RiverTemple_ReturnsFalse() {
        map.board[5][5] = map.river;

        assertFalse(placeTile.riverCheck(
                new int[]{5,5},
                map.temple
        ));
    }

    @Test
    void riverCheck_NormalTemple_ReturnsTrue() {
        map.board[5][5] = map.empty;

        assertTrue(placeTile.riverCheck(
                new int[]{5,5},
                map.temple
        ));
    }

    @Test
    void riverCheck_NormalFarm_ReturnsFalse() {
        map.board[5][5] = map.empty;

        assertFalse(placeTile.riverCheck(
                new int[]{5,5},
                map.farm
        ));
    }

    @Test
    void riverCheck_MarketOnNormalTile_ReturnsTrue() {
        map.board[3][4] = map.empty;

        assertTrue(placeTile.riverCheck(
                new int[]{3,4},
                map.market
        ));
    }

    @Test
    void riverCheck_SettlementOnRiver_ReturnsFalse() {
        map.board[2][2] = map.river;

        assertFalse(placeTile.riverCheck(
                new int[]{2,2},
                map.settlement
        ));
    }

    @Test
    void placeTile_PlacesTemple() {

        String input =
                "A1\n" +   // location
                        "1\n";     // choose first tile

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        helper = new Helper(map);

        player.pieces = new ArrayList<>();
        player.pieces.add(map.temple);

        placeTile = new Place_Tile(map, helper, players);

        placeTile.placeTile(player);

        assertEquals(map.temple, map.board[1][0]);
    }

    @Test
    void placeTile_PlacesMarket() {

        String input =
                "A1\n" + // location
                        "1\n"; // choose first tile

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        helper = new Helper(map);

        player.pieces.clear();
        player.pieces.add(map.market);

        placeTile = new Place_Tile(map, helper, players);

        placeTile.placeTile(player);

        assertEquals(map.market, map.board[1][0]);
    }

    @Test
    void placeTile_PlacesSettlement() {

        String input =
                "A1\n" +
                        "1\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        helper = new Helper(map);

        player.pieces.clear();
        player.pieces.add(map.settlement);

        placeTile = new Place_Tile(map, helper, players);

        placeTile.placeTile(player);

        assertEquals(map.settlement, map.board[1][0]);
    }

    @Test
    void placeTile_PlacesFarmOnRiver() {

        map.board[0][1] = map.river;

        String input =
                "A1\n" +
                        "1\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        helper = new Helper(map);

        player.pieces.clear();
        player.pieces.add(map.farm);

        placeTile = new Place_Tile(map, helper, players);

        placeTile.placeTile(player);

        assertEquals(map.farm, map.board[1][0]);
    }

    @Test
    void riverCheck_FarmOnNormalTile_ReturnsFalse() {

        map.board[4][4] = map.empty;

        assertFalse(placeTile.riverCheck(
                new int[]{4,4},
                map.farm
        ));
    }

    @Test
    void riverCheck_TempleOnRiver_ReturnsFalse() {

        map.board[4][4] = map.river;

        assertFalse(placeTile.riverCheck(
                new int[]{4,4},
                map.temple
        ));
    }
}
package Tests;

import Helpers.Helper;
import Midgame.Adjust_Map;
import Setup.Bag;
import Setup.Map;
import Setup.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class Adjust_MapTest {

    Map map=new Map();
    Helper helper=new Helper(map);
    Bag bag=new Bag();
    Player player=new Player(bag, map, "Lion");
    HashMap<String, Player> players;
    Adjust_Map adjustMap=new Adjust_Map(map, players, helper);

    @BeforeEach
    void setUp() {
        map = new Map();

        // Clear board
        for (int r = 0; r < map.board.length; r++) {
            for (int c = 0; c < map.board[r].length; c++) {
                map.board[r][c] = "-";
            }
        }

        player = new Player(bag, map, "Lion");

        player.redLocation = new int[]{-1, -1};
        player.blueLocation = new int[]{-1, -1};
        player.greenLocation = new int[]{-1, -1};
        player.blackLocation = new int[]{-1, -1};

        HashMap<String, Player> players = new HashMap<>();
        players.put("Lion", player);

        adjustMap = new Adjust_Map(map, players, helper);
    }

    @Test
    void placePiece() {
        String location="A0";
        String tile="Temple";
        adjustMap.placePiece(helper.inputToLocation(location), tile);
        assertEquals("T", map.board[0][0]);
    }

    @Test
    void removeLeader() {
        int[] location=helper.inputToLocation("A0");
        adjustMap.placeLeader(player, 'T', location);
        assertEquals(0, player.redLocation[0]);
        assertEquals(0, player.redLocation[1]);
        adjustMap.removeLeader(player, 'T');
        assertEquals("-", map.board[0][0]);
    }

    @Test
    void updateLeaderPosition() {
        int[] location=new int[2]; // all variables set to 0
        location[0]=1;
        location[1]=1;
        adjustMap.updateLeaderPosition(player, 'T', location);
        assertEquals(1, player.redLocation[0]);
        assertEquals(1, player.redLocation[1]);
    }

    @Test
    void placeLeader() {
        int[] location=helper.inputToLocation("A0");
        char color='T';
        adjustMap.placeLeader(player, color, location);
        assertEquals(0, player.redLocation[0]);
        assertEquals(0, player.redLocation[1]);
        assertEquals("LT", map.board[0][0]);
    }

    // ---------------------------------------------------------
    // placePiece()
    // ---------------------------------------------------------

    @Test
    void placePiece_placesTempleTile() {
        int[] location = {2, 3};

        adjustMap.placePiece(location, "Temple");

        assertEquals("T", map.board[2][3]);
    }

    @Test
    void placePiece_placesFarmTile() {
        int[] location = {5, 7};

        adjustMap.placePiece(location, "Farm");

        assertEquals("F", map.board[5][7]);
    }

    // ---------------------------------------------------------
    // moveLeader()
    // ---------------------------------------------------------

    @Test
    void removeLeader_removesExistingRedLeader() {
        player.redLocation = new int[]{1, 2};
        map.board[1][2] = "ST";

        adjustMap.removeLeader(player, 'T');

        assertEquals("-", map.board[1][2]);
    }

    @Test
    void removeLeader_removesExistingBlueLeader() {
        player.blueLocation = new int[]{2, 4};
        map.board[2][4] = "SF";

        adjustMap.removeLeader(player, 'F');

        assertEquals("-", map.board[2][4]);
    }

    @Test
    void removeLeader_doesNothingIfLeaderNotPlaced() {
        adjustMap.removeLeader(player, 'M');

        assertEquals("-", map.board[0][0]);
    }

    // ---------------------------------------------------------
    // updateLeaderPosition()
    // ---------------------------------------------------------

    @Test
    void updateLeaderPosition_updatesRedLocation() {
        adjustMap.updateLeaderPosition(player, 'T', new int[]{4, 5});

        assertArrayEquals(new int[]{4, 5}, player.redLocation);
    }

    @Test
    void updateLeaderPosition_updatesBlueLocation() {
        adjustMap.updateLeaderPosition(player, 'F', new int[]{3, 7});

        assertArrayEquals(new int[]{3, 7}, player.blueLocation);
    }

    @Test
    void updateLeaderPosition_updatesGreenLocation() {
        adjustMap.updateLeaderPosition(player, 'M', new int[]{6, 1});

        assertArrayEquals(new int[]{6, 1}, player.greenLocation);
    }

    @Test
    void updateLeaderPosition_updatesBlackLocation() {
        adjustMap.updateLeaderPosition(player, 'S', new int[]{8, 9});

        assertArrayEquals(new int[]{8, 9}, player.blackLocation);
    }

    // ---------------------------------------------------------
    // placeLeader()
    // ---------------------------------------------------------

    @Test
    void placeLeader_placesNewRedLeader() {
        int[] location = {2, 2};

        adjustMap.placeLeader(player, 'T', location);

        assertEquals("LT", map.board[2][2]);
        assertArrayEquals(location, player.redLocation);
    }

    @Test
    void placeLeader_movesExistingLeader() {
        player.redLocation = new int[]{1, 1};
        map.board[1][1] = "LT";

        int[] newLocation = {4, 4};

        adjustMap.placeLeader(player, 'T', newLocation);

        assertEquals("-", map.board[1][1]);
        assertEquals("LT", map.board[4][4]);
        assertArrayEquals(newLocation, player.redLocation);
    }

    @Test
    void placeLeader_placesBlueLeader() {
        int[] location = {7, 6};

        adjustMap.placeLeader(player, 'F', location);

        assertEquals("LF", map.board[7][6]);
        assertArrayEquals(location, player.blueLocation);
    }

    @Test
    void placeLeader_placesGreenLeader() {
        int[] location = {5, 3};

        adjustMap.placeLeader(player, 'M', location);

        assertEquals("LM", map.board[5][3]);
        assertArrayEquals(location, player.greenLocation);
    }

    @Test
    void placeLeader_placesBlackLeader() {
        int[] location = {8, 8};

        adjustMap.placeLeader(player, 'S', location);

        assertEquals("LS", map.board[8][8]); // S + B (Black)
        assertArrayEquals(location, player.blackLocation);
    }
}
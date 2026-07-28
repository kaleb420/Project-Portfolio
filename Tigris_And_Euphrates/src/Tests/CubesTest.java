package Tests;

import Midgame.Cubes;
import Setup.Bag;
import Setup.Map;
import Setup.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CubesTest {

    Map map=new Map();
    Cubes cubes;
    Bag bag=new Bag();
    HashMap<String, Player> players;

    @BeforeEach
    void setup() {

        players = new HashMap<>();

        Player bulls = new Player(bag, map, map.bulls);

        Player lions = new Player(bag, map, map.lions);

        Player archers = new Player(bag, map, map.archers);

        Player pots = new Player(bag, map, map.pots);

        players.put(map.bulls, bulls);
        players.put(map.lions, lions);
        players.put(map.archers, archers);
        players.put(map.pots, pots);

        bulls.setPlayers(players);
        lions.setPlayers(players);
        archers.setPlayers(players);
        pots.setPlayers(players);
        cubes=new Cubes(map, players);
    }

    @Test
    void getRedCube_incrementsBullPlayer() {

        map.board[2][2] = "BT";

        cubes.getRedCube(map, new int[]{2,2});

        assertEquals(1, players.get(map.bulls).cubes.redCubes);
    }

    @Test
    void getBlueCube_incrementsLionPlayer() {

        map.board[2][2] = "LF";

        cubes.getBlueCube(map, new int[]{2,2});

        assertEquals(1, players.get(map.lions).cubes.blueCubes);
    }

    @Test
    void getGreenCube_incrementsArcherPlayer() {

        map.board[2][2] = "AM";

        cubes.getGreenCube(map, new int[]{2,2});

        assertEquals(1, players.get(map.archers).cubes.greenCubes);
    }

    @Test
    void getBlackCube_incrementsPotPlayer() {

        map.board[2][2] = "PS";

        cubes.getBlackCube(map, new int[]{2,2});

        assertEquals(1, players.get(map.pots).cubes.blackCubes);
    }

    @Test
    void getRedCube_doesNothingWhenLeaderMissing() {

        map.board[2][2] = "T";

        cubes.getRedCube(map, new int[]{2,2});

        assertEquals(0, players.get(map.bulls).cubes.redCubes);
        assertEquals(0, players.get(map.lions).cubes.redCubes);
        assertEquals(0, players.get(map.archers).cubes.redCubes);
        assertEquals(0, players.get(map.pots).cubes.redCubes);
    }
}
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

        Player bulls = new Player(bag, map, "Bulls");

        Player lions = new Player(bag, map, "Lions");

        Player archers = new Player(bag, map, "Archers");

        Player pots = new Player(bag, map, "Pots");

        players.put("Bulls", bulls);
        players.put("Lions", lions);
        players.put("Archers", archers);
        players.put("Pots", pots);

        bulls.setPlayers(players);
        lions.setPlayers(players);
        archers.setPlayers(players);
        pots.setPlayers(players);
        cubes=new Cubes(map, players);
    }

    @Test
    void getRedCube_incrementsBullPlayer() {

        map.board[2][2] = "BR";

        cubes.getRedCube(map, new int[]{2,2});

        assertEquals(1, players.get("Bulls").cubes.redCubes);
    }

    @Test
    void getBlueCube_incrementsLionPlayer() {

        map.board[2][2] = "LB";

        cubes.getBlueCube(map, new int[]{2,2});

        assertEquals(1, players.get("Lions").cubes.blueCubes);
    }

    @Test
    void getGreenCube_incrementsArcherPlayer() {

        map.board[2][2] = "AG";

        cubes.getGreenCube(map, new int[]{2,2});

        assertEquals(1, players.get("Archers").cubes.greenCubes);
    }

    @Test
    void getBlackCube_incrementsPotPlayer() {

        map.board[2][2] = "PK";

        cubes.getBlackCube(map, new int[]{2,2});

        assertEquals(1, players.get("Pots").cubes.blackCubes);
    }

    @Test
    void getRedCube_doesNothingWhenLeaderMissing() {

        map.board[2][2] = "T";

        cubes.getRedCube(map, new int[]{2,2});

        assertEquals(0, players.get("Bulls").cubes.redCubes);
        assertEquals(0, players.get("Lions").cubes.redCubes);
        assertEquals(0, players.get("Archers").cubes.redCubes);
        assertEquals(0, players.get("Pots").cubes.redCubes);
    }
}
package Tests;

import Helpers.Helper;
import Setup.Map;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HelperTest {

    Map map = new Map();
    Helper helper = new Helper(map);

    @Test
    void inputToLocation_ValidInput() {
        int[] result = helper.inputToLocation("A0");

        assertArrayEquals(new int[]{0, 0}, result);
    }

    @Test
    void inputToLocation_MiddleBoard() {
        int[] result = helper.inputToLocation("C5");
        assertEquals(5, result[0]);
        assertEquals(2, result[1]);
    }

    @Test
    void inputToLocation_InvalidString() {
        int[] result = helper.inputToLocation("");

        assertEquals(-1, result[0]);
    }

    @Test
    void inputToLocation_OneCharacter() {
        int[] result = helper.inputToLocation("A");

        assertEquals(-1, result[0]);
    }

    @Test
    void inputToLocationB0() {
        int[] result = helper.inputToLocation("B0");
        assertEquals(0, result[0]);
        assertEquals(1, result[1]);
        assertEquals("-", map.board[result[0]][result[1]]);
    }

}
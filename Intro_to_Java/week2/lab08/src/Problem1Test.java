import static org.junit.jupiter.api.Assertions.*;

class Problem1Test {

    @org.junit.jupiter.api.Test
    void repeatStrings() {
        assertArrayEquals(new String[]{"CANVASCANVAS", "Plain", "RowRowRow"}, Problem1.repeatStrings(new String[]{"CANVAS", "Plain", "Row"}, new int[]{2,1,3}));
        assertArrayEquals(new String[]{"CANVAS", "Plain", ""}, Problem1.repeatStrings(new String[]{"CANVAS", "Plain", ""}, new int[]{1,1,2}));
        assertArrayEquals(new String[]{""}, Problem1.repeatStrings(new String[]{""}, new int[]{1}));
    }
}
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem2Test {

    @Test
    void median() {
        assertEquals(5, Problem2.median(new int[]{1, 2, 3, 4, 5}, new int[]{6,7,8,9,10}));
        assertEquals(8, Problem2.median(new int[]{}, new int[]{6,7,8,9,10}));
        assertEquals(3, Problem2.median(new int[]{1, 2, 3, 4, 5}, new int[]{}));
        assertEquals(0, Problem2.median(new int[]{}, new int[]{}));
        assertEquals(6, Problem2.median(new int[]{1,2,3,4}, new int[]{8,9,10,11}));
        assertEquals(-6, Problem2.median(new int[]{-1,-2,-3,-4}, new int[]{-8,-9,-10,-11}));
        assertEquals(5, Problem2.median(new int[]{1,3,5,7}, new int[]{2,4,6,8,10}));
    }
}
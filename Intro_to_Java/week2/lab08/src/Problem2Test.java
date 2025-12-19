import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem2Test {

    @Test
    void countEvenOdds() {
        assertArrayEquals(new int[]{4,4}, Problem2.countEvenOdds(new int[]{11, 9, 2, 3, 7, 10, 12, 114}));
        assertArrayEquals(new int[]{0,4}, Problem2.countEvenOdds(new int[]{11, 13, 15, 17}));
        assertArrayEquals(new int[]{0,0}, Problem2.countEvenOdds(new int[]{}));
    }
}
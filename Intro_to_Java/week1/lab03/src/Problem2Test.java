import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem2Test {

    @Test
    void crazyMath() {
        double Delta=.01;
        assertEquals(Double.NaN, Problem2.crazyMath(0));
        assertEquals(Double.POSITIVE_INFINITY, Problem2.crazyMath(1), Delta);
        assertEquals(21.52368973013284, Problem2.crazyMath(2), Delta);
        assertEquals(14.692493055407942, Problem2.crazyMath(3), Delta);
        assertEquals(9.574086130947974, Problem2.crazyMath(10), Delta);
        assertEquals(86.49768321282015, Problem2.crazyMath(100000), Delta);
    }
}
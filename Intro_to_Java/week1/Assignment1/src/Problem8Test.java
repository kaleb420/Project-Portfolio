import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem8Test {

    @Test
    void lessThan20() {
        assertEquals(true, Problem8.lessThan20(19, 2, 412));
        assertEquals(true, Problem8.lessThan20(2, 19, 50));
        assertEquals(true, Problem8.lessThan20(65, -38, 50));
        assertEquals(true, Problem8.lessThan20(-19, 0, 50));
        assertEquals(true, Problem8.lessThan20(-19, -5000, -5));
        assertEquals(true, Problem8.lessThan20(-0, -38, -57));
        assertEquals(false, Problem8.lessThan20(999, 979, 777));

    }
}
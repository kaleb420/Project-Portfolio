import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem4Test {

    @Test
    void isExtremeOutlier() {
        assertEquals(false, Problem4.isExtremeOutlier(100,100,10));
        assertEquals(false, Problem4.isExtremeOutlier(65,50,5));
        assertEquals(true, Problem4.isExtremeOutlier(34,20,4));
    }
}
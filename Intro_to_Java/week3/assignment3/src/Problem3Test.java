import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem3Test {

    @Test
    void canSum() {
        assertEquals(false, Problem3.canSum(new int[]{2,4,10,8},9));
        assertEquals(true, Problem3.canSum(new int[]{3,7,4,5,9},8));
        assertEquals(true, Problem3.canSum(new int[]{2,4,2,1,5,4},9));
        assertEquals(true, Problem3.canSum(new int[]{},0));
        assertEquals(true, Problem3.canSum(new int[]{-5,5,-7,7},0));
    }
}
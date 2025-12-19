import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem3Test {

    @Test
    void pyramidSurfaceArea() {
        assertEquals(0, Problem3.pyramidSurfaceArea(0,0,0));
        assertEquals(3.236, Problem3.pyramidSurfaceArea(1,1,1));
        assertEquals(261.7, Problem3.pyramidSurfaceArea(10,6,12));
    }
}
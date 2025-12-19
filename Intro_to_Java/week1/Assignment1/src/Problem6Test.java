import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem6Test {

    @Test
    void distanceTraveled() {
        assertEquals(6, Problem6.distanceTraveled(3,0,2));
        assertEquals(122.5, Problem6.distanceTraveled(0,9.8,5));
        assertEquals(16.5, Problem6.distanceTraveled(10,-3,3));
    }
}
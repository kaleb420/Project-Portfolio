import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem4Test {

    @Test
    void correlationCoefficient() {
        double delta=.05;
        assertEquals(0, Problem4.correlationCoefficient(new double[]{1,2,3}, new double[]{5,0,5}), delta);
        assertEquals(.5, Problem4.correlationCoefficient(new double[]{1,2,3}, new double[]{5,3,7}), delta);
        assertEquals(-.5, Problem4.correlationCoefficient(new double[]{1,2,3}, new double[]{7,3,5}), delta);
        assertEquals(1, Problem4.correlationCoefficient(new double[]{1,2,3}, new double[]{2,4,6}), delta);
        assertEquals(-1, Problem4.correlationCoefficient(new double[]{1,2,3}, new double[]{10,8,6}), delta);
    }
}
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem5Test {

    @Test
    void lawofCosines() {
        assertEquals(6.24, Problem5.lawOfCosines(5,7,60));
        assertEquals(14.14, Problem5.lawOfCosines(10,10,90));
        assertEquals(9.23, Problem5.lawOfCosines(8,6,30));
    }
}
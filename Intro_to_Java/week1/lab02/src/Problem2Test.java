import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem2Test {

    @Test
    void combineDigits() {
        assertEquals(45,Problem2.combineDigits(4,5));
        assertEquals(73,Problem2.combineDigits(7,3));
        assertEquals(7,Problem2.combineDigits(0,7));
    }
}
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem9Test {

    @Test
    void isEvenlySpaced() {
        assertEquals(true, Problem9.isEvenlySpaced(-20, 60, 20));
        assertEquals(true, Problem9.isEvenlySpaced(90, 60, 30));
        assertEquals(false, Problem9.isEvenlySpaced(10, 11, 22));
        assertEquals(false, Problem9.isEvenlySpaced(10, 21, 19));
        assertEquals(false, Problem9.isEvenlySpaced(-1, 11, 22));

    }
}
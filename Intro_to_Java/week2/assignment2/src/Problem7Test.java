import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem7Test {

    @Test
    void substring() {
        assertEquals("al", Problem7.substring("Bald", 1,3));
        assertEquals("", Problem7.substring("Bald", 3,3));
        assertEquals("Bald", Problem7.substring("Bald", 0,4));
        assertEquals(null, Problem7.substring("Ba", 0,3));
        assertEquals(null, Problem7.substring("", 0,3));
        assertEquals(null, Problem7.substring("Bald", -1,3));
        assertEquals(null, Problem7.substring("Bald", 3,1));
    }
}
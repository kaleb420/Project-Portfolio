import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem2Test {

    @Test
    void grocery() {
        assertEquals(0, Problem2.grocery(0,0,0,0,0));
        assertEquals(.59, Problem2.grocery(1,0,0,0,0));
        assertEquals(11.32, Problem2.grocery(2,2,2,2,2));
    }
}
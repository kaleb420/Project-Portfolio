import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem12Test {

    @Test
    void isInsideRectangle() {
        assertEquals(true, Problem12.isInsideRectangle(3,3,2,4,2.5,2.5));
        assertEquals(false, Problem12.isInsideRectangle(3,3,2,4,4,4));
        assertEquals(false, Problem12.isInsideRectangle(3,3,2,4,2,1));
        assertEquals(false, Problem12.isInsideRectangle(3,3,2,4,4,5));
        assertEquals(false, Problem12.isInsideRectangle(3,3,2,4,2,5));
        assertEquals(false, Problem12.isInsideRectangle(3,3,2,4,4,1));
        assertEquals(false, Problem12.isInsideRectangle(3,3,2,4,2,4));
        assertEquals(false, Problem12.isInsideRectangle(3,3,2,4,4,4));
        assertEquals(false, Problem12.isInsideRectangle(3,3,2,4,3,1));
        assertEquals(false, Problem12.isInsideRectangle(3,3,2,4,3,5));
        assertEquals(false, Problem12.isInsideRectangle(3,3,2,4,5,-2));
        assertEquals(true, Problem12.isInsideRectangle(0,0,1,1,0,0));
        assertEquals(true, Problem12.isInsideRectangle(-2,-2,2,2,-1.5,-2.5));
        assertEquals(true, Problem12.isInsideRectangle(3,3,2,4,2.1,4.9));
    }
}
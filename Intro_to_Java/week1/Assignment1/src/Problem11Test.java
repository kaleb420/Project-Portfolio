import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem11Test {

    @Test
    void cond() {
        assertEquals(true, Problem11.cond(true,true));
        assertEquals(false, Problem11.cond(true,false));
        assertEquals(true, Problem11.cond(false,true));
        assertEquals(true, Problem11.cond(false,false));
    }

    @Test
    void bicond() {
        assertEquals(true, Problem11.bicond(true,true));
        assertEquals(false, Problem11.bicond(true,false));
        assertEquals(false, Problem11.bicond(false,true));
        assertEquals(true, Problem11.bicond(false,false));
    }

    @Test
    void and() {
        assertEquals(true, Problem11.and(true,true));
        assertEquals(false, Problem11.and(true,false));
        assertEquals(false, Problem11.and(false,true));
        assertEquals(false, Problem11.and(false,false));
    }

    @Test
    void or() {
        assertEquals(true, Problem11.or(true,true));
        assertEquals(true, Problem11.or(true,false));
        assertEquals(true, Problem11.or(false,true));
        assertEquals(false, Problem11.or(false,false));
    }

    @Test
    void not() {
        assertEquals(false, Problem11.not(true));
        assertEquals(true, Problem11.not(false));
    }
}
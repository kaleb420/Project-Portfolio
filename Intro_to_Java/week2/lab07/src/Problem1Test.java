import static org.junit.jupiter.api.Assertions.*;

class Problem1Test {

    @org.junit.jupiter.api.Test
    void chickenCounter() {
        assertEquals(2, Problem1.chickenCounter("chchickenicken"));
        assertEquals(1, Problem1.chickenCounter("chicken"));
        assertEquals(0, Problem1.chickenCounter(""));
        assertEquals(0, Problem1.chickenCounter("hickenc"));
    }
}
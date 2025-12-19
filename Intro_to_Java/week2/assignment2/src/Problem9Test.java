import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem9Test {

    @Test
    void strSumNums() {
        assertEquals(100, Problem9.strSumNums("hello50how20are30you?"));
        assertEquals(10, Problem9.strSumNums("t1h1i1s1i1s1e1a1s1y1!"));
        assertEquals(0, Problem9.strSumNums("there are no numbers :("));
        assertEquals(0, Problem9.strSumNums("still 0 just 0 zero0!"));
        assertEquals(500000, Problem9.strSumNums("500000"));
        assertEquals(0, Problem9.strSumNums(""));
    }
}
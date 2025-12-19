import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem5Test {

    @Test
    void atoi() {
        assertEquals(0, Problem5.atoi("ABCD"));
        assertEquals(42, Problem5.atoi("42"));
        assertEquals(42, Problem5.atoi("000042"));
        assertEquals(4200, Problem5.atoi("004200"));
        assertEquals(42, Problem5.atoi("ABCD42ABCD"));
        assertEquals(42, Problem5.atoi("ABCD+42ABCD"));
        assertEquals(-42, Problem5.atoi("ABCD-42ABCD"));
        assertEquals(-42000, Problem5.atoi("000-42000"));
        assertEquals(0, Problem5.atoi("000-ABCD"));
        assertEquals(0, Problem5.atoi("-+-+1234"));
        assertEquals(0, Problem5.atoi("-A1234"));
        assertEquals(42, Problem5.atoi("000+42ABCD"));
        assertEquals(8080, Problem5.atoi("8080*8080"));
        assertEquals(123, Problem5.atoi("123.456"));
        assertEquals(0, Problem5.atoi(""));
        assertEquals(123123123, Problem5.atoi("123123123"));
        assertEquals(0, Problem5.atoi("0hl42"));
        assertEquals(42, Problem5.atoi("0^42^55"));
    }
}
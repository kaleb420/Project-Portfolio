import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem6Test {

    @Test
    void guessWord() {
        assertEquals(null, Problem6.guessWord("PLANS", "TRAP"));
        assertEquals("--A-*", Problem6.guessWord("PLANS", "TRAIN"));
        assertEquals("PLAN-", Problem6.guessWord("PLANS", "PLANE"));
        assertEquals("PLANS", Problem6.guessWord("PLANS", "PLANS"));
        assertEquals("*****", Problem6.guessWord("PLANS", "SNLPA"));
        assertEquals("", Problem6.guessWord("", ""));
    }
}
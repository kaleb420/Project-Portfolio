import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem10Test {

    @Test
    void cutTry() {
        assertEquals("Bald", Problem10.cutTry("Bald"));
        assertEquals("Bald", Problem10.cutTry("Baldtry"));
        assertEquals("", Problem10.cutTry("try"));
        assertEquals("trymore", Problem10.cutTry("trymore"));
        assertEquals("", Problem10.cutTry(""));
    }
}
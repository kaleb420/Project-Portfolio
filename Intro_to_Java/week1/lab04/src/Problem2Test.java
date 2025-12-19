import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem2Test {

    @Test
    void middleString() {
        assertEquals("bald", Problem2.middleString("cald", "bald", "aald"));
        assertEquals("third", Problem2.middleString("ascertain", "zebra", "third"));
        assertEquals("reason", Problem2.middleString("reason", "adler", "rzason"));
    }
}
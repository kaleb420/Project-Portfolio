import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem7Test {

    @Test
    void cutUsername() {
        assertEquals("bald", Problem7.cutUsername("bald@gmail.com"));
        assertEquals("longemailusername", Problem7.cutUsername("longemailusername@gmail.com"));
        assertEquals("", Problem7.cutUsername("@gmail.com"));
    }
}
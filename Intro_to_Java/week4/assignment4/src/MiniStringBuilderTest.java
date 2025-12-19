import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MiniStringBuilderTest {

    @Test
    void MiniStringBuilderTests() {
        MiniStringBuilder c = new MiniStringBuilder("B,a!l{d");
        MiniStringBuilder c2= new MiniStringBuilder("");
        MiniStringBuilder c3 = new MiniStringBuilder("B,a!l{d");
        MiniStringBuilder c4 = new MiniStringBuilder("apgoiwepaoghiesderg");
        assertEquals(true, c.equals(c3)); // tests valid equals
        assertEquals(false, c.equals(c2)); // tests invalid equals
        assertEquals("Ba!ld", c.toString()); // tests string with illegal characters
        assertEquals("", c2.toString()); // tests empty string
        c2.append("Bald"); // tests appending to empty string
        c4.append("Bald"); // tests appending which goes over the array limit
        assertEquals("apgoiwepaoghiesdergBald", c4.toString());
        c4.clear();
        assertEquals("", c4.toString());
    }
}
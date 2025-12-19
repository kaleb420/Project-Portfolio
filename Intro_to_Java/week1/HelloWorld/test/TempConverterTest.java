import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TempConverterTest {

    @Test
    void fToCTest() {
        assertEquals( 0.0, TempConverter.fToC(32));
    }
}
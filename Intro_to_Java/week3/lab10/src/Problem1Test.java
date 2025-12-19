import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Problem1Test {

    @org.junit.jupiter.api.Test
    void frequencies() {
        assertEquals(Map.of("World", 10, "Hello", 5), Problem1.frequencies("Hello Hello Hello Hello Hello World World World World World World World World World World"));
        assertEquals(Map.of("This", 1, "is", 1, "a", 1, "line", 1), Problem1.frequencies("This is a line"));
        assertEquals(Map.of(), Problem1.frequencies(""));
    }
}
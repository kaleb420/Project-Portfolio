import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Problem7Test {

    @Test
    void wordCount() {
        Map<String, Integer> M= new HashMap<>();
        M.put("hello", 1);
        M.put("world", 3);
        M.put("the", 2);
        M.put("is", 3);
        M.put("healthy", 2);
        M.put("it", 1);
        M.put("i", 1);
        M.put("certainly", 1);
        M.put("agree", 1);
        M.put("that", 1);
        M.put("1", 1);
        M.put("and", 1);
        M.put("not", 1);
        Map<String, Integer> M2= new HashMap<>();
        assertEquals(M, Problem7.wordCount("Hello world, the world is healthy, is it not? I certainly agree that the world is #1 and healthy."));
        assertEquals(M2, Problem7.wordCount("{}{}  {{] [][.? ><:"));
        assertEquals(M2, Problem7.wordCount(""));
    }
}
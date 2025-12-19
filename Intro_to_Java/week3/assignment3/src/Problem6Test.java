import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Problem6Test {

    @Test
    void tokenize() {
        assertEquals(List.of("1", "2", "3"), Problem6.tokenize("1,2,3", ','));
        assertEquals(List.of("B", "ld"), Problem6.tokenize("Bald", 'a'));
        assertEquals(List.of("Bald"), Problem6.tokenize("Bald", 'c'));
        assertEquals(List.of("Bal"), Problem6.tokenize("Bald", 'd'));
        assertEquals(List.of("ald"), Problem6.tokenize("Bald", 'B'));
        assertEquals(List.of("B", "ld"), Problem6.tokenize("Baald", 'a'));
        assertEquals(List.of(""), Problem6.tokenize("d", 'd'));
        assertEquals(List.of(""), Problem6.tokenize("dddd", 'd'));
        assertEquals(List.of("Bald", "Bald", "Bald"), Problem6.tokenize("Bald Bald Bald", ' '));
        assertEquals(List.of("ihargiohaiopfehaoifh", "apoiehfaihfiohaiwofh", "aopiwehfioahefohaoiefhapwo"), Problem6.tokenize("ihargiohaiopfehaoifh apoiehfaihfiohaiwofh aopiwehfioahefohaoiefhapwo", ' '));
        assertEquals(List.of(""), Problem6.tokenize("", ' '));
    }
}
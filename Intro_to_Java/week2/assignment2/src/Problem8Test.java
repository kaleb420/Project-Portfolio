import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem8Test {

    @Test
    void compareFiles() {
        assertEquals(1, Problem8.compareFiles("File12.txt", "File1.txt"));
        assertEquals(-1, Problem8.compareFiles("File10.txt", "File11.txt"));
        assertEquals(-1, Problem8.compareFiles("File1.txt", "File12.txt"));
        assertEquals(0, Problem8.compareFiles("File1.txt", "File1.txt"));
        assertEquals(-1, Problem8.compareFiles("Bald1.txt", "File1.txt"));
        assertEquals(-1, Problem8.compareFiles("File1.exe", "File1.txt"));
        assertEquals(1, Problem8.compareFiles("File1.exe", ""));
        assertEquals(-1, Problem8.compareFiles("", "File1.exe"));
        assertEquals(0, Problem8.compareFiles("", ""));
        assertEquals(0, Problem8.compareFiles("File01.txt", "File1.txt"));
    }
}
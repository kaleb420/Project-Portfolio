import static org.junit.jupiter.api.Assertions.*;

class Problem1Test {

    @org.junit.jupiter.api.Test
    void isPalindromeTR() {
        assertEquals(true, Problem1.isPalindromeTR("racecar"));
        assertEquals(false, Problem1.isPalindromeTR("bald"));
        assertEquals(true, Problem1.isPalindromeTR(""));
    }

    @org.junit.jupiter.api.Test
    void isPalindromeLoop() {
        assertEquals(true, Problem1.isPalindromeLoop("racecar"));
        assertEquals(false, Problem1.isPalindromeLoop("bald"));
        assertEquals(true, Problem1.isPalindromeLoop(""));
    }
}
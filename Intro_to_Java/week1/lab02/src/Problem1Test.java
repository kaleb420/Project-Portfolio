import static org.junit.jupiter.api.Assertions.assertEquals;

public class Problem1Test {
    @org.junit.jupiter.api.Test
    void celsiusToFahrenheit(){
        assertEquals(32, Problem1.celsiusToFahrenheit(0));
        assertEquals(212, Problem1.celsiusToFahrenheit(100));
        assertEquals(-40, Problem1.celsiusToFahrenheit(-40));
    }
}

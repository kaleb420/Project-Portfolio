import static org.junit.jupiter.api.Assertions.*;

class Problem1Test {

    @org.junit.jupiter.api.Test
    void gigameterToLightsecond() {
        double delta=.001;
        assertEquals(0, Problem1.gigameterToLightsecond(0), delta);
        assertEquals(1, Problem1.gigameterToLightsecond(0.2998), delta);
        assertEquals(3.3356, Problem1.gigameterToLightsecond(1), delta);
    }
}
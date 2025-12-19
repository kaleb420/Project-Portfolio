import static org.junit.jupiter.api.Assertions.*;

class TemperatureTest {
    double delta=.01;

    @org.junit.jupiter.api.Test
    void TemperatureTest() {
        Temperature t1 = new Temperature(0);
        Temperature t2= new Temperature(273.15);
        Temperature t3= new Temperature(-273.15);
        t1.setCelsius(0);
        assertEquals(0, t1.getCelsius(), delta);
        assertEquals(32, t1.getFahrenheit(), delta);
        assertEquals(273.15, t1.getKelvin(), delta);
        t2.setFahrenheit(32);
        assertEquals(0, t2.getCelsius(), delta);
        assertEquals(32, t2.getFahrenheit(), delta);
        assertEquals(273.15, t2.getKelvin(), delta);
        t3.setKelvin(-273.15);
        assertEquals(-546.3, t3.getCelsius(), delta);
        assertEquals(-951.34, t3.getFahrenheit(), delta);
        assertEquals(-273.15, t3.getKelvin(), delta);
    }
}
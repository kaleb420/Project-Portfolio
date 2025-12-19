import static org.junit.jupiter.api.Assertions.*;
class RgbColorTest {

    @org.junit.jupiter.api.Test
    void RgbColorTest(){
        RgbColor t1= new RgbColor(255,255,255);
        RgbColor t2= new RgbColor(124,42,4);
        RgbColor t3= new RgbColor(0,0,0);
        RgbColor a1= new RgbColor(0,0,0);
        RgbColor a2= new RgbColor(255,255,255);
        RgbColor a3= new RgbColor(255,255,255);
        assertEquals(a1, t1.invert()); // checks the invert function
        assertEquals(a3, t3.invert()); // checks the invert function
        assertEquals(a2, t1.grayscale()); // checks the convert to grayscale
        assertEquals(true, t1.isGrayscale()); // checks if it is grayscale (true)
        assertEquals(false, t2.isGrayscale()); // checks if it is grayscale (false)
        assertEquals(true, t1.equals(a2)); // checks if a given object is equal to the current RgbColor object (true)
        assertEquals(false, t1.equals(a1));
        assertEquals("(255, 255, 255)", t1.toString()); // converts the RgbColor object to a string
    }
}
import static org.junit.jupiter.api.Assertions.*;

class ChocolateBoxTest {

    @org.junit.jupiter.api.Test
    void ChocolateBoxTest() {
        ChocolateBox t1= new ChocolateBox(3,3);
        assertEquals(true, t1.addChocolate("Dark", 5, 0,0));
        assertEquals(true, t1.addChocolate("Nut", 5, 0,1));
        assertEquals(true, t1.addChocolate("Dark", 5, 0,2));
        assertEquals(true, t1.addChocolate("Nut", 5, 0,3));
        assertEquals(true, t1.addChocolate("Dark", 5, 1,0));
        assertEquals(false, t1.addChocolate("Nut", 3, 1,1));
        assertEquals(1, t1.numberOfChocolates());
        assertEquals(0, t1.removeFirst("Dark"));
    }
}
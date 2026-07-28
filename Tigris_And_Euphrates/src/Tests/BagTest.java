package Tests;

import Setup.Bag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    Bag bag=new Bag();

    @Test
    void remove() {
        assertEquals(0, bag.temples);
        assertEquals(0, bag.farms);
        assertEquals(0, bag.markets);
        assertEquals(0, bag.settlements);
    }

    @Test
    void draw() {
        for (int i = 0; i < 153; i++) {
            String drew=bag.draw();
            assertTrue(drew.equals("T") || drew.equals("F") || drew.equals("M") || drew.equals("S"));
        }
        assertTrue(bag.shuffledBag.isEmpty());
    }
}
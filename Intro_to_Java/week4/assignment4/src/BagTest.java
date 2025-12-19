import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    @Test
    void BagTests() {
        Bag<Integer> bag = new Bag<>();
        Bag<Integer> bag2 = new Bag<>();
        bag.insert(4);
        bag.insert(4);
        bag.insert(3);
        assertEquals(true, bag.contains(4));
        assertEquals(false, bag.contains(2));
        assertEquals(2, bag.count(4));
        assertEquals(0, bag.count(2));
        assertEquals(3, bag.size());
        assertEquals(true, bag.remove(3));
        assertEquals(false, bag.contains(3));
        assertEquals(false, bag.remove(3));
        assertEquals(true, bag.isSubBag(bag2)); // bag contains 2 4's, bag2 contains nothing
        bag2.insert(4);
        assertEquals(true, bag.isSubBag(bag2)); // bag contains 2 4's, bag2 contains 1 4
        assertEquals(false, bag2.isSubBag(bag)); // bag contains 2 4's, bag2 contains 1 4
        bag.insert(3);
        bag2.insert(3);
        bag2.insert(5);
        assertEquals(false, bag.isSubBag(bag2)); // bag contains 2 4's and 1 3, bag2 contains 1 4, 1 3, and 1 5
        bag2.remove(5);
        assertEquals(true, bag.isSubBag(bag2)); // bag contains 2 4's and 1 3, bag2 contains 1 4 and  1 3
        assertEquals(false, bag2.isSubBag(bag)); // bag contains 2 4's and 1 3, bag2 contains 1 4 and 1 3
        bag2.remove(3);
        assertEquals(true, bag.isSubBag(bag2)); // bag contains 2 4's and 1 3, bag2 contains 1 4
        assertEquals(false, bag2.isSubBag(bag)); // bag contains 2 4's and 1 3, bag2 contains 1 4
        bag.remove(4);
        bag.remove(4);
        bag.remove(4);
        bag.remove(3);
        assertEquals(0, bag.size());
        assertEquals(false, bag.isSubBag(bag2)); // bag contains nothing, bag2 contains 1 4 and 1 3
        bag2.remove(4);
        bag2.remove(3);
        assertEquals(true, bag.isSubBag(bag2)); // both bags contain nothing
    }
}
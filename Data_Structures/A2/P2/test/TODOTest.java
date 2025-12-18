import hash.chains.Chained;
import hash.exceptions.KeyNotFoundE;
import hash.extendible.Extendible;
import hash.probing.DoubleHashing;
import hash.probing.Linear;
import hash.probing.Quadratic;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TODOTest {

    @Test
    void testChains () throws KeyNotFoundE{
        Chained<Integer, String> map = new Chained<>(4);

        // 1️⃣ Initially empty
        assertTrue(map.isEmpty(), "Map should start empty");
        assertEquals(0, map.size());
        assertEquals(0.0, map.getLoadFactor());

        // 2️⃣ Basic insertion and lookup
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        assertEquals(3, map.size());
        assertEquals("A", map.get(1));
        assertEquals("B", map.get(2));
        assertEquals("C", map.get(3));
        assertFalse(map.isEmpty());

        map.put(2, "BB");
        assertEquals("BB", map.get(2));
        assertEquals(3, map.size());

        assertTrue(map.containsKey(1));
        assertTrue(map.containsValue("BB"));
        assertFalse(map.containsKey(999));
        assertFalse(map.containsValue("ZZZ"));

        Set<Integer> keys = map.keySet();
        Set<String> values = map.values();
        assertTrue(keys.containsAll(Set.of(1, 2, 3)));
        assertTrue(values.containsAll(Set.of("A", "BB", "C")));

        map.put(5, "D");
        assertEquals("D", map.get(5));

        map.remove(1);
        assertEquals(3, map.size());
        assertFalse(map.containsKey(1));

        map.put(6, "E");
        map.put(7, "F");

        map.clear();
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
        assertEquals(0.0, map.getLoadFactor());
    }

    @Test
    void testLinear () throws KeyNotFoundE {
        Linear<Integer, String> map = new Linear<>(4);

        assertTrue(map.isEmpty());
        assertEquals(0, map.size());

        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        assertEquals("A", map.get(1));
        assertEquals("B", map.get(2));
        assertEquals("C", map.get(3));
        assertEquals(3, map.size());

        map.put(2, "BB");
        assertEquals("BB", map.get(2));

        assertTrue(map.containsKey(1));
        assertTrue(map.containsValue("BB"));
        assertFalse(map.containsKey(999));
        assertFalse(map.containsValue("Z"));

        Set<Integer> keys = map.keySet();
        Set<String> vals = map.values();
        assertTrue(keys.containsAll(Set.of(1, 2, 3)));
        assertTrue(vals.containsAll(Set.of("A", "BB", "C")));

        map.remove(1);
        assertFalse(map.containsKey(1));

        map.put(10, "10");
        map.rehash();
        assertEquals("10", map.get(10));

        map.clear();
        assertTrue(map.isEmpty());
    }

    @Test
    void testQuadratic () throws KeyNotFoundE{
        Quadratic<Integer, String> map = new Quadratic<>(4);

        assertTrue(map.isEmpty());
        assertEquals(0, map.size());

        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        assertEquals("A", map.get(1));
        assertEquals("B", map.get(2));
        assertEquals("C", map.get(3));

        map.put(3, "CC");
        assertEquals("CC", map.get(3));

        assertTrue(map.containsKey(2));
        assertTrue(map.containsValue("CC"));
        assertFalse(map.containsKey(999));

        Set<Integer> keys = map.keySet();
        Set<String> vals = map.values();
        assertTrue(keys.containsAll(Set.of(1, 2, 3)));
        assertTrue(vals.containsAll(Set.of("A", "B", "CC")));

        map.remove(2);
        assertFalse(map.containsKey(2));
        assertThrows(KeyNotFoundE.class, () -> map.get(2));

        // Auto rehash
        map.put(4, "D");
        map.put(5, "E");

        map.put(10, "10");
        map.rehash();
        assertEquals("10", map.get(10));

        map.clear();
        assertTrue(map.isEmpty());

        map.put(100, "Z");
    }

    @Test
    void testDoubleHash () throws KeyNotFoundE{
        DoubleHashing<Integer, String> map = new DoubleHashing<>(5);

        assertTrue(map.isEmpty());
        assertEquals(0, map.size());

        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        assertEquals("A", map.get(1));
        assertEquals("B", map.get(2));
        assertEquals("C", map.get(3));

        map.put(2, "BB");
        assertEquals("BB", map.get(2));

        assertTrue(map.containsKey(1));
        assertTrue(map.containsValue("BB"));
        assertFalse(map.containsKey(999));

        Set<Integer> keys = map.keySet();
        Set<String> vals = map.values();
        assertTrue(keys.containsAll(Set.of(1, 2, 3)));
        assertTrue(vals.containsAll(Set.of("A", "BB", "C")));

        // Remove
        map.remove(1);
        assertFalse(map.containsKey(1));
        assertThrows(KeyNotFoundE.class, () -> map.get(1));

        map.put(4, "D");
        map.put(5, "E");

        map.put(10, "10");
        map.rehash();
        assertEquals("10", map.get(10));

        map.clear();
        assertTrue(map.isEmpty());
    }

    @Test
    void testExtendible () throws KeyNotFoundE{
        Extendible<Integer, String> map = new Extendible<>();

        assertTrue(map.isEmpty());
        assertEquals(0, map.size());

        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        assertEquals(3, map.size());
        assertEquals("A", map.get(1));
        assertEquals("B", map.get(2));
        assertEquals("C", map.get(3));
        assertFalse(map.isEmpty());

        map.put(2, "BB");
        assertEquals("BB", map.get(2));
        assertEquals(3, map.size());

        assertTrue(map.containsKey(1));
        assertTrue(map.containsValue("BB"));
        assertFalse(map.containsKey(99));
        assertFalse(map.containsValue("Z"));

        Set<Integer> keys = map.keySet();
        Set<String> values = map.values();
        assertTrue(keys.containsAll(Set.of(1, 2, 3)));
        assertTrue(values.containsAll(Set.of("A", "BB", "C")));

        map.rehash();

        map.remove(1);
        assertFalse(map.containsKey(1));
        assertEquals(2, map.size());

        map.clear();
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());

        map.put(200, "X");
        map.put(201, "Y");

        assertEquals(2, map.size());
        assertEquals("X", map.get(200));
        assertEquals("Y", map.get(201));
    }

    @Test
    void speed () {
        assertTrue(true);
    }
}

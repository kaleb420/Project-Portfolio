import dynamicArray.DequeueA;
import org.junit.jupiter.api.Test;
import pointers.DequeueP;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TODOTestP1 {

    @Test
    void testDequeueP() {
        DequeueP<Integer> dq1 = new DequeueP<>();
        DequeueP<Integer> dq2 = new DequeueP<>();

        dq1.enqueueBack(1);
        dq1.enqueueBack(2);
        dq1.enqueueBack(3);

        dq2.enqueueBack(1);
        dq2.enqueueBack(2);
        dq2.enqueueBack(3);

        assertEquals(dq1, dq2);
        assertEquals(dq1.hashCode(), dq2.hashCode());

        DequeueP<Integer> dq3 = new DequeueP<>();
        dq3.enqueueBack(1);
        dq3.enqueueBack(4);
        dq3.enqueueBack(9);

    }

    @Test
    void testDequeueA() {
        DequeueA<Integer> dq1 = new DequeueA<>(10);
        DequeueA<Integer> dq2 = new DequeueA<>(10);

        dq1.enqueueBack(10);
        dq1.enqueueBack(20);
        dq1.enqueueBack(30);

        dq2.enqueueBack(10);
        dq2.enqueueBack(20);
        dq2.enqueueBack(30);

        assertEquals(dq1, dq2);
        assertEquals(dq1.hashCode(), dq2.hashCode());

        DequeueA<Integer> dq3 = new DequeueA<>(10);
        dq3.enqueueBack(10);
        dq3.enqueueBack(25);
        dq3.enqueueBack(35);

        assertNotEquals(dq1, dq3);
        assertNotEquals(dq1.hashCode(), dq3.hashCode());

        DequeueA<Integer> empty1 = new DequeueA<>(5);
        DequeueA<Integer> empty2 = new DequeueA<>(5);
        assertEquals(empty1, empty2);
        assertEquals(empty1.hashCode(), empty2.hashCode());
    }
        // --- Helper ---
        private DequeueP<Integer> dequeOf(Integer... values) {
            DequeueP<Integer> dq = new DequeueP<>();
            for (Integer v : values) {
                dq.enqueueBack(v);
            }
            return dq;
        }

        // --- Basic equality tests ---

        @Test
        void testEqualSameElements() {
            DequeueP<Integer> dq1 = dequeOf(1, 2, 3);
            DequeueP<Integer> dq2 = dequeOf(1, 2, 3);
            assertEquals(dq1, dq2, "Equal deques with same elements should be equal");
            assertEquals(dq1.hashCode(), dq2.hashCode(), "Equal deques should have same hash code");
        }

        @Test
        void testDifferentOrder() {
            DequeueP<Integer> dq1 = dequeOf(1, 2, 3);
            DequeueP<Integer> dq2 = dequeOf(3, 2, 1);
            assertNotEquals(dq1, dq2, "Deques with same elements but different order should not be equal");
        }

        @Test
        void testDifferentValues() {
            DequeueP<Integer> dq1 = dequeOf(1, 2, 3);
            DequeueP<Integer> dq2 = dequeOf(1, 2, 4);
            assertNotEquals(dq1, dq2, "Deques with different values should not be equal");
        }

        @Test
        void testDifferentSizes() {
            DequeueP<Integer> dq1 = dequeOf(1, 2, 3);
            DequeueP<Integer> dq2 = dequeOf(1, 2);
            assertNotEquals(dq1, dq2, "Deques with different sizes should not be equal");
        }

        @Test
        void testEmptyDequesEqual() {
            DequeueP<Integer> dq1 = new DequeueP<>();
            DequeueP<Integer> dq2 = new DequeueP<>();
            assertEquals(dq1, dq2, "Two empty deques should be equal");
            assertEquals(dq1.hashCode(), dq2.hashCode(), "Empty deques should have same hash code");
        }

        @Test
        void testEmptyVsNonEmpty() {
            DequeueP<Integer> dq1 = new DequeueP<>();
            DequeueP<Integer> dq2 = dequeOf(1);
            assertNotEquals(dq1, dq2, "Empty deque and non-empty deque should not be equal");
        }

        // --- Reference and null comparisons ---

        @Test
        void testSelfEquality() {
            DequeueP<Integer> dq = dequeOf(1, 2, 3);
            assertEquals(dq, dq, "Deque should be equal to itself");
        }

        @Test
        void testNullEquality() {
            DequeueP<Integer> dq = dequeOf(1, 2, 3);
            assertNotEquals(dq, null, "Deque should not be equal to null");
        }

        @Test
        void testDifferentClassNotEqual() {
            DequeueP<Integer> dq = dequeOf(1, 2, 3);
            String notDeque = "not a deque";
            assertNotEquals(dq, notDeque, "Deque should not be equal to an object of another class");
        }

        // --- Edge / Special cases ---

        @Test
        void testWithNullElements() {
            DequeueP<String> dq1 = new DequeueP<>();
            DequeueP<String> dq2 = new DequeueP<>();
            dq1.enqueueBack(null);
            dq2.enqueueBack(null);
            assertEquals(dq1, dq2, "Deques containing single null should be equal");
            assertEquals(dq1.hashCode(), dq2.hashCode(), "Hash codes for null-element deques should match");
        }

        @Test
        void testNullAndNonNullElements() {
            DequeueP<String> dq1 = new DequeueP<>();
            DequeueP<String> dq2 = new DequeueP<>();
            dq1.enqueueBack(null);
            dq2.enqueueBack("X");
            assertNotEquals(dq1, dq2, "Deque with null should not equal deque with non-null element");
        }

        @Test
        void testRepeatedElements() {
            DequeueP<Integer> dq1 = dequeOf(5, 5, 5);
            DequeueP<Integer> dq2 = dequeOf(5, 5, 5);
            assertEquals(dq1, dq2, "Deques with identical repeated elements should be equal");
            assertEquals(dq1.hashCode(), dq2.hashCode(), "Repeated element deques should share hash code");
        }

        @Test
        void testRepeatedElementsDifferentLength() {
            DequeueP<Integer> dq1 = dequeOf(5, 5, 5);
            DequeueP<Integer> dq2 = dequeOf(5, 5);
            assertNotEquals(dq1, dq2, "Different-length repeated element deques should not be equal");
        }

        @Test
        void testHashCodeStableForUnchangedDeque() {
            DequeueP<Integer> dq = dequeOf(1, 2, 3);
            int h1 = dq.hashCode();
            int h2 = dq.hashCode();
            assertEquals(h1, h2, "hashCode should be stable for unchanged object");
        }

        @Test
        void testHashCodeChangesWhenElementsChange() {
            DequeueP<Integer> dq = dequeOf(1, 2, 3);
            int oldHash = dq.hashCode();
            dq.enqueueBack(4);
            int newHash = dq.hashCode();
            assertNotEquals(oldHash, newHash, "hashCode should change when elements change");
        }

        @Test
        void testHashCodeEqualForIndependentEqualObjects() {
            DequeueP<Integer> dq1 = dequeOf(10, 20, 30);
            DequeueP<Integer> dq2 = dequeOf(10, 20, 30);
            assertEquals(dq1.hashCode(), dq2.hashCode(), "Independent but equal deques should have same hash code");
        }

        @Test
        void testHashCodeDifferentForDistinctObjects() {
            DequeueP<Integer> dq1 = dequeOf(10, 20, 30);
            DequeueP<Integer> dq2 = dequeOf(10, 20, 31);
            assertNotEquals(dq1.hashCode(), dq2.hashCode(), "Different contents should yield different hash codes");
        }

        @Test
        void testEqualAfterSameOperations() {
            DequeueP<Integer> dq1 = new DequeueP<>();
            DequeueP<Integer> dq2 = new DequeueP<>();

            dq1.enqueueFront(1);
            dq1.enqueueBack(2);
            dq1.enqueueBack(3);

            dq2.enqueueFront(1);
            dq2.enqueueBack(2);
            dq2.enqueueBack(3);

            assertEquals(dq1, dq2, "Deques built by same operations should be equal");
            assertEquals(dq1.hashCode(), dq2.hashCode(), "Hash code should match for operationally equal deques");
        }

        @Test
        void testNotEqualAfterDifferentOperations() {
            DequeueP<Integer> dq1 = new DequeueP<>();
            DequeueP<Integer> dq2 = new DequeueP<>();

            dq1.enqueueBack(1);
            dq1.enqueueBack(2);
            dq2.enqueueBack(1);
            dq2.enqueueFront(2);

            assertNotEquals(dq1, dq2, "Different operation order producing different structure should not be equal");
        }
}

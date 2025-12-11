package heap;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeapTest_Student {

    private <E extends Comparable<E>> BinaryMinHeap.Heap2Node<E> bn(E v) {
        return new BinaryMinHeap.Heap2Node<>(v);
    }

    @Test
    public void binaryHeap() throws Exception {
        BinaryMinHeap<Integer> h = new BinaryMinHeap<>();

        HeapNode<Integer> a = bn(5);
        HeapNode<Integer> b = bn(2);
        HeapNode<Integer> c = bn(8);
        HeapNode<Integer> d = bn(1);

        h.insert(a);
        h.insert(b);
        h.insert(c);
        h.insert(d);

        assertEquals(4, h.getSize());
        assertEquals(Integer.valueOf(1), h.getMin().value);
        assertEquals(0, h.getMin().index);
    }

    @Test
    public void removeMin() throws Exception {
        BinaryMinHeap<Integer> h = new BinaryMinHeap<>();

        HeapNode<Integer> a = bn(5);
        HeapNode<Integer> b = bn(3);
        HeapNode<Integer> c = bn(8);
        HeapNode<Integer> d = bn(1);

        h.insert(a);
        h.insert(b);
        h.insert(c);
        h.insert(d);

        HeapNode<Integer> removed = h.removeMin();
        assertEquals(Integer.valueOf(1), removed.value);

        assertEquals(3, h.getSize());
        assertEquals(Integer.valueOf(3), h.getMin().value);
    }

    @Test
    public void reduceValue() throws Exception {
        BinaryMinHeap<Integer> h = new BinaryMinHeap<>();

        HeapNode<Integer> n9 = bn(9);
        HeapNode<Integer> n4 = bn(4);
        HeapNode<Integer> n7 = bn(7);

        h.insert(n9);
        h.insert(n4);
        h.insert(n7);

        h.reduceValue(n7, 1);

        assertEquals(Integer.valueOf(1), h.getMin().value);
        assertEquals(0, h.getMin().index);
    }

    @Test
    public void swapNodes() {
        BinaryMinHeap<Integer> h = new BinaryMinHeap<>();

        HeapNode<Integer> a = bn(4);
        HeapNode<Integer> b = bn(7);

        h.insert(a);
        h.insert(b);

        assertEquals(0, a.index);
        assertEquals(1, b.index);

        h.swapNodes(a, b);

        assertEquals(1, a.index);
        assertEquals(0, b.index);
        assertSame(b, h.nodes.get(0));
        assertSame(a, h.nodes.get(1));
    }

    @Test
    public void minChild() throws Exception {
        BinaryMinHeap<Integer> h = new BinaryMinHeap<>();

        HeapNode<Integer> a = bn(1);
        HeapNode<Integer> b = bn(10);
        HeapNode<Integer> c = bn(3);

        h.insert(a);
        h.insert(b);
        h.insert(c);

        assertEquals(c, h.getMinChild(a));
    }
}

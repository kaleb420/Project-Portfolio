package heap;
import java.util.ArrayList;
import java.util.List;

/**
 * MinHeap is an abstract class that represents a minimum heap data structure.
 * It provides methods to insert elements, remove the minimum element, and
 * manage the heap properties.
 */
public abstract class MinHeap<E extends Comparable<E>> {
    protected List<HeapNode<E>> nodes = new ArrayList<>();
    protected int size = 0;

    /**
     * The two abstract methods getParent and getChildren that need to be
     * implemented by the subclasses of MinHeap.
     */
    public abstract HeapNode<E> getParent(HeapNode<E> node) throws NoParentE;

    public abstract List<HeapNode<E>> getChildren(HeapNode<E> node) throws NoChildE;

    public List<HeapNode<E>> getNodes() { return nodes; }

    //--------------------------------------------------------------------
    // Getters and some utility methods public for testing
    //--------------------------------------------------------------------

    public int getSize() {
        return size;
    }

    /**
     * Returns the minimum child of the given node or throws NoChildE if the node has no children.
     */
    public HeapNode<E> getMinChild(HeapNode<E> elem) throws NoChildE {
        List<HeapNode<E>> children=getChildren(elem);
        if (children.isEmpty())
            throw new NoChildE();
        HeapNode<E> smallest=children.getFirst();
        for (int i = 1; i < children.size(); i++) {
            if (children.get(i).value.compareTo(smallest.value)<0)
                smallest=children.get(i);
        }
        return smallest;
    }

    /**
     * Swaps the two nodes in the heap. It is important to note that this method
     * must update the indices of the nodes as well.
     */
    public void swapNodes(HeapNode<E> a, HeapNode<E> b) {
        int indexA=nodes.indexOf(a);
        int indexB=nodes.indexOf(b);
        nodes.set(indexA, b);
        nodes.set(indexB, a);
        a.index=indexB;
        b.index=indexA;
    }

    /**
     * Moves the given node up in the heap until it is in the correct position
     * according to the heap property.
     */
    public void moveUp(HeapNode<E> elem) {
            while (elem.index!=0){
                try {
                    if (getParent(elem).value.compareTo(elem.value)>0)
                        swapNodes(elem, getParent(elem));
                    else
                        return;
                }
                catch (NoParentE e) {
                    return;
                }
            }
    }

    /**
     * Reduces the value of the given node and moves it up in the heap until it is
     * in the correct position according to the heap property.
     */
    public void reduceValue (HeapNode<E> elem, E newValue) {
        elem.value=newValue;
        moveUp(elem);
    }

    /**
     * Moves the given node down in the heap until it is in the correct position
     * according to the heap property.
     */
    public void moveDown(HeapNode<E> elem) {
        try {
            while (!getChildren(elem).isEmpty()) {
                HeapNode<E> minChild=getMinChild(elem);
                if (minChild.value.compareTo(elem.value)<0)
                    swapNodes(elem, minChild);
                else
                    return;
            }
        }
        catch (NoChildE e) {
        }
    }

    /**
     * Returns the minimum element in the heap without removing it. Throws
     * EmptyHeapExc if the heap is empty.
     */
    public HeapNode<E> getMin() throws EmptyHeapExc {
        if (!nodes.isEmpty())
            return nodes.getFirst();
        throw new EmptyHeapExc();
    }

    /**
     * Inserts the given node into the heap. The node must not already be in the
     * heap so its fields 'index' and 'heap' must be properly initialized at this point.
     * The node is added to the end of the heap and then moved up to its
     * correct position.
     */
    public void insert(HeapNode<E> elem) {
        if (nodes.contains(elem))
            return;
        nodes.add(elem);
        elem.setHeap(this);
        elem.setIndex(size);
        size++;
        moveUp(elem);
    }

    /**
     * Removes the minimum element from the heap and returns it. But first,
     * the element at index 0 in the array cannot be left empty: it must be updated to
     * contain the new minimum. To do that, the last node in the array is moved to the
     * first position, and then we invoke moveDown on it. This will ensure that the
     * heap property is maintained. The method returns the minimum element that was
     * removed.
     */
    public HeapNode<E> removeMin() throws EmptyHeapExc {
        if (nodes.isEmpty())
            throw new EmptyHeapExc();
        swapNodes(nodes.getFirst(), nodes.getLast());
        HeapNode<E> temp=nodes.getLast();
        temp.index=0;
        nodes.remove(nodes.getLast());
        if (!nodes.isEmpty())
            moveDown(nodes.getFirst());
        size--;
        return temp;
    }

    // --------------------------------------------------------------------
    // Exceptions
    // --------------------------------------------------------------------

    public static class NoParentE extends Exception {}
    public static class NoChildE extends Exception {}
    public static class EmptyHeapExc extends Exception {}
}

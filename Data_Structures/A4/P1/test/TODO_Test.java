import org.junit.jupiter.api.Test;
import redblack.*;
import redblack.RedBlackTree;
import trees.EmptyTreeExc;
import trees.TreePrinter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TODO_Test {
    /** TODO: Create your own tests using TreePrinter.java to visualize
     *    the structure of your AVL Tree(s) and confirm balancing behavior
     */
    @Test
    void duplicates() throws EmptyTreeE {
        AVL_Tree<Integer> tree = new Empty<>();
        tree = tree.insert(10).insert(5).insert(15);

        AVL_Tree<Integer> original = tree;

        tree = tree.insert(10);

        assertSame(original, tree);
        assertTrue(tree.contains(10));
        assertEquals(5, tree.findMin());
    }

    @Test
    void mergeSubtrees() throws EmptyTreeE {
        AVL_Tree<Integer> tree = new Empty<>();

        tree = tree.insert(20).insert(10).insert(30)
                .insert(5).insert(15)
                .insert(25).insert(40);

        tree = tree.remove(20);

        assertFalse(tree.contains(20));
        assertTrue(tree.contains(10));
        assertTrue(tree.contains(30));
        assertTrue(tree.contains(25));
        assertEquals(5, tree.findMin());
    }

    @Test
    void testManyNodes() throws EmptyTreeE {
        AVL_Tree<Integer> tree = new Empty<>();

        for (int i = 1; i <= 100; i++)
            tree = tree.insert(i);
        for (int i = 100; i >= 1; i--)
            tree = tree.remove(i);

        assertTrue(tree.isEmpty());
    }

    @Test
    void removeAll() throws EmptyTreeE {
        AVL_Tree<Integer> tree =
                new Empty<Integer>().insert(5).insert(3).insert(8)
                        .insert(2).insert(4).insert(7).insert(9);

        while (!tree.isEmpty()) {
            int min = tree.findMin();
            tree = tree.remove(min);
            assertTrue(Math.abs(tree.balanceFactor()) <= 1);
        }

        assertTrue(tree.isEmpty());
    }

}
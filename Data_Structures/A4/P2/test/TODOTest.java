import org.junit.jupiter.api.Test;
import redblack.Color;
import redblack.EmptyRB;
import redblack.RBNode;
import redblack.RedBlackTree;
import trees.EmptyTreeExc;

import static org.junit.jupiter.api.Assertions.*;

public class TODOTest {
    @Test
    public void testColor() {
        assertEquals(Color.RED, Color.BLACK.redder());
        assertEquals(Color.BLACK, Color.RED.blacker());
        assertEquals(Color.DOUBLE_BLACK, Color.BLACK.blacker());
        assertEquals(Color.NEGATIVE_BLACK, Color.RED.redder());
        assertEquals(Color.RED, Color.BLACK.redder());
    }

    @Test
    public void testEmptyRB() {
        RedBlackTree<Integer> b = new EmptyRB<>(Color.BLACK);
        RedBlackTree<Integer> db = new EmptyRB<>(Color.DOUBLE_BLACK);

        assertTrue(b.isEmpty());
        assertTrue(db.isEmpty());

        assertTrue(b.isBlack());
        assertTrue(db.isDoubleBlack());

        assertTrue(b.redden().isRed());

        assertTrue(b.blacken().isBlack());
    }

    @Test
    public void constructRB () {
        RedBlackTree<Integer> leaf =
                new RBNode<>(Color.RED, 10);

        RedBlackTree<Integer> leftChild =
                new RBNode<>(Color.BLACK, 5, new EmptyRB<>(), new EmptyRB<>());

        RedBlackTree<Integer> rightChild =
                new RBNode<>(Color.BLACK, 15, new EmptyRB<>(), new EmptyRB<>());

        RedBlackTree<Integer> root =
                new RBNode<>(Color.BLACK, 20, leftChild, rightChild);

        assertTrue(leaf.isWellFormed());
        assertTrue(leftChild.isWellFormed());
        assertTrue(rightChild.isWellFormed());
        assertTrue(root.isWellFormed());

        assertEquals(leftChild.blackHeight(), rightChild.blackHeight());
        assertEquals(root.blackHeight(),
                Math.max(leftChild.blackHeight(), rightChild.blackHeight()) + 1);
    }

    @Test
    public void testInsert () {
        RedBlackTree<Integer> tree = new EmptyRB<>();

        tree = tree.insert(10);
        tree = tree.insert(5);
        tree = tree.insert(15);

        assertTrue(tree.isWellFormed());

        tree = tree.insert(1);
        tree = tree.insert(0);
        tree = tree.insert(7);
        tree = tree.insert(20);
        tree = tree.insert(25);
        tree = tree.insert(24);

        assertTrue(tree.isWellFormed());
    }

    @Test
    public void testRemove () throws EmptyTreeExc {
        RedBlackTree<Integer> tree = new EmptyRB<>();

        tree = tree.insert(10);
        tree = tree.insert(5);
        tree = tree.insert(15);
        tree = tree.insert(2);
        tree = tree.insert(7);
        tree = tree.insert(12);
        tree = tree.insert(17);

        assertTrue(tree.isWellFormed());

        tree = tree.remove(2);
        assertTrue(tree.isWellFormed());

        tree = tree.remove(5);
        assertTrue(tree.isWellFormed());

        tree = tree.remove(10);
        assertTrue(tree.isWellFormed());

        tree = tree.remove(7);
        tree = tree.remove(12);
        tree = tree.remove(15);
        tree = tree.remove(17);

        assertTrue(tree.isWellFormed());
        assertTrue(tree.isEmpty());
    }
}

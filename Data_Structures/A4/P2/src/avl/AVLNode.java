package avl;

import trees.EmptyTreeExc;
import trees.TreePrinter;
import org.jetbrains.annotations.NotNull;

public record AVLNode<E extends Comparable<E>>
        (@NotNull E value,
         @NotNull AVLTree<E> left,
         @NotNull AVLTree<E> right,
         int height)
        implements AVLTree<E> {

    // Constructors -------------------------------------------------------------------------

    public AVLNode(@NotNull E value) {
        this(value, new EmptyAVL<>(), new EmptyAVL<>());
    }

    public AVLNode(@NotNull E value, @NotNull AVLTree<E> left, @NotNull AVLTree<E> right) {
        this(value, left, right, 1 + Math.max(left.height(), right.height()));
    }

    // Simple one-liner methods ------------------------------------------------------------

    public boolean isEmpty() { return false; }
    public int height() {
        return height;
    }
    public int balanceFactor() {
        return left.height() - right.height();
    }
    public boolean isWellFormed() {
        return left.isWellFormed() && right.isWellFormed() && Math.abs(balanceFactor()) <= 1;
    }

    // Search methods -----------------------------------------------------------------------

    public boolean contains(@NotNull E searchValue) {
        boolean leftCase;
        boolean rightCase;
        if (this.value==searchValue)
            return true;
        else if (this.left.isEmpty() && this.right.isEmpty())
            return false;
        leftCase=this.left.contains(searchValue);
        rightCase=this.right.contains(searchValue);
        return leftCase || rightCase;
        // in case comparison of searchValue to value fails, throw new RuntimeException("Comparison failed");
    }

    public @NotNull E findMin() throws EmptyTreeExc {
        if (this.isEmpty())
            throw new EmptyTreeExc("Empty tree");
        else if (this.left.isEmpty())
            return this.value;
        return this.left.findMin();
    }

    // Insertion and deletion methods ------------------------------------------------------

    public @NotNull AVLTree<E> insert(@NotNull E newValue) {
        if (newValue.compareTo(value)<0 && left.isEmpty())
            return mkBalanced(value, new AVLNode<>(newValue, new EmptyAVL<>(), new EmptyAVL<>()), right);
        else if (newValue.compareTo(value)>0 && right.isEmpty())
            return mkBalanced(value, left, new AVLNode<>(newValue, new EmptyAVL<>(), new EmptyAVL<>()));
        else if (newValue.compareTo(value)<0)
            return mkBalanced(value, left.insert(newValue), right);
        else if (newValue.compareTo(value)>0)
            return mkBalanced(value, left, right.insert(newValue));
        else
            return this;
        // in case comparison of newValue to value fails, throw new RuntimeException("Comparison failed");
    }

    public @NotNull AVLTree<E> remove(@NotNull E removeValue) throws EmptyTreeExc {
        if (isEmpty())
            throw new EmptyTreeExc("Empty tree");
        if (removeValue.compareTo(value)<0)
            return mkBalanced(value, left.remove(removeValue), right);
        else if (removeValue.compareTo(value)>0)
            return mkBalanced(value, left, right.remove(removeValue));
        else {
            return mergeSubtrees(left, right);
        }
        // in case comparison of removeValue to value fails, throw new RuntimeException("Comparison failed");
    }

    public static <E extends Comparable<E>> @NotNull AVLTree<E> mergeSubtrees(
            @NotNull AVLTree<E> left,
            @NotNull AVLTree<E> right) {

        try { return mkBalanced(right.findMin(), left, right.removeMin()); }
        catch (EmptyTreeExc e) { return left; }
    }

    public @NotNull AVLTree<E> removeMin() {
        try { return mkBalanced(value, left.removeMin(), right); }
        catch (EmptyTreeExc e) { return right; }
    }

    // Rotations ----------------------------------------------------------------------------

    public static <E extends Comparable<E>> AVLTree<E> mkBalanced
            (@NotNull E value,
             @NotNull AVLTree<E> left,
             @NotNull AVLTree<E> right) {
        return new AVLNode<>(value, left, right).rotate();
    }

    public @NotNull AVLTree<E> rotate() {
        if (balanceFactor()==2){
            if (this.left.balanceFactor()>=0)
                return rotateRight();
            else
                return rotateLeftRight();
        }
        else if (balanceFactor()==-2){
            if (this.right.balanceFactor()<=0)
                return rotateLeft();
            else
                return rotateRightLeft();
        }
        return this;
    }

    public @NotNull AVLTree<E> rotateRight() {
        AVLNode<E> leftTree=(AVLNode<E>) this.left;
        AVLTree<E> second=new AVLNode<>(value, leftTree.right, right);
        return new AVLNode<>(leftTree.value, leftTree.left, second);
        // in case rotation fails, throw new RuntimeException("Right rotation failed");
    }

    public @NotNull AVLTree<E> rotateLeft() {
        AVLNode<E> rightTree=(AVLNode<E>) this.right;
        AVLTree<E> second=new AVLNode<>(value, left, rightTree.left);
        return new AVLNode<>(rightTree.value, second, rightTree.right);
        // in case rotation fails, throw new RuntimeException("Left rotation failed");
    }

    public AVLTree<E> rotateLeftRight() {
        AVLNode<E> treeLeft=(AVLNode<E>) this.left();
        AVLNode<E> first=new AVLNode<>(value, treeLeft.rotateLeft(), right);
        return first.rotateRight();
        // in case rotation fails, throw new RuntimeException("Left-Right rotation failed");
    }

    public AVLTree<E> rotateRightLeft() {
        AVLNode<E> treeRight =(AVLNode<E>) this.right();
        AVLNode<E> first=new AVLNode<>(value, left, treeRight.rotateRight());
        return first.rotateLeft();
        // in case rotation fails, throw new RuntimeException("Right-Left rotation failed");
    }

    // TreePrinter.PrintableNode interface methods ------------------------------------------

    public TreePrinter.PrintableNode getLeft() {
        return left.isEmpty() ? null : left;
    }
    public TreePrinter.PrintableNode getRight() {
        return right.isEmpty() ? null : right;
    }
    public String getText() {
        return value.toString();
    }
}

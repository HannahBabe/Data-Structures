/**
 * abstract class representing any binary tree
 * 
 * @author hannahbabe
 *
 * @param <T>
 */
public abstract class BinaryTree<T> {
    abstract String toString(String indent);

    abstract boolean isEmpty();

    abstract int height();

    abstract int nodeCount();

    abstract int leafCount();

    abstract int levelCount(int level);

    abstract BinaryTree<T> mirrorImage();

    abstract int weightBalanceFactor();

    abstract int nodeSum();

    abstract void doubles();

    abstract int maxPathSum();

    abstract String preOrder();

    abstract String postOrder();

    abstract String inOrder();

}

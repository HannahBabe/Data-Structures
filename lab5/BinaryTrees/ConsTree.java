/**
 * represents any non-empty binary tree
 * 
 * @author hannahbabe
 * 
 * @param <T>
 */
public class ConsTree<T> extends BinaryTree<T> {
    private T data;
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;

    public ConsTree(T data, BinaryTree<T> left, BinaryTree<T> right) {
	this.data = data;
	leftChild = left;
	rightChild = right;
    }

    public ConsTree(T data) {
	this(data, new EmptyTree<T>(), new EmptyTree<T>());
    }

    public String toString(String indent) {
	return rightChild.toString(indent + "   ") + "\n" + indent + "/\n" + indent + data.toString() + "\n" + indent
		+ "\\" + leftChild.toString(indent + "   ");
    }

    public String toString() {
	return toString("");
    }

    public boolean isEmpty() {
	return false;
    }

    public int height() {
	return Math.max(leftChild.height(), rightChild.height()) + 1;
    }

    public int nodeCount() {
	return 1 + leftChild.nodeCount() + rightChild.nodeCount();
    }

    public int leafCount() {
	if (leftChild.isEmpty() && rightChild.isEmpty()) {
	    return 1;
	} else {
	    return leftChild.leafCount() + rightChild.leafCount();
	}
    }

    public int levelCount(int level) {
	if (level == 0) {
	    return 1;
	}
	return leftChild.levelCount(level - 1) + rightChild.levelCount(level - 1);
    }

    public BinaryTree<T> mirrorImage() {// return mirrored image of tree
	leftChild.mirrorImage();
	rightChild.mirrorImage();
	BinaryTree<T> temp = leftChild;
	leftChild = rightChild;
	rightChild = temp;
	return new ConsTree<T>(data, leftChild, rightChild);

    }

    public int weightBalanceFactor() {// return weighted balance factor
	int nodeCount = Math.abs(leftChild.nodeCount() - rightChild.nodeCount());
	int maxBalance = Math.max(leftChild.weightBalanceFactor(), rightChild.weightBalanceFactor());
	return Math.max(nodeCount, maxBalance);
    }

    public int nodeSum() { // returns sum of data in tree
	return Integer.parseInt((String) data) + rightChild.nodeSum() + leftChild.nodeSum();
    }

    public void doubles() {// double value in every tree node
	int d = Integer.parseInt((String) data) * 2;
	data = (T) ("" + d);
	rightChild.doubles();
	leftChild.doubles();

    }

    public int maxPathSum() {// max sum of data values from root to leaf
	int a = Integer.parseInt((String) data) + rightChild.maxPathSum();
	int b = Integer.parseInt((String) data) + leftChild.maxPathSum();
	return Math.max(a, b);
    }

    public String preOrder() {
	return data + "\n" + leftChild.preOrder() + rightChild.preOrder();
    }

    public String postOrder() {
	return leftChild.postOrder() + rightChild.postOrder() + data + "\n";
    }

    public String inOrder() {
	return leftChild.inOrder() + data + "\n" + rightChild.inOrder();
    }
}

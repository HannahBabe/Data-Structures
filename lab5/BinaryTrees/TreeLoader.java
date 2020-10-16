
/** @author hannahbabe
 * Load a tree from a text file.  Format is line based, with each line
 * consisting of a String for data, followed by two ints indicating if
 * the node has a left child or right child.  (1 is yes, 0 is no).
 * Ordering of nodes is postorder.
 *
 * @author John Donaldson
 * @author Benjamin Kuperman (Spring 2007)
 * @author Alexa Sharp (Fall 2012)
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class TreeLoader {

    BinaryTree<String> loadTreeFromFile(String fname) throws IOException {
	Stack<BinaryTree<String>> stack = new Stack<BinaryTree<String>>();
	Scanner scan = new Scanner(new File(fname));
	while (scan.hasNextLine()) {
	    String Line = scan.nextLine();
	    Scanner S = new Scanner(Line);
	    String data = S.next();
	    BinaryTree<String> leftChild = new EmptyTree<String>();
	    BinaryTree<String> rightChild = new EmptyTree<String>();
	    int leftBit = S.nextInt();
	    int rightBit = S.nextInt();
	    if (rightBit == 1) {
		rightChild = stack.pop();
	    }
	    if (leftBit == 1) {
		leftChild = stack.pop();
	    }
	    BinaryTree<String> tree = new ConsTree<String>(data, leftChild, rightChild);
	    stack.push(tree);
	}
	return stack.peek();

    }

    // So you can test your tree loader
    public static void main(String[] args) throws IOException {
	if (args.length != 1) {
	    System.out.println("Usage:  java TreeLoader filename");
	} else {
	    TreeLoader tl = new TreeLoader();
	    BinaryTree<String> t = tl.loadTreeFromFile(args[0]);
	    System.out.println(t);
	}
    }
}

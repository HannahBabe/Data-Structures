/* creates a priority queue based on smallest value
 * @author hannahbabe
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyPriorityQueue<T> {
    int size;
    Comparator<T> c;
    List<T> arr;

    public MyPriorityQueue(int initial_capacity, Comparator<T> comp) {
	this.arr = new ArrayList<T>(initial_capacity);
	arr.add(0, null);
	this.c = comp;
	this.size = 0;
    }

    // returns the index of my parent
    private int parent(int myIndex) {
	return myIndex / 2;
    }

    // returns the index of my left child
    private int leftChild(int myIndex) {
	return 2 * myIndex;
    }

    // returns the index of my rightChild
    private int rightChild(int myIndex) {
	return 2 * (myIndex) + 1;
    }

    // This is the basis of the offer method. index is the current index
    // of the node we are trying to insert. If index is not 1 compare the
    // item at index with its parent; if they are in the wrong order switch
    // them and recurse on the parent's index.
    private void percolateUp(int index) {
	if (index != 1) {
	    T parentData = arr.get(parent(index));
	    T node = arr.get(index);
	    // System.out.println(node);
	    // System.out.println(parentData);
	    // System.out.println(c.compare(node, parentData));
	    if (c.compare(node, parentData) == -1) {
		arr.set(parent(index), node);
		arr.set(index, parentData);
		percolateUp(parent(index));
	    }
	}
    }

    // This is the basis of the poll method. index is the current index of
    // the node we are trying to put in place. If index has no children just
    // stop. If index has only a left child compare the value there with the
    // value at index. If they are in the wrong order switch them and recurse on
    // the child's index. If index has two children compare the value at index
    // with the smaller of the two child values; if they are in the wrong order
    // switch them and recurse on the child's index.
    // Remember that if the queue currently has size entries they are indexed
    // 1...size
    private void percolateDown(int index) {
	if (2 * index > size) {
	    return;
	} else if (2 * index == size) {
	    if (c.compare(arr.get(index), arr.get(size)) == 1) {
		T temp = arr.get(index);
		arr.set(index, arr.get(size));
		arr.set(size, temp);
		percolateDown(leftChild(index));
	    }
	} else {
	    T smallest = null;
	    int childIndex = 0;
	    T left = arr.get(leftChild(index));
	    T right = arr.get(rightChild(index));
	    if (c.compare(left, right) == -1) {
		smallest = left;
		childIndex = leftChild(index);
	    } else {
		smallest = right;
		childIndex = rightChild(index);
	    }
	    if (c.compare(arr.get(index), smallest) == 1) {
		T temp = arr.get(index);
		arr.set(index, smallest);
		arr.set(childIndex, temp);
		percolateDown(childIndex);
	    }
	}

    }

    // return the number of entries in the priority queue.
    public int size() {
	return size;
    }

    // run through all of the entries in the queue; set them to null.
    // Then set the size to .
    public void clear() {
	for (int i = 0; i <= size; i++) {
	    arr.set(i, null);
	    size = 0;
	}
    }

    // returns the value at the root of the heap, or null if the heap is empty.
    public T peek() {
	if (size == 0) {
	    return null;
	} else {
	    return arr.get(1);
	}
    }

    // This removes and returns the value at the root of the heap.
    // If the heap is empty it returns null.
    // First save the value at the root in a temporary variable.
    // Put the value of the last child at the root, reduce the size,
    // and call percolateDown at the root. At the end return the old
    // root that you saved at the start.
    // Remember that the nodes are indexed 1...size
    public T poll() {
	if (size == 0) {
	    return null;
	} else {
	    T tempRoot = arr.get(1);
	    arr.set(1, arr.get(size));
	    size--;
	    percolateDown(1);
	    return tempRoot;
	}
    }

    // This adds a new element to the queue by inserting it at the next
    // available leaf, then percolating up. It should always return true.
    public boolean offer(T item) {
	// System.out.println("array" + arr);
	arr.add(size + 1, item);
	percolateUp(size + 1);
	size++;
	return true;

    }

}

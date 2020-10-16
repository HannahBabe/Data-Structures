
/**
 * 3/2/2020
 * code defines MyLinkedList, Node, and MyListIterator
 * hbabe
 */

import java.util.AbstractList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList1<T> extends AbstractList<T> {

    protected class Node {
	T data;
	Node next;
	Node prev;

	public Node() {
	    data = null;
	    next = prev = null;
	}

	public Node(T data) {
	    this.data = data;
	    next = prev = null;
	}

    }

    // MyLinkedList class
    private Node head;
    private Node tail;
    private int size;
    private int numEdits;

    public MyLinkedList1() {
	head = new Node();
	tail = new Node();
	head.next = tail;
	tail.prev = head;
	size = 0;
	numEdits = 0;
    }

    private Node getNth(int index) {
	if (index < 0 || index > size) {
	    throw new IndexOutOfBoundsException("Index out of bounds!.");
	}
	Node p = this.head;
	for (int i = 0; i <= index; i++) {
	    p = p.next;
	}
	return p;
    }

    public boolean add(T data) {
	add(size, data);
	return true;
    }

    public void add(int index, T data) {
	if (data == null) {
	    throw new NullPointerException("Tried to add a null value.");
	}
	if (index < 0 || index > this.size) {
	    throw new IndexOutOfBoundsException("Can't add at that index.");
	}
	Node n = new Node();
	n.data = data;
	Node current = getNth(index);
	// if (current == null) {
	// System.out.println("yes");
	// }
	// System.out.println(data);
	Node p = current.prev;
	p.next = n;
	n.prev = p;
	n.next = current;
	current.prev = n;

	/*
	 * if (index != 0) { // System.out.println(current.data); Node p = current.prev;
	 * n.prev = p; p.next = n; } n.next = current; //
	 * System.out.println(current.data); current.prev = n;
	 */
	this.size++;
	numEdits++;
    }

    public T get(int i) {
	if (i < 0 || i > size) {
	    throw new IndexOutOfBoundsException("Index out of bounds!.");
	}
	return (T) getNth(i).data;
    }

    public T set(int i, T data) {
	if (data == null) {
	    throw new NullPointerException("Tried to add a null value.");
	}
	if (i < 0 || i >= this.size) {
	    throw new IndexOutOfBoundsException("Can't add at that index.");
	}
	Node p = getNth(i);
	T oldData = p.data;
	p.data = data;
	return oldData;
    }

    public T remove(int i) {
	if (i < 0 || i >= this.size) {
	    throw new IndexOutOfBoundsException("Can't add at that index.");
	}
	Node p = getNth(i);
	T d = p.data;
	// System.out.println(d);
	Node pr = p.prev;
	Node ne = p.next;
	pr.next = ne;
	ne.prev = pr;
	this.size--;
	numEdits++;
	return d;
    }

    public void clear() {
	head.next = tail;
	tail.prev = head;
	size = 0;
    }

    public boolean isEmpty() {
	return (size == 0);
    }

    public int size() {
	return size;
    }

    public ListIterator<T> listIterator() {
	return new MyListIterator();
    }

    public Iterator<T> iterator() {
	return new MyListIterator();
    }

    protected class MyListIterator implements ListIterator<T> {
	private Node left;
	private boolean hasmoved;
	private boolean hasaltered;
	private boolean movingright;
	private boolean movingleft;
	private int index;
	private int numEdits;

	public MyListIterator() {
	    this.left = head;
	    this.hasaltered = false;
	    this.hasmoved = false;
	    this.movingright = false;
	    this.movingleft = false;
	    this.index = 0;
	    this.numEdits = MyLinkedList1.this.numEdits;
	}

	@Override
	public boolean hasNext() {
	    check();
	    return left.next != tail;
	}

	@Override
	public T next() throws NoSuchElementException {
	    check();
	    if (hasNext()) {
		left = left.next;
		hasmoved = true;
		movingright = true;
		movingleft = false;
		hasaltered = false;
		index++;
		return (T) left.data;
	    } else {
		throw new NoSuchElementException();
	    }
	}

	@Override
	public boolean hasPrevious() {
	    check();
	    return left != head;
	}

	@Override
	public T previous() {
	    check();
	    if (hasPrevious()) {
		left = left.prev;
		hasmoved = true;
		movingleft = true;
		movingright = false;
		hasaltered = false;
		index--;
		return left.next.data;
	    } else {
		throw new NoSuchElementException();
	    }
	}

	@Override
	public int nextIndex() {
	    check();
	    if (!hasNext()) {
		return size;
	    } else {
		return index + 1;
	    }
	}

	@Override
	public int previousIndex() {
	    check();
	    if (!hasPrevious()) {
		return -1;
	    } else {
		return index - 1;
	    }
	}

	@Override
	public void remove() {
	    check();
	    if (hasmoved == false || hasaltered == true) {
		throw new IllegalStateException();
	    } else if (movingleft == true) {
		MyLinkedList1.this.remove(previousIndex());
	    } else if (movingright == true) {
		MyLinkedList1.this.remove(nextIndex());
	    }
	    hasaltered = true;
	    index--;
	    numEdits++;
	}

	@Override
	public void set(T e) throws IllegalStateException {
	    check();
	    if (hasmoved == false || hasaltered == true) {
		throw new IllegalStateException();
	    } else if (movingleft == true) {
		MyLinkedList1.this.set(previousIndex(), e);
	    } else if (movingright == true) {
		MyLinkedList1.this.set(nextIndex(), e);
	    }
	}

	@Override
	public void add(T e) {
	    check();
	    Node oldleft = left;
	    Node newleft = new Node();
	    newleft.data = e;
	    Node right = oldleft.next;

	    newleft.next = right;
	    newleft.prev = oldleft;
	    right.prev = newleft;
	    oldleft.next = newleft;
	    left = newleft;
	    index++;
	    size++;
	    hasaltered = true;
	    numEdits++;
	}

	private void check() {
	    if (numEdits != MyLinkedList1.this.numEdits) {
		throw new IllegalStateException();
	    }
	}
    }

}

// You need to add distance and predecessor information for the Single Source All Paths algorithm.
// You also need to complete the getPath method.

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Vertex {
    String name;
    List<Edge> adjacentList;
    int distance;
    Vertex predecessor;
    // add distance and predeesoor variables here

    // initialize distance and predecessor here.
    public Vertex(String name) {
	this.name = name;
	adjacentList = new LinkedList<Edge>();
	this.distance = Integer.MAX_VALUE;
	this.predecessor = null;

    }

    public int distance() {
	return distance;
    }

    public List<Edge> adjacents() {
	return adjacentList;
    }

    public String toString() {
	return name;
    }

    // This is called, after the allPaths algorithm is run, to represent the
    // path from the source to this vertex.
    // Follow the predecessor nodes back to the source (whose predecessor should
    // be null), each time pushing the vertex
    // onto a stack. Then pop the stack until it is empty, each time adding a
    // String version of the vertex onto a String.
    // When the staclk is empty return the string.
    // If 'A">is the source nod3, "C" is the current node and the path goes
    // through "B" this should return somethign like
    // "A => B => C"

    public String getPath() {
	Stack<Vertex> stack = new Stack<Vertex>();
	Vertex v = this;
	while (v != null) {
	    stack.push(v);
	    v = v.predecessor;
	}
	String s = "";
	while (!stack.isEmpty()) {
	    Vertex d = stack.pop();
	    if (stack.isEmpty()) {
		s += d.toString();
	    } else {
		s += d.toString() + " => ";
	    }
	}

	return s;
    }
}

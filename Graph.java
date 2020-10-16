import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Graph {
    public static int Infinity = Integer.MAX_VALUE;

    private HashMap<String, Vertex> vertexMap;
    private LinkedList<Vertex> actors;
    private LinkedList<Vertex> movies;

    public Graph() {
	vertexMap = new HashMap<String, Vertex>();
	movies = new LinkedList<Vertex>();
	actors = new LinkedList<Vertex>();
    }

    public Vertex getVertex(String name) {
	Vertex v = vertexMap.get(name);
	if (v == null) {
	    v = new Vertex(name);
	    vertexMap.put(name, v);
	}
	return v;
    }

    HashMap<String, Vertex> vertexMap() {
	return vertexMap;
    }

    public int vertexCount() {
	return vertexMap.size();
    }

    public void addEdge(String source, String dest) {
	Vertex v = getVertex(source);
	Vertex w = getVertex(dest);
	List<Edge> L = v.adjacents();
	L.add(new Edge(w));
    }

    public void printAvailableActors() {
	for (Vertex e : actors)
	    System.out.printf("%d: %s\n", e.distance() / 2, e);
    }

    public void printAvailableMovies() {
	for (Vertex e : movies)
	    System.out.printf("%s\n", e);
    }

    // EVERYTHING ABOVE THIS LINE IS ALREADY IMPLEMENTED.

    // This reads the named file one line at a time and builds the graph.
    // The file is formatted in the form
    // source|destination
    public void loadFile(String fName) {
	Scanner scan = null;
	try {
	    if (fName.substring(0, 4).equals("http"))
		scan = new Scanner(new URL(fName).openStream());
	    else
		scan = new Scanner(new File(fName));
	} catch (FileNotFoundException e) {
	    System.out.printf("File '%s' not found\n", fName);
	} catch (IOException e) {
	}
	while (scan.hasNextLine()) {
	    String line = scan.nextLine();
	    int b = line.indexOf('|');
	    String actor = line.substring(0, b);
	    String movie = line.substring(b + 1, line.length());
	    addEdge(actor, movie);
	    addEdge(movie, actor);
	}

    }

    // This implements the All Paths Single Source algorithm. As it works if it
    // finds that a node has a less-than-infinite distance from the source node
    // it
    // should add the node together the available movie list or the available
    // actors
    // list.
    // Note that a node of the graph represents an actor if its distance is
    // even,
    // and it represents a movie if its distance is odd.
    public void findAllPaths(String s) {
	Vertex v = getVertex(s); // set v to vertex with name 's'
	MyQueue<Vertex> queue = new MyQueue<Vertex>();
	queue.enqueue(v);
	v.distance = 0;
	while (!queue.isEmpty()) {
	    Vertex vert = (Vertex) queue.dequeue();
	    for (Edge edge : vert.adjacents()) { // iterate through all adjacent edges next to the source
		Vertex dest = edge.destination(); // set dest to destination
		if (dest.distance >= Infinity) {
		    dest.predecessor = vert;
		    dest.distance = vert.distance() + 1;
		    queue.enqueue(dest);
		    if (dest.distance() % 2 == 0) {
			actors.add(dest);
		    } else {
			movies.add(dest);
		    }
		}
	    }

	}

    }
}

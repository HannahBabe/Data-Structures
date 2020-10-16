/**
 * 2/25/2020 Extends MazeSolver using queues to solve mazes!
 * 
 * @author hbabe
 */
public class MazeSolverQueue extends MazeSolver {
    MyQueue<Square> queue;

    public MazeSolverQueue(Maze maze) {
	super(maze);
    }

    public static void main(String[] args) {
	Maze maze = new Maze();
	if (maze.loadMaze(args[0])) {
	    maze.loadMaze(args[0]);
	    MazeSolverQueue solver = new MazeSolverQueue(maze);
	    solver.makeEmpty();
	    solver.add(maze.getStart());
	    solver.solve();
	} else {
	    System.out.println("Could not load maze.");
	    System.exit(0);
	}
    }

    @Override
    void makeEmpty() {
	queue = new MyQueue<Square>();

    }

    @Override
    boolean isEmpty() {
	return queue.isEmpty();
    }

    @Override
    void add(Square sq) {
	queue.enqueue(sq);
    }

    @Override
    Square next() {
	return (Square) queue.dequeue();
    }

}

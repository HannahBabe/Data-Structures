/**
 * 2/25/2020 Extends MazeSolver using stacks to solve mazes!
 * 
 * @author hbabe
 */

public class MazeSolverStack extends MazeSolver {
    private MyStack<Square> stack;

    public MazeSolverStack(Maze maze) {
	super(maze);
    }

    public static void main(String[] args) {
	Maze maze = new Maze();
	if (maze.loadMaze(args[0])) {
	    MazeSolverStack solver = new MazeSolverStack(maze);
	    solver.makeEmpty();
	    solver.add(maze.getStart());
	    solver.solve();
	} else {
	    System.out.println("Could not load maze.");
	    System.exit(0);
	}
    }

    @Override
    public void makeEmpty() {
	stack = new MyStack<Square>();
    }

    @Override
    public boolean isEmpty() {
	return stack.isEmpty();
    }

    @Override
    public void add(Square sq) {
	stack.push(sq);

    }

    @Override
    public Square next() {
	return (Square) stack.pop();
    }

}

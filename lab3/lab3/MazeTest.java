import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

public class MazeTest extends TestCase {

    @Test
    public void testLoadMazeTrue() {
	Maze maze = new Maze();
	assertTrue(maze.loadMaze("maze-1"));
    }

    @Test
    public void testLoadMazeFalse() {
	Maze maze = new Maze();
	assertFalse(maze.loadMaze("maaze"));
    }

    @Test
    public void testGetNeighborsStart() {
	Maze maze = new Maze();
	maze.loadMaze("maze-1");
	System.out.print(maze.getNeighbors(maze.getStart()));
	System.out.println();
	ArrayList<Square> r = maze.getNeighbors(maze.getStart());
	for (Square s : r) {
	    System.out.println(s.getType());
	}
    }

    @Test
    public void testReset() {
	Maze maze = new Maze();
	maze.loadMaze("maze-1");
	ArrayList<Square> r = maze.getNeighbors(maze.getStart());
	for (Square s : r) {
	    assertFalse(s.reset());
	}
    }

    @Test
    public void testgetStart() {
	Maze maze = new Maze();
	maze.loadMaze("maze-1");
	assertEquals(2, maze.getStart().getType());
    }

    @Test
    public void testgetFinish() {
	Maze maze = new Maze();
	maze.loadMaze("maze-1");
	assertEquals(3, maze.getFinish().getType());
    }

}

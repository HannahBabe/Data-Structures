import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 2/24/2020 Stores logical layout of a maze
 * 
 * @author hbabe
 */

public class Maze {
    private Square[][] maze;
    private int numRows;
    private int numCols;
    private Square start;
    private Square finish;

    public Maze() {
    }

    public boolean loadMaze(String fname) {
	try {
	    Scanner reader = new Scanner(new File(fname));
	    numRows = reader.nextInt();
	    numCols = reader.nextInt();
	    maze = new Square[numRows][numCols];
	    for (int row = 0; row < numRows; row++) {
		for (int col = 0; col < numCols; col++) {
		    int type = reader.nextInt();
		    if (type == 2) {
			start = new Square(row, col, type);
		    }
		    if (type == 3) {
			finish = new Square(row, col, type);
		    }
		    maze[row][col] = new Square(row, col, type);
		}
	    }

	    // Prints maze

	    for (int r = 0; r < maze.length; r++) {
		for (int c = 0; c < maze[0].length; c++) {
		    System.out.print(maze[r][c].getType());
		}
		System.out.println();
	    }

	    // prints rows, cols
	    // System.out.println(numRows + ", " + numCols);

	} catch (FileNotFoundException e) {
	    System.out.println("File does not exist.");
	    return false;
	}

	return true;
    }

    public ArrayList<Square> getNeighbors(Square sq) {
	ArrayList<Square> neighbors = new ArrayList<Square>();
	int row = sq.getRow();
	int col = sq.getCol();

	if (row > 0) {
	    int northT = maze[row - 1][col].getType();
	    if (northT == 0 || northT == 3) {
		neighbors.add(maze[row - 1][col]);
	    }
	}

	if (col < numCols - 1) {
	    int eastT = maze[row][col + 1].getType();
	    if (eastT == 0 || eastT == 3) {
		neighbors.add(maze[row][col + 1]);
	    }
	}

	if (row < numRows - 1) {
	    int southT = maze[row + 1][col].getType();
	    if (southT == 0 || southT == 3) {
		neighbors.add(maze[row + 1][col]);
	    }
	}

	if (col > 0) {
	    int westT = maze[row][col - 1].getType();
	    if (westT == 0 || westT == 3) {
		neighbors.add(maze[row][col - 1]);
	    }
	}
	/*
	 * for (int i = 0; i < neighbors.size(); i++) {
	 * System.out.print(neighbors.get(i).getType()); } System.out.println();
	 */
	return neighbors;
    }

    public Square getStart() {
	return start;
    }

    public Square getFinish() {
	return finish;
    }

    public void reset() {
	for (int r = 0; r < maze.length; r++) {
	    for (int c = 0; c < maze[0].length; c++) {
		maze[r][c].reset();
	    }
	}
    }
}

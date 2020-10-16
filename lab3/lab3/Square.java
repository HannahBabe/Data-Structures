
/**
 * 2/24/2020 Represents a single square in the maze
 * 
 * @author hbabe
 */
public class Square {
    public static final int SPACE = 0, WALL = 1, START = 2, EXIT = 3;
    private int row;
    private int col;
    private int type;
    private boolean marked;
    private Square previous;

    public int getRow() {
	return row;
    }

    public int getCol() {
	return col;
    }

    public int getType() {
	return type;
    }

    public Square(int row, int col, int type) {
	this.row = row;
	this.col = col;
	this.type = type;
	this.marked = false;
	this.previous = null;
    }

    public boolean reset() {
	marked = false;
	return marked;
    }

    public void mark() {
	this.marked = true;
    }

    public boolean marked() {
	return marked;
    }

    public void setPrevious(Square p) {
	previous = p;
    }

    public Square getPrevious() {
	return previous;
    }

}

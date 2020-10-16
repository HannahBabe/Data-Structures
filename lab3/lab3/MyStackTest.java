import java.util.Stack;

import org.junit.Test;

import junit.framework.TestCase;

public class MyStackTest extends TestCase {

    @Test
    public void testIsEmpty() {
	Stack<Integer> real = new Stack<Integer>();
	MyStack<Integer> test = new MyStack<Integer>();
	assertEquals(real.empty(), test.isEmpty());
    }

    @Test
    public void testClear() {
	Stack<Integer> real = new Stack<Integer>();
	MyStack<Integer> test = new MyStack<Integer>();
	real.push(1);
	test.push(1);
	real.clear();
	test.clear();
	assertEquals(real.empty(), test.isEmpty());

    }

    @Test
    public void testPushAndTop() {
	Stack<Integer> real = new Stack<Integer>();
	MyStack<Integer> test = new MyStack<Integer>();
	real.push(1);
	test.push(1);
	assertEquals(real.peek(), test.top());
    }

    @Test
    public void testPop() {
	Stack<Integer> real = new Stack<Integer>();
	MyStack<Integer> test = new MyStack<Integer>();
	real.push(1);
	test.push(1);
	assertEquals(real.pop(), test.pop());
    }
    /*
     * @Test(expected = NoSuchElementException.class) public void testPopException()
     * throws Exception { MyStack<Integer> test = new MyStack<Integer>();
     * test.pop(); }
     * 
     * 
     * @Test(expected = NoSuchElementException.class) public void testTopException()
     * throws Exception { MyStack<Integer> test = new MyStack<Integer>();
     * test.top(); } }
     */
}

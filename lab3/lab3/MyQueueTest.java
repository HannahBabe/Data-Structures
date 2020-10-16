import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Test;

import junit.framework.TestCase;

public class MyQueueTest extends TestCase {

    @Test
    public void testEnqueue() {
	ConcurrentLinkedQueue<Integer> real = new ConcurrentLinkedQueue<Integer>();
	MyQueue<Integer> test = new MyQueue<Integer>();
	real.add(1);
	test.enqueue(1);

	assertEquals(real.peek(), test.front());
    }

    @Test
    public void testClear() {
	ConcurrentLinkedQueue<Integer> real = new ConcurrentLinkedQueue<Integer>();
	MyQueue<Integer> test = new MyQueue<Integer>();
	real.add(1);
	test.enqueue(1);
	real.clear();
	test.clear();
	assertEquals(real.isEmpty(), test.isEmpty());
    }

    @Test
    public void testIsEmpty() {
	ConcurrentLinkedQueue<Integer> real = new ConcurrentLinkedQueue<Integer>();
	MyQueue<Integer> test = new MyQueue<Integer>();
	assertEquals(real.isEmpty(), test.isEmpty());
    }

    @Test
    public void testDequeue() {
	ConcurrentLinkedQueue<Integer> real = new ConcurrentLinkedQueue<Integer>();
	MyQueue<Integer> test = new MyQueue<Integer>();
	real.add(1);
	test.enqueue(1);
	assertEquals(real.poll(), test.dequeue());
    }

    @Test
    public void testFront() {
	ConcurrentLinkedQueue<Integer> real = new ConcurrentLinkedQueue<Integer>();
	MyQueue<Integer> test = new MyQueue<Integer>();
	real.add(1);
	test.enqueue(1);
	assertEquals(real.peek(), test.front());
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeueException() throws NoSuchElementException {
	MyQueue<Integer> test = new MyQueue<Integer>();
	test.dequeue();
    }

    @Test(expected = NoSuchElementException.class)
    public void testFrontException() throws NoSuchElementException {
	MyQueue<Integer> test = new MyQueue<Integer>();
	test.front();
    }
}

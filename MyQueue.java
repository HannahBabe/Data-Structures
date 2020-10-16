import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * 2/25/2020 Implements QueueADT
 * 
 * @author hbabe
 */
public class MyQueue<T> implements QueueADT {
    LinkedList<T> queue = new LinkedList<T>();

    @Override
    public void enqueue(Object item) {
	queue.add((T) item);

    }

    @Override
    public Object dequeue() throws NoSuchElementException {
	if (queue.size() == 0) {
	    throw new NoSuchElementException();
	}
	return queue.remove(0);
    }

    @Override
    public Object front() throws NoSuchElementException {
	if (queue.size() == 0) {
	    throw new NoSuchElementException();
	}
	return queue.getFirst();
    }

    @Override
    public int size() {
	return queue.size();
    }

    @Override
    public boolean isEmpty() {
	return (queue.size() == 0);
    }

    @Override
    public void clear() {
	for (T element : queue) {
	    queue.remove(element);
	}

    }
}

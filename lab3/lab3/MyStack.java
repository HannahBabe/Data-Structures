
/**
 * 2/25/2020 
 * Implements StackADT
 * 
 * @author hbabe
 */

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MyStack<T> implements StackADT {
    ArrayList<T> stack = new ArrayList<T>();

    @Override
    public void push(Object item) {
	stack.add((T) item);

    }

    @Override
    public Object pop() {
	if (stack.size() == 0) {
	    throw new NoSuchElementException();
	}
	T top = stack.get(stack.size() - 1);
	stack.remove(stack.size() - 1);
	return top;
    }

    @Override
    public Object top() throws NoSuchElementException {
	if (stack.size() == 0) {
	    throw new NoSuchElementException();
	}
	return stack.get(stack.size() - 1);
    }

    @Override
    public int size() {
	return stack.size();
    }

    @Override
    public boolean isEmpty() {
	return (stack.size() == 0);
    }

    @Override
    public void clear() {
	stack.clear();
    }

}

import java.util.Iterator;

/**
 * Creates most recently used list. Extends MyLinedList<T> 3/10/20
 * 
 * @author Hannah Babe
 */

public class MRUList1<T> extends MyLinkedList1<T> {

    public boolean contains(Object o) {
	Iterator<T> listIt = this.iterator();
	while (listIt.hasNext()) {
	    T temp = listIt.next();
	    if (temp == o) {
		listIt.remove();
		this.add(temp);
		return true;
	    }
	}
	return false;
    }

    public boolean add(T x) {
	add(0, x);
	return true;
    }

    public void add(int index, T x) {
	super.add(index, x);
    }

}
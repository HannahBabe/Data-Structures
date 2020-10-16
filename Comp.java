import java.util.Comparator;

/* determines ordering of elements x and y.
 * 
 * @author hannahbabe
 */

public class Comp implements Comparator<String> {

    public int compare(String x, String y) {
	if (x.length() < y.length()) {
	    return 1;
	} else if (x.length() == y.length()) {
	    return 0;
	} else
	    return -1;
    }
}

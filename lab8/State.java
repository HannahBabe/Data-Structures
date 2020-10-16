
/** represents a string of K characters
 * @author hannah babe
 */

import java.util.Random;
import java.util.TreeMap;

public class State {
    String str;
    int count;
    TreeMap<Character, Integer> suffixes;
    Random rand;

    public State(String sub) {
	this.str = sub;
	this.count = 0;
	suffixes = new TreeMap<Character, Integer>();
	rand = new Random();

    }

    public void add() {
	count += 1;
    }

    public void add(char c) {
	if (suffixes.containsKey(c)) {
	    int v = suffixes.get(c);
	    suffixes.put(c, v + 1);
	    // System.out.println("repeat");
	} else {
	    suffixes.put(c, 1);
	}
    }

    public String toString() {
	String s = String.format("%d %s:", count, str);
	for (Character ch : suffixes.keySet())
	    s += String.format(" (%c %d) ", ch, suffixes.get(ch));
	return s;
    }

    public char generate() {
	int r = rand.nextInt(count);
	for (char c : suffixes.keySet()) {
	    r -= suffixes.get(c);
	    if (r < 0) {
		return c;
	    }
	}
	return '#';
    }

}

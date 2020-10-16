
/** main class. has Hashmap that gets State information for any K-character String
 * Also has method to populate the HashMap from a text file.
 * 
 * @author hannah babe
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MarkovModel {
    int K;
    HashMap<String, State> model;

    public MarkovModel(int k, String fileName) {
	this.K = k;
	this.model = new HashMap<String, State>();
	train(fileName);
    }

    void train(String fileName) {
	try {
	    FileReader fr = new FileReader(fileName);
	    String s = "";
	    for (int i = 0; i < this.K; i++) {
		int c = fr.read();
		s += (char) c;
	    }
	    boolean done = false;
	    while (!done) {
		int c = fr.read();
		if (c == -1)
		    done = true;

		else {
		    if (model.containsKey(s)) {
			State state = model.get(s);
			state.add();
			state.add((char) c);

		    } else {
			State state = new State(s);
			state.add();
			state.add((char) c);
			model.put(s, state);
		    }
		    s += (char) c;
		    s = s.substring(1);
		}
	    }
	} catch (FileNotFoundException e) {
	    System.out.println("Bad file name");
	} catch (IOException e) {
	    System.out.println("IOEException");
	}
    }

    public void printModel() {
	System.out.printf("%d distinct states:\n", model.size());
	for (String s : model.keySet())
	    System.out.printf("   %s\n", model.get(s));
    }

    public String generateText(int M, String start) {
	String text = start;
	String s = start;
	// System.out.print(model);
	State state = null;
	boolean done = false;
	while (!done) {
	    if (text.length() == M) {
		done = true;
	    } else {
		if (model.containsKey(s)) {
		    state = model.get(s);
		} else {
		    s = start;
		    state = model.get(start);
		}
		char c = state.generate();
		text += c;
		s += c;
		s = s.substring(1);
	    }
	}
	return text;
    }

}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 * @author hannababe
 */
public class Concordance {
    int wordCount;
    int lineCount;
    TreeMap<String, ArrayList<Integer>> words;
    TreeSet<Character> punct;
    int MAX_LIST_SIZE = 15;

    public static void main(String[] args) {
	Concordance C = new Concordance("\"\'!#$%&()*+,-./:;<=>?@[]^_`{|}~", args[0]);
    }

    public Concordance(String punctuationString, String fName) {
	wordCount = 0;
	lineCount = 0;
	words = new TreeMap<String, ArrayList<Integer>>();
	punct = new TreeSet<Character>();
	for (int i = 0; i < punctuationString.length(); i++) { // loop through the characters of the punctuationString,
							       // add all characters to the TreeSet
	    punct.add(punctuationString.charAt(i));

	}
	Build((String) fName);
	Print();
	Search();
    }

    public void Build(String fName) {
	Scanner line = null;
	try {
	    line = new Scanner(new File(fName)); // opens scanner line for new file
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	while (line.hasNextLine()) {
	    Scanner w = new Scanner(line.nextLine()); // line is set to next line
	    if (w.hasNext()) {
		lineCount++; // adds 1 to lineCount
	    }
	    while (w.hasNext()) {
		String s = w.next(); // s set to next word
		String word = strip(s);
		if (word.length() > 0) { // don't want the empty string to be a key of your concordance
		    wordCount++;
		    if (words.containsKey(word)) { // adding the fact that String word appeared on line lineCount
			ArrayList<Integer> lineNums = words.get(word);
			lineNums.add(lineCount);
		    } else {
			ArrayList<Integer> lineNums = new ArrayList<Integer>();
			lineNums.add(lineCount);
			words.put(word, lineNums);
		    }
		}
	    }
	}
    }

    public String strip(String word) {
	int i = 0;
	int j = word.length() - 1;
	while (i <= j && punct.contains(word.charAt(i))) { // While i <=j and the ith character of the word is a
							   // punctuation mark
	    i++;
	}
	while (j >= i && punct.contains(word.charAt(j))) { // while j >= i and the jth character is a punctuation mark
							   // decrement j.
	    j--;
	}
	return word.substring(i, j + 1);
    }

    public void Print() {
	System.out.printf("%s lines, %s words, and %s unique words.", lineCount, wordCount, words.size());
	System.out.println();
	// System.out.println(words);
	Set<String> keys = words.keySet();
	ArrayList<String> keylist = new ArrayList<String>();
	keylist.addAll(keys);
	Collections.sort(keylist);
	for (String word : keylist) {
	    System.out.print(word + " ");
	    ArrayList<Integer> numInstances = words.get(word);
	    if (numInstances.size() <= MAX_LIST_SIZE) {
		for (int i = 0; i < numInstances.size(); i++)
		    System.out.printf("[%s]", numInstances.get(i));
		System.out.println();
	    } else {
		System.out.print("many entries");
	    }
	}
	System.out.println();

    }

    public void Search() {
	Scanner input = new Scanner(System.in);
	boolean done = false;
	while (!done) {
	    System.out.println("Please enter a word to search, or quit if you would like to exit: ");
	    String w = input.next();
	    if (w.contentEquals("quit")) {
		done = true;
		System.exit(0);
	    } else if (words.containsKey(w)) {
		System.out.println(words.get(w));
	    } else {
		System.out.println("File does not contain that word.");
	    }
	}

    }

}

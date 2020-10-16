import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester {

    public static void main(String[] args) {
	MyPriorityQueue<String> PQ = new MyPriorityQueue<String>(10, new Comp());

	Scanner line = null;
	try {
	    line = new Scanner(new File(args[0])); // opens scanner line for new file
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	while (line.hasNextLine()) {
	    String s = line.nextLine();
	    PQ.offer(s);
	    // Scanner w = new Scanner(line.nextLine()); // line is set to next line
	    // while (w.hasNext()) {
	    // String s = w.next();
	    // PQ.offer(s);

	}
	for (int i = 1; i <= 50; i++) {
	    System.out.println(i + " " + PQ.poll());
	}
    }
}

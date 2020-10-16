import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * application program
 * 
 * @author hannah babe
 */
public class TextGenerator {

    public static void main(String[] args) {
	try {
	    int K = Integer.parseInt(args[0]);
	    int M = Integer.parseInt(args[1]);
	    String fileName = args[2];
	    MarkovModel model = new MarkovModel(K, fileName);
	    String start = "";
	    FileReader fr = new FileReader(fileName);
	    for (int i = 0; i < K; i++) {
		int c = fr.read();
		start += (char) c;
	    }
	    String text = model.generateText(M, start);
	    System.out.print(text);

	} catch (FileNotFoundException e) {
	    System.out.println("Bad file name");
	} catch (IOException e) {
	    System.out.println("IOEException");
	}

    }

}

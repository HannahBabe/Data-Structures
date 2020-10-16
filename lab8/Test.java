
/**
 * Test class
 * 
 * @author hannahbabe
 *
 */
public class Test {
    public static void main(String[] args) {
	MarkovModel model = new MarkovModel(2, "markovTest.txt");
	model.printModel();
    }
}

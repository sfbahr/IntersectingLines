import java.io.File;
import java.util.Arrays;

/**
 * @author Anuj
 *
 */
public class IntersectingLines {

	private File outputFile;
	private static final int lineSegments = 2;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		RandomLines r = new RandomLines(lineSegments);
		Line[] lines = r.lines();
		Arrays.sort(lines);
		
	}

}

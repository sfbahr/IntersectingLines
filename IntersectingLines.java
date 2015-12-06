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
		
		long startTime1 = System.currentTimeMillis();
		//brute force algorithm
		long endTime1 = System.currentTimeMillis();
		long totalTime1 = endTime1 - startTime1;
		
		long startTime2 = System.currentTimeMillis();
		Arrays.sort(lines);
		//do more work here
		long endTime2 = System.currentTimeMillis();
		long totalTime2 = endTime1 - startTime1;
		
	}

}

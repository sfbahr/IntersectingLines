import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Anuj
 *
 */
public class IntersectingLines {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] lineSegments = {10, 100, 500, 1000, 2000, 10000, 20000};
		System.out.println("Line Segments (n) \tBrute Force \tOur Algorithm \tIntersections");
		for (int i = 0; i < lineSegments.length; i++) {
			RandomLines r = new RandomLines(lineSegments[i]);
			Line[] lines = r.lines();

			// Brute Force
			long startTime1 = System.currentTimeMillis();
			BruteForceAlgorithm bruteForce = new BruteForceAlgorithm(lines);
			LinkedList<Point> intersections = bruteForce.findIntersects();
			long endTime1 = System.currentTimeMillis();
			long totalTime1 = endTime1 - startTime1;
			
			// Our Algorithm
			long startTime2 = System.currentTimeMillis();
			Arrays.sort(lines);
			//do more work here
			long endTime2 = System.currentTimeMillis();
			long totalTime2 = endTime1 - startTime1;
			
			System.out.println(lineSegments[i] + "\t\t\t" + totalTime1 + "\t\t\t\t" + intersections);
			
		}
		
		
	}

}

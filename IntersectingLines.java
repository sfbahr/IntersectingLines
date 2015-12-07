import java.util.Arrays;
import java.util.List;

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
			
			Result res1 = bruteForce(lines);
			long totalTime1 = res1.getTotalTime();
			
			long startSortTime = System.currentTimeMillis();
			Arrays.sort(lines);
			long endSortTime = System.currentTimeMillis();
			long sortTime = startSortTime - endSortTime;
			
			Result res2 = proposedAlgorithm(lines);
			long totalTime2 = res2.getTotalTime();
			
			System.out.println(lineSegments[i] + "\t\t\t" + totalTime1 + "\t\t\t\t"
					+ res1.getIntersections() + res2.getIntersections());
			
		}		
	}
	
	private static Result bruteForce(Line[] lines) {
		long startTime = System.currentTimeMillis();
		BruteForceAlgorithm bruteForce = new BruteForceAlgorithm(lines);
		List<Point> intersections = bruteForce.findIntersects();
		long endTime = System.currentTimeMillis();
		return new Result(endTime - startTime, intersections);
	}
	
	private static Result proposedAlgorithm(Line[] lines) {
		long startTime = System.currentTimeMillis();
		//meat
		List<Point> intersections = null;
		long endTime = System.currentTimeMillis();
		return new Result(endTime - startTime, intersections);
	}
}

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Anuj, Sam, and Max
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
			
			Result res2 = proposedAlgorithm(lines, containsDuplicate(res1));
			long totalTime2 = res2.getTotalTime();
			
			System.out.println(lineSegments[i] + "\t\t\t" + totalTime1 + "\t\t"
					+ totalTime2 + "\t\t" + 
					res1.getIntersections() + res2.getIntersections());
			
		}		
	}
	
	private static boolean containsDuplicate(Result r) {
		List<Point> pointList = r.getIntersections();
		Point[] points = new Point[pointList.size()];
		points = pointList.toArray(points);
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				if (points[i].getX() == points[j].getX() && points[i].getY() == points[j].getY())
					return true;
			}
		}
		return false;
	}
	
	private static boolean containsDuplicate(Line[] lines) {
		for (int i = 0; i < lines.length; i++) {
			for (int j = i + 1; j < lines.length; j++) {
				if (lines[i].isVertical() && lines[j].isVertical() && lines[i].getGreater().getX() == lines[j].getGreater().getX())
					return true;
			}
		}
		return false;
	}
	
	private static Result bruteForce(Line[] lines) {
		long startTime = System.currentTimeMillis();
		BruteForceAlgorithm bruteForce = new BruteForceAlgorithm(lines);
		List<Point> intersections = bruteForce.findIntersects();
		long endTime = System.currentTimeMillis();
		return new Result(endTime - startTime, intersections);
	}
	
	private static Result proposedAlgorithm(Line[] lines, boolean hasDupes) {
		long startTime = System.currentTimeMillis();
		Arrays.sort(lines);
		ProposedAlgorithm proposed = new ProposedAlgorithm(lines, hasDupes);
		List<Point> intersections = proposed.findIntersects();
		long endTime = System.currentTimeMillis();
		return new Result(endTime - startTime, intersections);
	}
}

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 
 */

/**
 * @author Anuj
 *
 */
public class IntersectingLinesTest {

	private List<Point> intersectionsBF;
	private List<Point> intersectionsP;
	private Line[] list;
	Line horizontalLine3;
	Line horizontalLine4;
	Line verticalLine2;
	Line verticalLine3;
	Line verticalLine4;
	
	/**
     * This sets up the test class.
     */
	public void setUp() {
		Line horizontalLine1 = new Line(new Point(0, 2), new Point(25, 2));
		Line horizontalLine2 = new Line(new Point(10, 2), new Point(35, 2));
		Line verticalLine1 = new Line(new Point(11, 0), new Point(11, 25), true);
		
		Line[] lines = {horizontalLine1, horizontalLine2, verticalLine1};
		
		BruteForceAlgorithm bruteForce = new BruteForceAlgorithm(lines);
		intersectionsBF = bruteForce.findIntersects();
		
		Arrays.sort(lines);
		ProposedAlgorithm proposed = new ProposedAlgorithm(lines);
		intersectionsP = proposed.findIntersects();
		
		horizontalLine3 = new Line(new Point(0, 25), new Point(25, 25));
		horizontalLine4 = new Line(new Point(10, 25), new Point(35, 25));
		verticalLine2 = new Line(new Point(25, 0), new Point(25, 25), true);
		verticalLine3 = new Line(new Point(35, 0), new Point(35, 25), true);
		verticalLine4 = new Line(new Point(25, 0), new Point(25, 25), true);
		list = new Line[5];
		list[0] = horizontalLine3;
		list[1] = horizontalLine4;
		list[2] = verticalLine2;
		list[3] = verticalLine3;
		list[4] = verticalLine4;
		Arrays.sort(list);
	}

	@Test
	public void testBruteForce() {
		assertEquals(intersectionsBF.toString(), "[(11, 2), (11, 2)]");
	}
	
	@Test
	public void testProposed() {
		assertEquals(intersectionsP.toString(), "[(11, 2), (11, 2)]");
	}
	
	public void testOverapVertical() {
		Line horizontalLine1 = new Line(new Point(0, 30), new Point(25, 30));
		Line horizontalLine2 = new Line(new Point(0, 100), new Point(25, 100));
		Line verticalLine1 = new Line(new Point(10, 25), new Point(10, 50), true);
		Line verticalLine2 = new Line(new Point(10, 30), new Point(10, 55), true);
		
		Line[] lines = {horizontalLine1, horizontalLine2, verticalLine1, verticalLine2};
		Line[] lines2 = {horizontalLine1, verticalLine1};
		
		// Brute Force
		// With 4 lines
		BruteForceAlgorithm bruteForce = new BruteForceAlgorithm(lines);
		List<Point> intersectionsBF = bruteForce.findIntersects();
		assertEquals(intersectionsBF.toString(), "[(10, 30), (10, 30)]");
		// With 2 lines
		BruteForceAlgorithm bruteForce2 = new BruteForceAlgorithm(lines2);
		List<Point> intersectionsBF2 = bruteForce2.findIntersects();
		assertEquals(intersectionsBF2.toString(), "[(10, 30)]");
		
		// Proposed
		// With 4 lines is currently failing
		Arrays.sort(lines);
		ProposedAlgorithm proposed = new ProposedAlgorithm(lines);
		List<Point> intersectionsP = proposed.findIntersects();
		assertEquals(intersectionsP.toString(), "[(10, 30), (10, 30)]");
		// With 2 Lines is currently giving array out of bounds error
		Arrays.sort(lines2);
		ProposedAlgorithm proposed2 = new ProposedAlgorithm(lines2);
		List<Point> intersectionsP2 = proposed2.findIntersects();
		assertEquals(intersectionsP2.toString(), "[(10, 30)]");
	}
	
	@Test
	public void testSweepLine() {
		assertEquals(list[0], verticalLine2);
		assertEquals(list[1], verticalLine3);
		assertEquals(list[2], verticalLine4);
		assertEquals(list[3], horizontalLine4);
		assertEquals(list[4], horizontalLine3);
	}

}

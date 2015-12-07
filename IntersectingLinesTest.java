import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
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
	
	@Test
	public void testSweepLine() {
		assertEquals(list[0], verticalLine2);
		assertEquals(list[1], verticalLine3);
		assertEquals(list[2], verticalLine4);
		assertEquals(list[3], horizontalLine4);
		assertEquals(list[4], horizontalLine3);
	}

}

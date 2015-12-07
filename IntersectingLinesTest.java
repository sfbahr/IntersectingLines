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

	List<Point> intersectionsBF;
	List<Point> intersectionsP;
	
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
		// meat
		intersectionsP = null;
	}

	@Test
	public void testBruteForce() {
		assertEquals(intersectionsBF.toString(), "[(11, 2), (11, 2)]");
	}
	
	@Test
	public void testProposed() {
		assertEquals(intersectionsP.toString(), "[(11, 2), (11, 2)]");
	}

}

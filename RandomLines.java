import java.util.ArrayList;
import java.util.Random;

/**
 * @author Anuj
 *
 */
public class RandomLines {

	private Random r;
	private int numberOfLines = 0;
	private final int upperBound = 999975;

	/**
	 * 
	 */
	public RandomLines(int numberOfLines) {
		r = new Random();
		this.numberOfLines = numberOfLines;
	}

	public ArrayList<Line> lines() {
		ArrayList<Line> lineSegments = new ArrayList<Line>(numberOfLines*2);
		for (int i = 0; i < numberOfLines; i++) {
			int randomIntX = r.nextInt(upperBound);
			int randomIntY = r.nextInt(upperBound);
			Line horizontalLine = new Line(new Point(randomIntX, randomIntY),
					new Point(randomIntX + 25, randomIntY));
			lineSegments.add(horizontalLine);
			randomIntX = r.nextInt(upperBound);
			randomIntY = r.nextInt(upperBound);
			Line verticalLine = new Line(new Point(randomIntX, randomIntY),
					new Point(randomIntX, randomIntY + 25), true);
			lineSegments.add(verticalLine);
		}
		return lineSegments;
	}
}

import java.util.Random;

/**
 * @author Anuj
 *
 */
public class RandomLines {

	private Random r;
	private int numberOfLines = 0;
	private final int upperBound = 999975;
	//private final int upperBound = 100000;

	/**
	 * 
	 */
	public RandomLines(int numberOfLines) {
		r = new Random();
		this.numberOfLines = numberOfLines;
	}

	public Line[] lines() {
		Line[] lineSegments = new Line[numberOfLines*2];
		for (int i = 0; i < 2*numberOfLines; i+= 2) {
			int randomIntX = r.nextInt(upperBound);
			int randomIntY = r.nextInt(upperBound);
			Line horizontalLine = new Line(new Point(randomIntX, randomIntY),
					new Point(randomIntX + 25, randomIntY));
			lineSegments[i] = horizontalLine;
			randomIntX = r.nextInt(upperBound);
			randomIntY = r.nextInt(upperBound);
			Line verticalLine = new Line(new Point(randomIntX, randomIntY),
					new Point(randomIntX, randomIntY + 25), true);
			lineSegments[i+1] = verticalLine;
		}
		return lineSegments;
	}
}

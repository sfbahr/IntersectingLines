import java.io.File;

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
		System.out.println(r.lines().toString());
	}

}

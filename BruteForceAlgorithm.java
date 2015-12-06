import java.util.LinkedList;

/**
 * @author Anuj
 *
 */
public class BruteForceAlgorithm {

	private Line[] lines;
	
	/**
	 * 
	 */
	public BruteForceAlgorithm(Line[] lines) {
		this.lines = lines;
	}
	
	public LinkedList<Point> findIntersects() {
		LinkedList<Point> list = new LinkedList<Point>(); 
		for (int i = 0; i < lines.length; i++) {
			for (int j = i + 1; j < lines.length; j++) {
				Point p = lines[i].intersect(lines[j]);
				if (p != null) {
					list.add(p);
				}
			}
		}
		return list;
	}

}

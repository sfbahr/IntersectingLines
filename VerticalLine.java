/**
 * @author Anuj
 *
 */
public class VerticalLine extends Line {

	public VerticalLine(Point start, Point end, Boolean vertical) {
		super(start, end, vertical);
	}
	
	public VerticalLine(Line line) {
		super(line.getLesser(), line.getGreater(), true);
	}
	
	@Override
	public int compareTo(Line o) {
		if (this.getLesser().getX() == o.getLesser().getX()) {
			return 0;
		}
		else if (this.getLesser().getX() > o.getLesser().getX()) {
			return 1;
		}
		return -1;
	}

	public int getX() {
		return this.getLesser().getX();
	}
}

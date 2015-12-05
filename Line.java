/**
 * @author Anuj
 *
 */
public class Line {
	
	private Point start;
	private Point end;
	private Boolean vertical = false;
	
	public Line(Point start, Point end) {
		setStart(start);
		setEnd(end);
	}
	
	public Line(Point start, Point end, Boolean vertical) {
		setStart(start);
		setEnd(end);
		setVertical(vertical);
	}

	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}

	public Boolean getVertical() {
		return vertical;
	}

	public void setVertical(Boolean vertical) {
		this.vertical = vertical;
	}
	
}

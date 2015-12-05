/**
 * @author Anuj
 *
 */
public class Line implements Comparable<Line> {
	
	private Point lesser;
	private Point greater;
	private Boolean vertical = false;
	
	public Line(Point start, Point end) {
		setLesser(start);
		setGreater(end);
	}
	
	public Line(Point start, Point end, Boolean vertical) {
		setLesser(start);
		setGreater(end);
		setVertical(vertical);
	}

	public Point getLesser() {
		return lesser;
	}

	public void setLesser(Point start) {
		this.lesser = start;
	}

	public Point getGreater() {
		return greater;
	}

	public void setGreater(Point end) {
		this.greater = end;
	}

	public Boolean getVertical() {
		return vertical;
	}

	public void setVertical(Boolean vertical) {
		this.vertical = vertical;
	}
	
	@Override
	public String toString() {
		if (vertical) {
			return "VerticalLine: {" + lesser.toString() + greater.toString() + "}";
		}
		return "HorizontalLine: {" + lesser.toString() + greater.toString() + "}";
	}

	@Override
	public int compareTo(Line o) {
		int x = lesser.compareTo(o.getLesser());
		if (x == 0) {
			if (vertical && o.getVertical()) {
				return 0;
			}
			else if (vertical) {
				return 1;
			}
			else {
				return -1;
			}
		}
		else {
			return x > 0 ? 1 : -1;
		}
	}
	
}

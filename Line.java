/**
 * @author Anuj
 *
 */
public class Line implements Comparable<Line> {

	private Point lesser;
	private Point greater;
	private boolean vertical = false;

	public Line(Point start, Point end) {
		setLesser(start);
		setGreater(end);
	}

	public Line(Point start, Point end, boolean vertical) {
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

	public boolean isVertical() {
		return vertical;
	}

	public void setVertical(boolean vertical) {
		this.vertical = vertical;
	}

	@Override
	public String toString() {
		if (vertical) {
			return "VerticalLine: {" + lesser.toString() + greater.toString()
					+ "}";
		}
		return "HorizontalLine: {" + lesser.toString() + greater.toString()
				+ "}";
	}

	@Override
	public int compareTo(Line o) {
		int x = lesser.compareTo(o.getLesser());
		if (x == 0) {
			if (vertical && o.isVertical()) {
				return 0;
			} else if (vertical) {
				return 1;
			} else {
				return -1;
			}
		} else {
			return x > 0 ? 1 : -1;
		}
	}

	public Point intersect(Line o) {

		if (isVertical() == o.isVertical()) {
			return null;
		}
		else if (isVertical()) {
			int x1 = o.getLesser().getX();
			int x2 = o.getGreater().getX();
			int y = o.getLesser().getY();
			if (x1 <= lesser.getX() && lesser.getX() <= x2
					&& lesser.getY() <= y && y <= greater.getY()) {
				return new Point(lesser.getX(), y);
			}
		}
		else {
			int x = o.getLesser().getX();
			int y1 = o.getLesser().getY();
			int y2 = o.getGreater().getY();
			if (lesser.getX() <= x && x <= greater.getX()
					&& y1 <= lesser.getY() && lesser.getY() <= y2) {
				return new Point(x, greater.getY());
			}
		}
		return null;
	}

}

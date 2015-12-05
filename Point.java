/**
 * @author Anuj
 *
 */
public class Point implements Comparable<Point> {
	
	private int x;
	private int y;
	
	public Point() {
		setX(0);
		setY(0);
	}
	
	public Point(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int compareTo(Point o) {
		if (this.y == o.getY()) {
			return 0;
		}
		else {
			return this.y > o.getY() ? 1 : -1;
		}
	}
	
}

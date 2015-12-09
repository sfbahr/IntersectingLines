
public class Event implements Comparable<Event>{
	Line line;	
	Boolean isLesser; //this is set to true for horizontal lines in ProposedAlgorithm
	Boolean isVertical;
	int y;
	
	public Event(Line l, Boolean isL) {
		line = l;
		isLesser = isL;
		isVertical = l.isVertical();
		if (isLesser)
			y = line.getLesser().getY();
		else
			y = line.getGreater().getY();
	}

	@Override
	public int compareTo(Event e) {
		int comp = y - e.getY();
		if (comp == 0) {
			if (isVertical && !e.isVertical()) {
				return -1;
			}
			else if (!isVertical && e.isVertical()) {
				return 1;
			}
		}
		return comp;
	}
	
	public Line getLine() {
		return line;
	}
	
	public Boolean isLesser() {
		return isLesser;
	}
	
	public Boolean isVertical() {
		return isVertical;
	}
	
	public int getY() {
		return y;
	}

}

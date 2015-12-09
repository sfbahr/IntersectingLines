import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ProposedAlgorithm {
	private BPTree tree;
	private Event[] events;
	
	public ProposedAlgorithm(Line[] lines) {
		this(lines, true);
	}
	
	public ProposedAlgorithm(Line[] lines, boolean hasDupes) {
		this.tree = new BPTree(hasDupes);
		this.events = createEvents(lines);
		Arrays.sort(events);
	}
	
	public Event[] createEvents(Line[] l) {
		Event[] evts = new Event[l.length*3/2];
		int i = 0;
		for (Line currentLine: l) {
			if (currentLine.isVertical()) {
				evts[i] = new Event(currentLine, true);
				i++;
				evts[i] = new Event(currentLine, false);
				i++;
			}
			else {
				evts[i] = new Event(currentLine, true); //it doesn't really matter what the second parameter is for a horizontal line
				i++;
			}
		}
		return evts;
	}
	
	public LinkedList<Point> findIntersects() {
		LinkedList<Point> list = new LinkedList<Point>();
		for (int i = 0; i < events.length; i++) {
			
			Event curEvent = events[i];
			
			if (curEvent.isVertical()) {
				VerticalLine vertLine = new VerticalLine(curEvent.getLine());
				KVPair<VerticalLine> lineWrapper = new KVPair<>(vertLine);
				if (curEvent.isLesser()) {
					tree.insert(lineWrapper);
				}
				else {
					tree.delete(lineWrapper);
				}
			}
			else {
				List<Point> intersections = tree.findRange(curEvent.getLine().getLesser().getX(), 
						curEvent.getLine().getGreater().getX(),
						curEvent.getY());
				list.addAll(intersections);
			}
		}
		return list;
	}
}

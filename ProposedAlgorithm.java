import java.util.Arrays;
import java.util.LinkedList;

public class ProposedAlgorithm {
	private Line[] lines;
	private BPTree tree;
	private Event[] events;
	
	public ProposedAlgorithm(Line[] lines) {
		this.lines = lines;
		this.tree = new BPTree(true);
		this.events = createEvents(lines);
		Arrays.sort(events);
	}
	
	public Event[] createEvents(Line[] l) {
		events = new Event[l.length*2];
		int i = 0;
		for (Line currentLine: l) {
			if (currentLine.isVertical()) {
				events[i] = new Event(currentLine, true);
				i++;
				events[i] = new Event(currentLine, false);
				i++;
			}
			else {
				events[i] = new Event(currentLine, true); //it doesn't really matter what the second parameter is for a horizontal line
				i++;
			}
		}
		return events;
	}
	
	public LinkedList<Point> findIntersects() {
		LinkedList<Point> list = new LinkedList<Point>();
		for (int i = 0; i < lines.length; i++) {
			
			Line curLine = lines[i];
			
			if (curLine.isVertical()) {
				VerticalLine vertLine = (VerticalLine) curLine;
				
				tree.insert(new KVPair<VerticalLine>(vertLine));
			}
			else {
				
			}
		}
		return list;
	}
}

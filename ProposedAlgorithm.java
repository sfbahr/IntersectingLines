import java.util.LinkedList;

public class ProposedAlgorithm {
	private Line[] lines;
	private BPTree tree;
	
	public ProposedAlgorithm(Line[] lines) {
		this.lines = lines;
		this.tree = new BPTree(true);
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

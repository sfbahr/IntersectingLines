import java.util.List;

public class Result {
	private long totalTime;
	private List<Point> intersections;
	
	public Result(long totalTime, List<Point> intersections) {
		this.totalTime = totalTime;
		this.intersections = intersections;
	}
	
	public long getTotalTime(){
		return totalTime;
	}
	
	public List<Point> getIntersections(){
		return intersections;
	}
}

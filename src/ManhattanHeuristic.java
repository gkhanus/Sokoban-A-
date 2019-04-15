import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class ManhattanHeuristic implements Heuristic {

	public void score(HaritaDurumu state) {
		Set<Point> goals = state.hedefiGetir();
		Set<Point> boxes = state.kutuyuGetir();
		
		Set<Point> kesisim = new HashSet<Point>(goals);
		kesisim.retainAll(boxes);
		goals.removeAll(kesisim);
		boxes.removeAll(kesisim);

		int cost = 0;
		for (Point box : boxes) {
			int minCost = Integer.MAX_VALUE;
			for (Point goal : goals) {
				int dist = ManhattanUzaklikHesapla(box, goal);
				if (dist < minCost)
					minCost = dist;
			}
			cost += minCost;
		}
		state.maliyetHesapla(cost);	
	}
	
	private static int ManhattanUzaklikHesapla(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
}

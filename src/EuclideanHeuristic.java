
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ErdoganCIVIL
 */
public class EuclideanHeuristic implements Heuristic{
 	public void score(HaritaDurumu state) {
		Set<Point> goals = state.hedefiGetir();
		Set<Point> boxes = state.kutuyuGetir();
		
		Set<Point> kesisim = new HashSet<Point>(goals);
		kesisim.retainAll(boxes);
		goals.removeAll(kesisim);
		boxes.removeAll(kesisim);

		int cost = 0;
		for (Point box : boxes) {
			double minMarginalCost = Integer.MAX_VALUE;
			for (Point goal : goals) {
				double dist = EuclideanUzaklikHesapla(box, goal);
				if (dist < minMarginalCost)
					minMarginalCost = dist;
			}
			cost += minMarginalCost;
		}
		state.maliyetHesapla(cost);	
	}
	
	private static double EuclideanUzaklikHesapla(Point p1, Point p2) {
		return Math.sqrt(((p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y)));
	}   
}

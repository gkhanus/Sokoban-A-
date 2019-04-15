import java.util.ArrayList;
import java.util.PriorityQueue;


public class AYıldız extends HaritaCozucu {
	private Heuristic heuristic;
	
	private AYıldız(HaritaDurumu baslangicDurumu) {
		super(baslangicDurumu);
		kuyruk = new PriorityQueue<HaritaDurumu>();
	}
	
	public AYıldız(HaritaDurumu initialBoard, Heuristic heuristic) {
		this(initialBoard);
		this.heuristic = heuristic;
	}

	@Override
	protected void searchFunction(ArrayList<HaritaDurumu> validMoves) {
		for (HaritaDurumu move : validMoves) {
			geriyeDonus.put(move, simdikiDurum);
			heuristic.score(move);
			kuyruk.add(move);
		}
	}
}

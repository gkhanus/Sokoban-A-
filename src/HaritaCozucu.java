import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public abstract class HaritaCozucu {
	protected HaritaDurumu simdikiDurum;
	protected HashSet<HaritaDurumu> gidilenDugum;
	protected HashMap<HaritaDurumu, HaritaDurumu> geriyeDonus;
	protected Queue<HaritaDurumu> kuyruk;
	
	private long baslangicZamani;
	private long bitisZamani;
	private int oncedenGidilen;
	
	public HaritaCozucu(HaritaDurumu baslangicDurumu) {
		simdikiDurum = baslangicDurumu;
		gidilenDugum = new HashSet<HaritaDurumu>();
		geriyeDonus = new HashMap<HaritaDurumu, HaritaDurumu>();
		baslangicZamani = bitisZamani = -1;
		oncedenGidilen = 0;
	}
	
	public String arama() {
		sureyiBaslat();
		aramayiBaslat();
		while (!kuyruk.isEmpty()) {
			simdikiDurum = kuyruk.poll();
			if (gidilenDugum.contains(simdikiDurum))
				oncedenGidilen++;
			gidilenDugum.add(simdikiDurum);

			if (simdikiDurum.cozulduMu()) {
				System.out.println(simdikiDurum);
				String solution = geriDonusHamlesi(simdikiDurum);
				sureyiDurdur();
				return solution;
			}

			ArrayList<HaritaDurumu> gecerliHamle = gecerliHamleyiGetir();
			searchFunction(gecerliHamle);
		}
		return "Çözüm bulunamadı!";
	}
	

	protected void aramayiBaslat() {
		kuyruk.add(simdikiDurum);
	}
	
	protected abstract void searchFunction(ArrayList<HaritaDurumu> validMoves);
	
	protected ArrayList<HaritaDurumu> gecerliHamleyiGetir() {
        ArrayList<HaritaDurumu> validMoves = new ArrayList<HaritaDurumu>(4);
        addIfValid(validMoves, Yon.UP);
        addIfValid(validMoves, Yon.RIGHT);
        addIfValid(validMoves, Yon.DOWN);
        addIfValid(validMoves, Yon.LEFT);
        return validMoves;
    }
	
    protected String geriDonusHamlesi(HaritaDurumu sonDurum) {
        
		LinkedList<Character> moveStack = new LinkedList<Character>();
		HaritaDurumu current = sonDurum;
		while (current.getDirectionTaken() != null) {
			char move = Yon.directionToChar(current.getDirectionTaken());
			moveStack.push(move);
            current = geriyeDonus.get(current);
		}
		
		StringBuilder solution = new StringBuilder();
		String delim = "";
		for (Character move : moveStack) {
			solution.append(delim);
			solution.append(move);
			delim = ", ";
		}
		return solution.toString();
	}
    
    protected void sureyiBaslat() {
    	baslangicZamani = System.currentTimeMillis();
    	bitisZamani = System.currentTimeMillis();
    }
    
    protected void sureyiDurdur() {
    	bitisZamani = System.currentTimeMillis();
    }
    
    public long gecenSureyiBul() {
    	return bitisZamani - baslangicZamani;
    }
    
    public int gidilenDugumleriHesapla() {
    	return gidilenDugum.size();
    }
    
    public int DallanmaBoyutunuHesapla() {
    	return kuyruk.size();
    }
    
    public int toplamGidilenDugumleriHesapla() {
    	return oncedenGidilenleriHesapla() + DallanmaBoyutunuHesapla() + gidilenDugumleriHesapla();
    }
    
    public int oncedenGidilenleriHesapla() {
    	return oncedenGidilen;
    }

	private void addIfValid(ArrayList<HaritaDurumu> hamle, Point yon) {
	    if (simdikiDurum.hareketEdebilirMi(yon)) {
	        HaritaDurumu newState = simdikiDurum.hamleyiGetir(yon);
	        if (!gidilenDugum.contains(newState))
	            hamle.add(newState);
	    }
	}

}

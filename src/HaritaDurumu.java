import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;


public class HaritaDurumu implements Comparable<HaritaDurumu> {
	
        static int width;
        static int height;
        
	public static final byte PLAYER = 1 << 0;
	public static final byte WALL = 1 << 1;
	public static final byte BOX = 1 << 2;
	public static final byte GOAL = 1 << 3;
        
	private static HashMap<Character, Byte> charToField;
	private static HashMap<Byte, Character> fieldToChar;
	static {
		charToField = new HashMap<Character, Byte>();
		charToField.put('#', WALL);
		charToField.put('.', GOAL);
		charToField.put('@', PLAYER);
		charToField.put('+', (byte) (PLAYER | GOAL));
		charToField.put('$', BOX);
		charToField.put('*', (byte) (BOX | GOAL));
		charToField.put(' ', (byte) 0);
		
		fieldToChar = new HashMap<Byte, Character>();
		for (Entry<Character, Byte> entry : charToField.entrySet()) {
			fieldToChar.put(entry.getValue(), entry.getKey());
		}
	}
	
	private byte[][] harita;
	private Point oyuncu;
	private Set<Point> hedef;
	private Set<Point> kutu;
	private Point gidilecekYon;	
	private int maliyet;
	
	public HaritaDurumu(byte[][] harita, Point oyuncu, Set<Point> hedef,
			Set<Point> kutu) {
		this(harita, oyuncu, hedef, kutu, null);
	}

	public HaritaDurumu(byte[][] harita, Point oyuncu, Set<Point> hedef, 
			Set<Point> kutu, Point gidilecekYon) {
		this.harita = harita;
		this.oyuncu = oyuncu;
		this.hedef = hedef;
		this.kutu = kutu;
		this.gidilecekYon = gidilecekYon;
		maliyet = 0;
	}
	
	public boolean cozulduMu() {
		for (Point p : hedef) {
			if (!(pointHas(p.x, p.y, GOAL) && pointHas(p.x, p.y, BOX))) {	
				return false;
			}
		}
		return true;
	}
	
	public boolean hareketEdebilirMi(Point yon) {
		Point newPos = new Point(oyuncu.x + yon.x, oyuncu.y + yon.y);
		Point oneOutPos = new Point(newPos.x + yon.x, newPos.y + yon.y);
		if (pointHas(newPos, BOX)) {
			if (pointHas(oneOutPos, WALL) || pointHas(oneOutPos, BOX))
				return false;
			else
				return true;
		}
		else if (pointHas(newPos, WALL))
			return false;
		else
			return true;
	}
	
	public HaritaDurumu hamleyiGetir(Point gidilecekYon) {
		Point yeniKonum = new Point(oyuncu.x + gidilecekYon.x, oyuncu.y + gidilecekYon.y);
		Point oneOutPos = new Point(yeniKonum.x + gidilecekYon.x, yeniKonum.y + gidilecekYon.y);
		Set<Point> yeniKutu = kutu;
		
		byte[][] yeniHarita = new byte[harita.length][];
		for (int i = 0; i < yeniHarita.length; i++)
			yeniHarita[i] = harita[i].clone();
		
		byte playerBitField = yeniHarita[oyuncu.x][oyuncu.y];
		yeniHarita[oyuncu.x][oyuncu.y] = toggleField(playerBitField, PLAYER); 
		
		byte newPlayerBitField = yeniHarita[yeniKonum.x][yeniKonum.y];
		yeniHarita[yeniKonum.x][yeniKonum.y] = toggleField(newPlayerBitField, PLAYER); 
		
		if (pointHas(yeniKonum, BOX)) {
			byte oldBoxBitfield = yeniHarita[yeniKonum.x][yeniKonum.y];
			byte newBoxBitfield = yeniHarita[oneOutPos.x][oneOutPos.y];
			yeniHarita[yeniKonum.x][yeniKonum.y] = toggleField(oldBoxBitfield, BOX);
			yeniHarita[oneOutPos.x][oneOutPos.y] = toggleField(newBoxBitfield, BOX);
			yeniKutu = new HashSet<Point>(kutu);
			yeniKutu.remove(yeniKonum);
			yeniKutu.add(oneOutPos);
		}

		return new HaritaDurumu(yeniHarita, yeniKonum, hedef, yeniKutu, gidilecekYon);
	}
	
	public boolean nextMoveHas(byte field, Point direction) {
		Point yeniKonum = new Point(oyuncu.x + direction.x, oyuncu.y + direction.y);
		return pointHas(yeniKonum, field);
	}
	
	public byte[][] haritayiGetir() {
		return harita;
	}

	public Point getDirectionTaken() {
		return gidilecekYon;
	}

	public void maliyetHesapla(int maliyet) {
		this.maliyet = maliyet;
	}

	public int maliyetiGetir() {
		return maliyet;
	}
	
	public Set<Point> hedefiGetir() {
		return new HashSet<Point>(hedef);
	}
	
	public Set<Point> kutuyuGetir() {
		return new HashSet<Point>(kutu);
	}

	public int compareTo(HaritaDurumu other) {
		if (this.maliyetiGetir() < other.maliyetiGetir())
			return -1;
		else if (this.maliyetiGetir() > other.maliyetiGetir())
			return 1;
		else
			return 0;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int satir = 0; satir < harita.length; satir++) {
			for (int sutun = 0; sutun < harita[0].length; sutun++) {
				builder.append(fieldToChar.get(harita[satir][sutun]));
			}
			builder.append('\n');
		}
		return builder.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(harita);
		result = prime * result + ((hedef == null) ? 0 : hedef.hashCode());
		result = prime * result + ((oyuncu == null) ? 0 : oyuncu.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HaritaDurumu other = (HaritaDurumu) obj;
		if (!Arrays.deepEquals(harita, other.harita))
			return false;
		if (hedef == null) {
			if (other.hedef != null)
				return false;
		} else if (!hedef.equals(other.hedef))
			return false;
		if (oyuncu == null) {
			if (other.oyuncu != null)
				return false;
		} else if (!oyuncu.equals(other.oyuncu))
			return false;
		return true;
	}

	private boolean pointHas(int satir, int sutun, byte field) {
		return (harita[satir][sutun] & field) == field;
	}
	
	private boolean pointHas(Point konum, byte field) {
		return pointHas(konum.x, konum.y, field);
	}
	
	private byte toggleField(byte bitfield, byte field) {
		return (byte) (bitfield ^ field);
	}

	public static HaritaDurumu haritaOlustur(String boardInput) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(boardInput));
		width = Integer.parseInt(reader.readLine());
		height = Integer.parseInt(reader.readLine());
		byte[][] elemanYerleri = new byte[height][width];
		Point oyuncu = new Point();
		Set<Point> hedef = new HashSet<Point>();
		Set<Point> kutu = new HashSet<Point>();

		String okunanSatir;
		for (int satirSay = 0; satirSay < height && (okunanSatir = reader.readLine()) != null; satirSay++) {
			for (int sutunSay = 0; sutunSay < width && sutunSay < okunanSatir.length(); sutunSay++) {
				byte field = charToField.get(okunanSatir.charAt(sutunSay));
				elemanYerleri[satirSay][sutunSay] = field;
				if ((field & PLAYER) == PLAYER)
					oyuncu = new Point(satirSay, sutunSay);
				if ((field & GOAL) == GOAL)
					hedef.add(new Point(satirSay, sutunSay));
				if ((field & BOX) == BOX)
					kutu.add(new Point(satirSay, sutunSay));
			}
		}

		reader.close();
		return new HaritaDurumu(elemanYerleri, oyuncu, hedef, kutu);
	}
}

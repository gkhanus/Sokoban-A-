import java.awt.Point;

public class Yon {
	public static final Point UP = new Point(-1, 0);
	public static final Point RIGHT = new Point(0, 1);
	public static final Point DOWN = new Point(1, 0);
	public static final Point LEFT = new Point(0, -1);
	
	private Yon() {}
	
	public static char directionToChar(Point direction) {
		if (direction.equals(UP))
			return 'u';
		else if (direction.equals(RIGHT))
			return 'r';
		else if (direction.equals(DOWN))
			return 'd';
		else if (direction.equals(LEFT))
			return 'l';
		else
			throw new IllegalStateException("Gidilemeyen yön: " 
					+ direction);
	}
}

import java.util.*;

public abstract class Line {

	protected ArrayList<Square> squares = new ArrayList<Square>();
	protected int id;
	protected int[] numbers;
	protected int size;
	
	public Line(int id, int[] numbers) {
		this.id = id;
		this.numbers = numbers;
		size = numbers.length;
	}
	
	public ArrayList<Square> getSquares() {
		return squares;
	}
	
	public abstract void addSquare(Square s);
	
}

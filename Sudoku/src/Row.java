import java.util.*;

public class Row {

	private ArrayList<Square> squares = new ArrayList<Square>();
	private int id;
	
	public Row(int id) {
		this.id = id;
	}
	
	public void elim(int num) {
		for(Square s: squares) {
			s.elim(num);
		}
	}
	
	public void addSquare(Square s) {
		squares.add(s);
		s.setRow(this);
	}
	
	public ArrayList<Square> getSquares() {
		return squares;
	}
	
}

import java.util.ArrayList;

public class Column {

	private ArrayList<Square> squares = new ArrayList<Square>();
	private int id;
	
	public Column(int id) {
		this.id = id;
	}
	
	public void elim(int num) {
		for(Square s: squares) {
			s.elim(num);
		}
	}
	
	public void addSquare(Square s) {
		squares.add(s);
		s.setCol(this);
	}
	
}

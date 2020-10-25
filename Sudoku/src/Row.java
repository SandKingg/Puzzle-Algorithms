public class Row extends Container {

	public Row(int id) {
		super(id);
	}

	@Override
	public void addSquare(Square s) {
		squares.add(s);
		s.setRow(this);
	}
	
}

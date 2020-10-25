public class Column extends Container {

	public Column(int id) {
		super(id);
	}

	@Override
	public void addSquare(Square s) {
		squares.add(s);
		s.setCol(this);
	}
	
}

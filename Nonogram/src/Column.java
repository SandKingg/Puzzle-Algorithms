public class Column extends Line {

	public Column(int id, int[] numbers) {
		super(id,numbers);
	}

	@Override
	public void addSquare(Square s) {
		squares.add(s);
		s.setCol(this);
	}
	
}
public class Row extends Line {

	public Row(int id, int[] numbers) {
		super(id,numbers);
	}

	@Override
	public void addSquare(Square s) {
		squares.add(s);
		s.setRow(this);
	}
	
}

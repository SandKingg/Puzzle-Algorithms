public class Row extends Line {

	public Row(int id, int[] numbers, int size) {
		super(id,numbers,size);
	}

	@Override
	public void addSquare(Square s) {
		squares.add(s);
		s.setRow(this);
	}
	
}

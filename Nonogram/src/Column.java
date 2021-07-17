public class Column extends Line {

	public Column(int id, int[] numbers, int size) {
		super(id,numbers,size);
	}

	@Override
	public void addSquare(Square s) {
		squares.add(s);
		s.setCol(this);
	}
	
}
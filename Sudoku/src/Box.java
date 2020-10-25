public class Box extends Container {

	public Box(int id) {
		super(id);
	}

	@Override
	public void addSquare(Square s) {
		squares.add(s);
		s.setBox(this);
	}
	
}

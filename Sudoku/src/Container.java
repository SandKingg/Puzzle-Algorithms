import java.util.*;

public abstract class Container {

	protected ArrayList<Square> squares = new ArrayList<Square>();
	private int id;
	
	public Container(int id) {
		this.id = id;
	}
	
	public void elim(int num) {
		for(Square s: squares) {
			s.elim(num);
		}
	}

	public void check() {
		HashMap<Integer,Integer> occurs = new HashMap<Integer,Integer>();
		for(Square s: squares) {
			if(s.getNum() == -1) {
				for(int i: s.getPoss()) {
					if(occurs.containsKey(i)) {
						occurs.put(i, occurs.get(i)+1);
					} else {
						occurs.put(i, 1);
					}
				}
			}
		}
		for(int i: occurs.keySet()) {
			if(occurs.get(i)==1) {
				for(Square s: squares) {
					if(s.getPoss().contains(i)) {
						s.setNum(i);
					}
				}
			}
		}
	}
	
	public boolean hintCheck() {
		HashMap<Integer,Integer> occurs = new HashMap<Integer,Integer>();
		for(Square s: squares) {
			if(s.getNum() == -1) {
				for(int i: s.getPoss()) {
					if(occurs.containsKey(i)) {
						occurs.put(i, occurs.get(i)+1);
					} else {
						occurs.put(i, 1);
					}
				}
			}
		}
		for(int i: occurs.keySet()) {
			if(occurs.get(i)==1) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Square> getSquares() {
		return squares;
	}
	
	public abstract void addSquare(Square s);
	
}

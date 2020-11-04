import java.util.*;

public abstract class Container {

	protected ArrayList<Square> squares = new ArrayList<Square>();
	protected int id;
	
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

	public boolean existExcept(int num, ArrayList<Square> sqs) {
		for(Square s: squares) {
			if(!sqs.contains(s) && s.getPoss().contains(num)) {
				return false;
			}
		}
		return true;
	}
	
	public void elimExcept(int num, ArrayList<Square> sqs) {
		for(Square s: squares) {
			if(!sqs.contains(s)) {
				s.elim(num);
			}
		}
	}
	
	protected HashSet<Integer> findDups(ArrayList<Integer> nums) {
		HashSet<Integer> hs = new HashSet<Integer>();
		HashSet<Integer> dups = new HashSet<Integer>();
		
		for(int i:nums) {
			if(hs.contains(i)) {
				dups.add(i);
			} else {
				hs.add(i);
			}
		}
		
		return dups;
	}
	
	public ArrayList<Square> getSquares() {
		return squares;
	}
	
	public abstract void addSquare(Square s);
	
	public abstract void checkPairs();
	
}

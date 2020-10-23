import java.util.*;

public class Grid {

	private HashMap<String,Square> squares = new HashMap<String,Square>();
	
	public Grid() {
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				String s = String.valueOf(i)+String.valueOf(j);
				squares.put(s,new Square(i,j,new ArrayList<Integer>(Arrays.asList(1,2,3,4,5))));
			}
		}
	}
	
	public void assertNum(String ref, int num) {
		squares.get(ref).setNum(num);
		squares.get(ref).alert();
		elimRow(ref.substring(0,1),num);
		elimCol(ref.substring(1),num);
	}
	
	//Asserts ref1 < ref2
	public void assertLess(String ref1, String ref2) {
		squares.get(ref1).lessThan(squares.get(ref2));
		squares.get(ref2).greaterThan(squares.get(ref1));
	}
	
	public void assertNot(String ref, int num) {
		squares.get(ref).elim(num);
	}
	
	public void elimRow(String row, int num) {
		for(int i=1;i<=5;i++) {
			String ref = row+i;
			squares.get(ref).elim(num);
		}
	}
	
	public void elimCol(String col, int num) {
		for(int i=1;i<=5;i++) {
			String ref = i+col;
			squares.get(ref).elim(num);
		}
	}
	
	public void checkRow(String row) {
		HashMap<Integer,Integer> occurs = new HashMap<Integer,Integer>();
		for(int i=1;i<=5;i++) {
			String ref = row+i;
			if(squares.get(ref).getNum() == -1) {
				for(int x:squares.get(ref).getPoss()) {
					if(occurs.containsKey(x)) {
						occurs.put(x, occurs.get(x)+1);
					} else {
						occurs.put(x, 1);
					}
				}
			}
		}
		for(int x: occurs.keySet()) {
			if(occurs.get(x) == 1) {
				for(int i=1;i<=5;i++) {
					String ref = row+i;
					if(squares.get(ref).getPoss().contains(x)) {
						assertNum(ref,x);
						break;
					}
				}
			}
		}
	}
	
	public void checkCol(String col) {
		HashMap<Integer,Integer> occurs = new HashMap<Integer,Integer>();
		for(int i=1;i<=5;i++) {
			String ref = i+col;
			if(squares.get(ref).getNum() == -1) {
				for(int x:squares.get(ref).getPoss()) {
					if(occurs.containsKey(x)) {
						occurs.put(x, occurs.get(x)+1);
					} else {
						occurs.put(x, 1);
					}
				}
			}
		}
		for(int x: occurs.keySet()) {
			if(occurs.get(x) == 1) {
				for(int i=1;i<=5;i++) {
					String ref = i+col;
					if(squares.get(ref).getPoss().contains(x)) {
						assertNum(ref,x);
						break;
					}
				}
			}
		}
	}
	
	public void fullCheck() {
		for(int i=1;i<=5;i++) {
			checkRow(String.valueOf(i));
		}
		for(int i=1;i<=5;i++) {
			checkCol(String.valueOf(i));
		}
		for(String s:squares.keySet()) {
			squares.get(s).checkPoss();
			if(!squares.get(s).checkAlert()) {
				squares.get(s).alert();
				int num = squares.get(s).getNum();
				elimRow(s.substring(0,1),num);
				elimCol(s.substring(1),num);
			}
		}
	}
	
	public Square getSquare(String ref) {
		return squares.get(ref);
	}
	
	public void printNums() {
		for(String s:squares.keySet()) {
			int num = squares.get(s).getNum();
			if(num != -1) {
				System.out.println(s + ':' + num);
			}
		}
	}
	
	public void printPoss() {
		for(String s:squares.keySet()) {
			System.out.println(s + ':' + squares.get(s).getPoss());
		}
	}
}

import java.util.*;

public class Grid {

	private int size; //Size of the grid is size*size squares
	private HashMap<String,Square> squares = new HashMap<String,Square>(); //Stores a map of references to squares
	
	public Grid(int size) {
		this.size = size;
		
		
		//Create a series of squares, all with initial possibilities of any number
		for(int i=1;i<=size;i++) {
			for(int j=1;j<=size;j++) {
				String s = String.valueOf(i)+String.valueOf(j);

				//Since we have one of each number per row, initialList contains the numbers 1 to size
				ArrayList<Integer> initialList = new ArrayList<Integer>();
				for(int k=1;k<=size;k++) {
					initialList.add(k);
				}
				squares.put(s,new Square(i,j,initialList));
			}
		}
	}
	
	//Allow the user to specify that a square contains a number
	public void assertNum(String ref, int num) {
		squares.get(ref).setNum(num);
		squares.get(ref).alert();
		elimRow(ref.substring(0,1),num);
		elimCol(ref.substring(1),num);
	}
	
	//The user specifies that ref1 < ref2
	public void assertLess(String ref1, String ref2) {
		squares.get(ref1).lessThan(squares.get(ref2));
		squares.get(ref2).greaterThan(squares.get(ref1));
	}
	
	//The user has worked out that a certain square cannot be a certain number
	public void assertNot(String ref, int num) {
		squares.get(ref).elim(num);
	}
	
	//Eliminates a possibility from every square in a row
	public void elimRow(String row, int num) {
		for(int i=1;i<=size;i++) {
			String ref = row+i;
			squares.get(ref).elim(num);
		}
	}
	
	//Eliminates a possibility from every square in a column
	public void elimCol(String col, int num) {
		for(int i=1;i<=size;i++) {
			String ref = i+col;
			squares.get(ref).elim(num);
		}
	}
	
	//If a certain number can only go in one square for a given row, then that square must contain that number
	public void checkRow(String row) {
		HashMap<Integer,Integer> occurs = new HashMap<Integer,Integer>();
		for(int i=1;i<=size;i++) {
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
				for(int i=1;i<=size;i++) {
					String ref = row+i;
					if(squares.get(ref).getPoss().contains(x)) {
						assertNum(ref,x);
						break;
					}
				}
			}
		}
	}

	//If a certain number can only go in one square for a given column, then that square must contain that number
	public void checkCol(String col) {
		HashMap<Integer,Integer> occurs = new HashMap<Integer,Integer>();
		for(int i=1;i<=size;i++) {
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
				for(int i=1;i<=size;i++) {
					String ref = i+col;
					if(squares.get(ref).getPoss().contains(x)) {
						assertNum(ref,x);
						break;
					}
				}
			}
		}
	}
	
	//Checks all rows, then checks all columns, then checks all squares and resolves any unalerted solves
	public void fullCheck() {
		for(int i=1;i<=size;i++) {
			checkRow(String.valueOf(i));
		}
		for(int i=1;i<=size;i++) {
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
	
	//Print solved squares
	public void printNums() {
		for(String s:squares.keySet()) {
			int num = squares.get(s).getNum();
			if(num != -1) {
				System.out.println(s + ':' + num);
			}
		}
	}
	
	//Print possibilities for all squares
	public void printPoss() {
		for(String s:squares.keySet()) {
			System.out.println(s + ':' + squares.get(s).getPoss());
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int i=1;i<=size;i++) {
			for(int j=1;j<=size;j++) {
				String ref = String.valueOf(i)+String.valueOf(j);
				if(squares.get(ref).getNum()==-1) {
					sb.append("[ ]");
				} else {
					sb.append("["+squares.get(ref).getNum()+"]");
				}
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}

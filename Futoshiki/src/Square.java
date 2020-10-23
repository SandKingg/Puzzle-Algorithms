import java.util.*;

public class Square {

	private boolean alerted = false;
	private int number = -1;
	private int row = -1;
	private int col = -1;
	private ArrayList<Integer> possibilities = new ArrayList<Integer>();
	private ArrayList<Square> lessThan = new ArrayList<Square>();
	private ArrayList<Square> greaterThan = new ArrayList<Square>();
	
	public Square(int row, int col, ArrayList<Integer> possibilities) {
		this.possibilities = possibilities;
		this.row = row;
		this.col = col;
	}
	
	public void checkPoss() {
		if(number == -1) {
			for(Square s:lessThan) {
				int greatest = Collections.max(s.getPoss());
				possibilities.removeIf(x -> (x >= greatest));
			}
			
			for(Square s:greaterThan) {
				int least = Collections.min(s.getPoss());
				possibilities.removeIf(x -> (x <= least));
			}
			
			if(possibilities.size() == 1) {
				setNum(possibilities.get(0));
			}
		}
	}
	
	public void elim(Integer n) {
		if(number == -1) {
			if(possibilities.contains(n)) {
				possibilities.remove(n);
			}
			checkPoss();
		}
	}
	
	public void lessThan(Square s) {
		lessThan.add(s);
		checkPoss();
	}
	
	public void greaterThan(Square s) {
		greaterThan.add(s);
		checkPoss();
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getNum() {
		return number;
	}
	
	public ArrayList<Integer> getPoss() {
		return possibilities;
	}
	
	public void setNum(int n) {
		if(number == -1) {
			number = n;
			possibilities.removeIf(x -> (x != n));
		}
	}
	
	public boolean checkAlert() {
		return alerted || number == -1;
	}
	
	public void alert() {
		alerted = true;
	}
	
}

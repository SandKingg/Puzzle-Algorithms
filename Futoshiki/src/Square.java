import java.util.*;

public class Square {

	private boolean alerted = false; //Have other squares been notified this one has been solved
	private int number = -1; //The number in this square, -1 for no number yet
	private int row = -1;
	private int col = -1; //Row and col reference (1,1) is top left.
	private ArrayList<Integer> possibilities = new ArrayList<Integer>(); //Which numbers can this square be
	private ArrayList<Square> lessThan = new ArrayList<Square>(); //Which squares is this one less than
	private ArrayList<Square> greaterThan = new ArrayList<Square>(); //Which squares is this one greater than
	
	public Square(int row, int col, ArrayList<Integer> possibilities) {
		this.possibilities = possibilities;
		this.row = row;
		this.col = col;
	}
	
	//Check if we can narrow down the possibilities for this square at all
	public void checkPoss() {
		if(number == -1) {
			//Must be less than the highest in these squares
			for(Square s:lessThan) {
				int greatest = Collections.max(s.getPoss());
				possibilities.removeIf(x -> (x >= greatest));
			}
			
			//Must be greater than the lowest in these squares
			for(Square s:greaterThan) {
				int least = Collections.min(s.getPoss());
				possibilities.removeIf(x -> (x <= least));
			}
			
			//If there's only one possibility, we have the solution for this square
			if(possibilities.size() == 1) {
				setNum(possibilities.get(0));
			}
		}
	}
	
	//Eliminate a number from the possibilities for this square, then check for further narrowing
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

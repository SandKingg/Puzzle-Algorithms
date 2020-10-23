import java.util.*;

public class Square {

	private char letter = '?';
	private int row = -1;
	private int col = -1;
	private ArrayList<Character> possibilities = new ArrayList<Character>();
	
	public Square(int row, int col, ArrayList<Character> possibilities) {
		this.row = row;
		this.col = col;
		this.possibilities = possibilities;
	}
	
	public void checkPoss() {
		if(possibilities.size() == 1) {
			setLetter(possibilities.get(0));
		}
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public char getLetter() {
		return letter;
	}
	
	public ArrayList<Character> getPoss() {
		return possibilities;
	}
	
	public void setLetter(char l) {
		if(letter == '?') {
			letter = l;
		}
	}
	
}

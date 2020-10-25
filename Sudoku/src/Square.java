import java.util.*;

public class Square {

	private Row row;
	private Column col;
	private Box box;
	private int number = -1;
	private ArrayList<Integer> possibilities;
	
	public Square() {
		possibilities = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
	}
	
	public void setNum(int num) {
		if(number == -1) {
			number = num;
			possibilities.removeIf(x -> (x != num));
			row.elim(num);
			col.elim(num);
			box.elim(num);
		}
	}
	
	public void elim(Integer num) {
		if (number == -1) {
			possibilities.remove(num);
			if(possibilities.size()==1) {
				setNum(possibilities.get(0));
			}
		}
	}
	
	public int getNum() {
		return number;
	}
	
	public void setRow(Row r) {
		row = r;
	}
	
	public void setCol(Column c) {
		col = c;
	}
	
	public void setBox(Box b) {
		box = b;
	}
}

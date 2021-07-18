import java.util.*;

public abstract class Line {

	protected ArrayList<Square> squares = new ArrayList<Square>();
	protected int id;
	protected int[] numbers;
	protected int size;
	protected boolean complete = false;
	
	public Line(int id, int[] numbers, int size) {
		this.id = id;
		this.numbers = numbers;
		this.size = size;
	}
	
	public void initialFills() {
		if(!complete) {
			if(numbers[0] == 0 && numbers.length == 1) {
				for(Square s:squares) {
					s.setValue(SquareValue.BLANK);
				}
			} else {
				int total = 0;
				int max = 0;
				for(int i:numbers) {
					total += i;
					total++;
					
					if(i > max) {
						max = i;
					}
				}
				total--;
				
				int offset = size - total;
				
				if(max > offset) {
					int pointer = 0;
					for(int i:numbers) {
						if(i > offset) {
							pointer += offset;
							for(int j=(i-offset);j>0;j--) {
								squares.get(pointer).setValue(SquareValue.FILLED);
								pointer++;
							}
						} else {
							pointer += i;
						}
						pointer++;
					}
				}
			}
			
			checkComplete();
		}
	}
	
	private void checkComplete() {
		if(!complete) {
			int[] currentNums = generateLineNumbers(this.toString());
			if(Arrays.equals(currentNums,numbers)) {

				blankAllUnknowns();
				
				boolean foundUnknown = false;
				for(Square s:squares) {
					if(s.getValue().equals(SquareValue.UNKNOWN)) {
						foundUnknown = true;
						break;
					}
				}
				
				if(!foundUnknown) {
					complete = true;
				}
			}
		}
	}
	
	private int[] generateLineNumbers(String line) {
		String[] marks = line.split("[?-]+");
		int markSize;
		if(marks.length == 0) {
			return new int[] {0};
		} else if(marks[0].equals("")) {
			markSize = marks.length - 1;
		} else {
			markSize = marks.length;
		}
		
		int[] output = new int[markSize];
		int counter = 0;
		
		for(String s:marks) {
			if(!s.equals("")) {
				output[counter] = s.length();
				counter++;
			}
		}
		
		return output;
	}
	
	private void blankAllUnknowns() {
		for(Square s:squares) {
			if(s.getValue().equals(SquareValue.UNKNOWN)) {
				s.setValue(SquareValue.BLANK);
			}
		}
	}
	
	public ArrayList<Square> getSquares() {
		return squares;
	}
	
	public abstract void addSquare(Square s);
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Square s:squares) {
			switch(s.getValue()) {
				case BLANK:
					sb.append('-');
					break;
				case FILLED:
					sb.append('*');
					break;
				case UNKNOWN:
					sb.append('?');
					break;
			}
		}
		return sb.toString();
	}
	
}

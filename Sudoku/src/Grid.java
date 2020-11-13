import java.util.*;
import java.io.*;

public class Grid {

	private HashMap<Integer,Row> rows = new HashMap<Integer,Row>();
	private HashMap<Integer,Column> cols = new HashMap<Integer,Column>();
	private HashMap<Integer,Box> boxes = new HashMap<Integer,Box>();
	
	public Grid(String file) {
		for(int i=1;i<=9;i++) {
			Row r = new Row(i);
			Column c = new Column(i);
			Box b = new Box(i);
			
			rows.put(i,r);
			cols.put(i,c);
			boxes.put(i,b);
		}
		for(int r=0;r<9;r++) {
			for(int c=0;c<9;c++) {
				Square s = new Square();
				rows.get(r+1).addSquare(s);
				cols.get(c+1).addSquare(s);
				
				int boxRow = (r/3);
				int boxCol = (c/3);
				int boxId = (boxRow * 3) + boxCol;
				boxes.get(boxId+1).addSquare(s);
			}
		}
		parse(file);
	}
	
	private void parse(String file) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			for(int i=1;i<=9;i++) {
				char[] line = in.readLine().toCharArray();
				for(int j=1;j<=9;j++) {
					if(line[j-1] != 'x') {
						int num = Character.getNumericValue(line[j-1]);
						assertNum(i,j,num);
					}
				}
			}
			in.close();
		} catch (Exception e) {}
	}
	
	private void assertNum(int row, int col, int num) {
		Square s = rows.get(row).getSquares().get(col-1);
		s.setNum(num);
	}
	
	private void numCheck() {
		for(int i=1;i<=9;i++) {
			rows.get(i).checkNums();
			cols.get(i).checkNums();
			boxes.get(i).checkNums();
		}
	}
	
	private void pointerCheck() {
		for(int i=1;i<=9;i++) {
			rows.get(i).checkPointers();
			cols.get(i).checkPointers();
			boxes.get(i).checkPointers();
		}
	}
	
	private void nakedSetCheck() {
		for(int i=1;i<=9;i++) {
			rows.get(i).checkNakedSets();
			cols.get(i).checkNakedSets();
			boxes.get(i).checkNakedSets();
		}
	}
	
	private String numHint() {
		for(int i=1;i<=9;i++) {
			if(rows.get(i).hintNums()) {
				return "Row "+i+": number.";
			}
			if(cols.get(i).hintNums()) {
				return "Column "+i+": number.";
			}
			if(boxes.get(i).hintNums()) {
				return "Box "+i+": number.";
			}
		}
		return "";
	}
	
	private String pointerHint() {
		for(int i=1;i<=9;i++) {
			if(rows.get(i).hintPointers()) {
				return "Row "+i+": pointer.";
			}
			if(cols.get(i).hintPointers()) {
				return "Column "+i+": pointer.";
			}
			if(boxes.get(i).hintPointers()) {
				return "Box "+i+": pointer.";
			}
		}
		return "";
	}
	
	private String nakedSetHint() {
		for(int i=1;i<=9;i++) {
			if(rows.get(i).hintNakedSets()) {
				return "Row "+i+": naked set.";
			}
			if(cols.get(i).hintNakedSets()) {
				return "Column "+i+": naked set.";
			}
			if(boxes.get(i).hintNakedSets()) {
				return "Box "+i+": naked set.";
			}
		}
		return "";
	}
	
	public void getHint() {
		String numHint = numHint();
		if(numHint.equals("")) {
			String pointerHint = pointerHint();
			if(pointerHint.equals("")) {
				String nakedSetHint = nakedSetHint();
				if(nakedSetHint.equals("")) {
					System.out.println("No hints available.");
				} else {
					System.out.println(nakedSetHint);
				}
			} else {
				System.out.println(pointerHint);
			}
		} else {
			System.out.println(numHint);
		}
	}
	
	public void fullCheck() {
		while(!complete()) {
			numCheck();
			pointerCheck();
			nakedSetCheck();
		}
	}
	
	private boolean complete() {
		for(Row r:rows.values()) {
			for(Square s:r.getSquares()) {
				if(s.getNum()==-1) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=9;i++) {
			Row r = rows.get(i);
			ArrayList<Square> squares = r.getSquares();
			for(int j=0;j<9;j++) {
				Square s = squares.get(j);
				if(s.getNum()!=-1) {
					sb.append("["+squares.get(j).getNum()+"]");
				} else {
					sb.append("[ ]");
				}
				if(j==2 || j==5) {
					sb.append(" | ");
				}
			}
			sb.append("\n");
			if(i==3 || i==6) {
				sb.append("---------------------------------\n");
			}
		}
		return sb.toString();
	}
	
}

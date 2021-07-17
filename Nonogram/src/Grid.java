import java.util.*;
import java.io.*;

public class Grid {

	private HashMap<Integer,Row> rows = new HashMap<Integer,Row>();
	private HashMap<Integer,Column> cols = new HashMap<Integer,Column>();
	private int width;
	private int height;
	
	public Grid(String file) {
		parse(file);
		
		for(int row=1;row<=height;row++) {
			for(int col=1;col<=width;col++) {
				Square s = new Square();
				rows.get(row).addSquare(s);
				cols.get(col).addSquare(s);
			}
		}
	}
	
	private void parse(String file) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String[] size = in.readLine().split(" ");
			width = Integer.parseInt(size[0]);
			height = Integer.parseInt(size[1]);
			
			for(int i=1;i<=height;i++) {
				String[] lineNumberStr = in.readLine().split(" ");
				int[] lineNumbers = StrToIntArray(lineNumberStr);
				
				rows.put(i, new Row(i,lineNumbers));
			}
			
			for(int i=1;i<=width;i++) {
				String[] lineNumberStr = in.readLine().split(" ");
				int[] lineNumbers = StrToIntArray(lineNumberStr);
				
				cols.put(i, new Column(i,lineNumbers));
			}
			
			in.close();
		} catch (Exception e) {}
	}
	
	private int[] StrToIntArray(String[] strArray) {
		int[] output = new int[strArray.length];
		
		for(int j=0;j<strArray.length;j++) {
			output[j] = Integer.parseInt(strArray[j]);
		}
		
		return output;
	}
	
}

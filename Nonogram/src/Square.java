public class Square {

	private Row row;
	private Column col;
	private SquareValue value = SquareValue.UNKNOWN;
	
	public void setValue(SquareValue v) {
		if(value == SquareValue.UNKNOWN) {
			value = v;
		}
	}
	
	public SquareValue getValue() {
		return value;
	}
	
	public Row getRow() {
		return row;
	}
	
	public Column getCol() {
		return col;
	}
	
	public void setRow(Row r) {
		row = r;
	}
	
	public void setCol(Column c) {
		col = c;
	}
	
}

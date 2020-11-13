import java.util.ArrayList;
import java.util.HashSet;

public class Row extends Container {

	public Row(int id) {
		super(id);
	}

	@Override
	public void addSquare(Square s) {
		squares.add(s);
		s.setRow(this);
	}
	
	@Override
	public void checkPointers() {
		for(int i=0;i<9;i+=3) {
			Square s1 = squares.get(i);
			Square s2 = squares.get(i+1);
			Square s3 = squares.get(i+2);
			
			ArrayList<Square> sqs = new ArrayList<Square>();
			sqs.add(s1);
			sqs.add(s2);
			sqs.add(s3);
			
			ArrayList<Integer> nums = new ArrayList<Integer>();
			nums.addAll(s1.getPoss());
			nums.addAll(s2.getPoss());
			nums.addAll(s3.getPoss());
			HashSet<Integer> dups = findDups(nums);
			
			for(int x:dups) {
				if(existExcept(x, sqs)) {
					s1.getBox().elimExcept(x, sqs);
				}
			}
		}
	}
	
	@Override
	public boolean hintPointers() {
		for(int i=0;i<9;i+=3) {
			Square s1 = squares.get(i);
			Square s2 = squares.get(i+1);
			Square s3 = squares.get(i+2);
			
			ArrayList<Square> sqs = new ArrayList<Square>();
			sqs.add(s1);
			sqs.add(s2);
			sqs.add(s3);
			
			ArrayList<Integer> nums = new ArrayList<Integer>();
			nums.addAll(s1.getPoss());
			nums.addAll(s2.getPoss());
			nums.addAll(s3.getPoss());
			HashSet<Integer> dups = findDups(nums);
			
			for(int x:dups) {
				if(existExcept(x, sqs)) {
					return true;
				}
			}
		}
		return false;
	}
}

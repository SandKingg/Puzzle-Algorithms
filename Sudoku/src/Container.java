import java.util.*;

public abstract class Container {

	protected ArrayList<Square> squares = new ArrayList<Square>();
	protected int id;
	
	public Container(int id) {
		this.id = id;
	}
	
	public void elim(int num) {
		for(Square s: squares) {
			s.elim(num);
		}
	}

	public void check() {
		HashMap<Integer,Integer> occurs = new HashMap<Integer,Integer>();
		for(Square s: squares) {
			if(s.getNum() == -1) {
				for(int i: s.getPoss()) {
					if(occurs.containsKey(i)) {
						occurs.put(i, occurs.get(i)+1);
					} else {
						occurs.put(i, 1);
					}
				}
			}
		}
		for(int i: occurs.keySet()) {
			if(occurs.get(i)==1) {
				for(Square s: squares) {
					if(s.getPoss().contains(i)) {
						s.setNum(i);
					}
				}
			}
		}
	}
	
	public boolean hintCheck() {
		HashMap<Integer,Integer> occurs = new HashMap<Integer,Integer>();
		for(Square s: squares) {
			if(s.getNum() == -1) {
				for(int i: s.getPoss()) {
					if(occurs.containsKey(i)) {
						occurs.put(i, occurs.get(i)+1);
					} else {
						occurs.put(i, 1);
					}
				}
			}
		}
		for(int i: occurs.keySet()) {
			if(occurs.get(i)==1) {
				return true;
			}
		}
		return false;
	}

	protected boolean existExcept(int num, ArrayList<Square> sqs) {
		for(Square s: squares) {
			if(!sqs.contains(s) && s.getPoss().contains(num)) {
				return false;
			}
		}
		return true;
	}
	
	protected void elimExcept(int num, ArrayList<Square> sqs) {
		for(Square s: squares) {
			if(!sqs.contains(s)) {
				s.elim(num);
			}
		}
	}
	
	protected void elimAllExcept(Collection<Integer> nums, ArrayList<Square> sqs) {
		for(int i: nums) {
			elimExcept(i, sqs);
		}
	}
	
	protected HashSet<Integer> findDups(ArrayList<Integer> nums) {
		HashSet<Integer> hs = new HashSet<Integer>();
		HashSet<Integer> dups = new HashSet<Integer>();
		
		for(int i:nums) {
			if(hs.contains(i)) {
				dups.add(i);
			} else {
				hs.add(i);
			}
		}
		
		return dups;
	}
	
	public void checkNakedSets() {
		//Get unsolved squares
		ArrayList<Square> unsolved = new ArrayList<Square>();
		for(Square s: squares) {
			if(s.getNum()==-1) {
				unsolved.add(s);
			}
		}
		
		//Check naked pairs
		if(unsolved.size() > 2) {
			ArrayList<Square> sqs = new ArrayList<Square>();
			for(int a=0;a<unsolved.size();a++) {
				Square sa = unsolved.get(a);
				sqs.add(sa);
				for(int b=a+1;b<unsolved.size();b++) {
					Square sb = unsolved.get(b);
					sqs.add(sb);
					if(isNakedSet(sqs)) {
						elimAllExcept(getPossSet(sqs),sqs);
					}
					
					//Check naked triples
					if(unsolved.size() > 3) {
						for(int c=b+1;c<unsolved.size();c++) {
							Square sc = unsolved.get(c);
							sqs.add(sc);
							if(isNakedSet(sqs)) {
								elimAllExcept(getPossSet(sqs),sqs);
							}
							
							//Check naked quads
							if(unsolved.size() > 4) {
								for(int d=c+1;d<unsolved.size();d++) {
									Square sd = unsolved.get(d);
									sqs.add(sd);
									if(isNakedSet(sqs)) {
										elimAllExcept(getPossSet(sqs),sqs);
									}
									
									//Check naked quints
									if(unsolved.size() > 5) {
										for(int e=d+1;e<unsolved.size();e++) {
											Square se = unsolved.get(e);
											sqs.add(se);
											if(isNakedSet(sqs)) {
												elimAllExcept(getPossSet(sqs),sqs);
											}
											
											sqs.remove(se);
										}
									}
									
									sqs.remove(sd);
								}
							}
							
							sqs.remove(sc);
						}
					}
					
					sqs.remove(sb);
				}
				
				sqs.remove(sa);
			}
		}
	}
	
	private HashSet<Integer> getPossSet(ArrayList<Square> sqs) {
		HashSet<Integer> possSet = new HashSet<Integer>();
		
		for(Square s:sqs) {
			possSet.addAll(s.getPoss());
		}
		
		return possSet;
	}
	
	private boolean isNakedSet(ArrayList<Square> sqs) {
		return sqs.size() == getPossSet(sqs).size();
	}
	
	public ArrayList<Square> getSquares() {
		return squares;
	}
	
	public abstract void addSquare(Square s);
	
	public abstract void checkPointers();
}


public class Main {

	public static void main(String[] args) {
		Grid g = new Grid("input.txt");
		g.fullNumCheck();
		g.fullNumCheck();
		g.pairCheck();
		g.pairCheck();
		g.fullNumCheck();
		//g.getHint();
		System.out.println(g);
	}

}

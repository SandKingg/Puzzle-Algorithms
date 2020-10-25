
public class Main {

	public static void main(String[] args) {
		Grid g = new Grid("input.txt");
		g.fullCheck();
		g.fullCheck();
		g.getHint();
		System.out.println(g);
	}

}

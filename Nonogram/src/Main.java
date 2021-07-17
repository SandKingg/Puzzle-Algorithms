public class Main {

	public static void main(String[] args) {
		Grid g = new Grid("input.txt");
		g.solve();
		System.out.println(g.toString());
	}

}

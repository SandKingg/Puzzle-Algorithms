
public class Main {

	public static void main(String[] args) {
		Grid g = new Grid("input.txt");
		g.fullNumCheck();
		g.fullNumCheck();
		g.pointerCheck();
		g.pointerCheck();
		g.nakedSetCheck();
		g.nakedSetCheck();
		g.fullNumCheck();
		//g.getHint();
		System.out.println(g);
	}

}

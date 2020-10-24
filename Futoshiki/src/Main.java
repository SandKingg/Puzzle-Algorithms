public class Main {

	public static void main(String[] args) {
		/*Grid g = new Grid(5);
		g.assertNum("21", 2);
		g.assertNum("23", 1);
		g.assertNum("43", 5);
		g.assertLess("13", "12");
		g.assertLess("12", "11");
		g.assertLess("21", "22");
		g.assertLess("24", "25");
		g.assertLess("34", "24");
		g.assertLess("34", "33");
		g.assertLess("33", "43");
		g.assertLess("34", "44");
		g.assertLess("41", "51");
		g.assertLess("45", "44");
		g.assertLess("51", "52");
		g.assertLess("55", "45");
		g.assertNot("34", 3);*/
		
		Grid g = new Grid(4);
		/*g.assertNum("14",2);
		g.assertLess("13", "14");
		g.assertLess("31", "21");
		g.assertLess("33", "23");
		g.assertLess("34", "44");*/
		g.fullCheck();
		g.fullCheck();
		System.out.println(g);
	}
	
}

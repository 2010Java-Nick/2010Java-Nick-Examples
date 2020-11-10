
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int i2 = 1;
		change(i2);
		System.out.println(i2);
		
		Stuff s2 = new Stuff();
		s2.value = 1;
		change(s2);
		System.out.println(s2.value);
		
	}
	
	static void change(int i) {
		i = -10;
	}
	
	static void change(Stuff s) {
		s = new Stuff();
		s.value = -10;
	}
	
	static class Stuff {
		int value;
	}

}

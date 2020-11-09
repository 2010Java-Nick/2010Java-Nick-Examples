package HotelReservation;

public class MyBetterThread implements Runnable {
	
	private String name;
	
	public MyBetterThread(String name) {
		super();
		this.name = name;
	}

	public MyBetterThread() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i<1000; i++)  {
			System.out.println("on loop " + i + " in thread " + getName());
		}		
	}

	public static void main(String[] args) {
		Runnable r1 = new MyBetterThread("T1");
		Runnable r2 = new MyBetterThread("T2");
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
	}
	
}

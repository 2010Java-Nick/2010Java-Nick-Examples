package HotelReservation;

public class MyNewThread extends Thread {

	@Override
	public void run() {
		for (int i = 0; i<1000; i++)  {
			System.out.println("on loop " + i + " in thread " + getName());
		}
		super.run();
	}
	
	
	
	public static void main(String[] args) {
		Thread t1 = new MyNewThread();
		t1.setName("Thread 1");
		Thread t2 = new MyNewThread();
		t2.setName("thread 2");
		t1.start();
		t2.start();
	}
	
}

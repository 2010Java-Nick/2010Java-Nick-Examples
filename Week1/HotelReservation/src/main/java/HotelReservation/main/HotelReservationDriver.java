package HotelReservation.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import HotelReservation.pojos.Guest;

public class HotelReservationDriver {

	private static Scanner scan = new Scanner(System.in);
	
	private static List<Guest> guests = new ArrayList<Guest>();

	public static void main(String args[]) {

		String userInput;
		
		do {
			System.out.println("Please choose one of the following");
			System.out.println("[1] check in");
			System.out.println("[0] exit");
			
			userInput = scan.next();
			
			switch(userInput) {
			
			case "1":
				if (guestCheckIn()) {
					System.out.println("registration complete");
				} else { System.out.println("failed to register guest"); }
				break;
			case "0":
				System.out.println("Thank you for registering with us!!!");
				break;
			default:
				System.out.println("Invalid choice, or somthing. you must choose 0-????");
				break;
			}
			
			
		} while (!"0".equals(userInput));

	}
	
	private static boolean guestCheckIn() {

		System.out.println("What is the guest's name?");
		String name = scan.nextLine();
		System.out.println("What is the guest's phone number?");
		String phoneNumber = scan.nextLine();
		System.out.println("What is the guest's payment?");
		Double payment = scan.nextDouble();
		scan.nextLine();
		
		Guest guest = new Guest(name, null, phoneNumber, payment);
		
		
		
		return false;
		
	}
	
	public void scopeExample(int param) {
		
		scan.next();
		
		for(int i;;) {
			param = 8;
			int x = 20; //<-- loop/local/block scope
		}
		
		//System.out.println(i);
		
		//System.out.println(x); <-- out of scope
		
	}

}

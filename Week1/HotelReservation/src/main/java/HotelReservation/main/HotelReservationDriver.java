package HotelReservation.main;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import HotelReservation.pojos.Booking;
import HotelReservation.pojos.Guest;
import HotelReservation.pojos.Hotel;
import HotelReservation.pojos.Room;
import HotelReservation.pojos.Room.RoomType;

public class HotelReservationDriver {
	
	private static Logger log = Logger.getRootLogger();

	private static Scanner scan = new Scanner(System.in);

	private static Hotel hotelCalifornia = new Hotel(1000, 0, new Room[1000], 1, "Hotel California");

	private static List<Guest> guests = new ArrayList<Guest>();

	private static List<Booking> bookings = new ArrayList<Booking>();

	public static void main(String args[]) {
		
		//new HotelReservationDriver().testCatchError();
		
		log.info("Program has started");

		String userInput;

		do {
			System.out.println("Please choose one of the following");
			System.out.println("[1] check in");
			System.out.println("[2] check list of guests");
			System.out.println("[3] check bookings");
			System.out.println("[0] exit");

			userInput = scan.nextLine();

			switch (userInput) {

			case "1":
				if (guestCheckIn()) {
					System.out.println("registration complete");
				} else {
					System.out.println("failed to register guest");
				}
				break;

			case "2":
				System.out.println(guests);
				break;
			case "3":
				System.out.println(bookings);
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

		// Get guest info
		System.out.println("What is the guest's name?");
		String name = scan.nextLine();
		System.out.println("What is the guest's phone number?");
		String phoneNumber = scan.nextLine();
		System.out.println("What is the guest's payment?");
		Double payment = scan.nextDouble();
		scan.nextLine();

		Guest guest = new Guest(name, null, phoneNumber, payment);
		
		log.info("Created guest: " + guest);

		System.out.println(guest);

		guests.add(guest);

		// Get room preferences
		System.out.println("Do you need a smoking room?");
		boolean smoking = scan.nextBoolean();
		System.out.println("Do you need room service?");
		boolean roomService = scan.nextBoolean();
		System.out.println("What type of room do you want: 1 single bed, 2, double bed, 3 suite, 4 luxury suite");
		int roomTypeSelection = scan.nextInt();
		scan.nextLine();
		RoomType roomType = null;
		int beds = -1;
		switch (roomTypeSelection) {

		case 1:
			roomType = RoomType.SINGLE_BED;
			beds = 1;
			break;
		case 2:
			roomType = RoomType.DOUBLE_BED;
			beds = 2;
			break;
		case 3:
			roomType = RoomType.SUITE;
			beds = 3;
			break;
		case 4:
			roomType = RoomType.LUXURY_SUITE;
			beds = 4;
			break;
		}

		Room room = new Room(beds, smoking, roomType, roomService, (int) (Math.random() * 1000));

		guest.setRoom(room);

		hotelCalifornia.getRooms()[hotelCalifornia.getCurrentCapacity()] = room;

		hotelCalifornia.setCurrentCapacity(hotelCalifornia.getCurrentCapacity() + 1);

		LocalDate checkinDate = null;

		LocalDate checkoutDate = null;

		do {
		
		System.out.println("When do you want to check in?");
		String checkin = scan.nextLine();
		System.out.println("When do you want to check out?");
		String checkout = scan.nextLine();

		try {
			checkinDate = LocalDate.parse(checkin);
			checkoutDate = LocalDate.parse(checkout);
		} catch (DateTimeParseException e) {
			System.out.println("Could not parse dates. Use format yyyy-mm-dd");
			log.warn("DateTimeParseException caught", e);
		}
		} while(checkinDate == null || checkoutDate == null);

		Guest[] currentGuests = new Guest[] { guest };

		Booking booking = new Booking(currentGuests, checkinDate, checkoutDate,
				hotelCalifornia, room);

		bookings.add(booking);

		return true;

	}

	public void scopeExample(int param) {

		scan.next();

		for (int i;;) {
			param = 8;
			int x = 20; // <-- loop/local/block scope
		}

		// System.out.println(i);

		// System.out.println(x); <-- out of scope

	}
	
	public void testCatchError() {
		try {
			testCatchError();
			throw new StackOverflowError();
		} catch(Throwable t) {
			System.out.println("hahaha I caught an error");
		}
	}

}

package HotelReservationHibernate.Dto;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import HotelReservationJavalin.pojos.Booking;
import HotelReservationJavalin.pojos.Guest;

public class BookingDto {

	private int bookingId;
	private List<String> guestNames;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private String hotelName;
	private int roomNumber;
	
	public BookingDto(Booking book) {
		this(book.getBookingId(),
				new LinkedList<String>(),
				book.getCheckIn(),
				book.getCheckout(),
				"",
				0);
		if(book.getGuests().length > 0) {
			for(Guest g : book.getGuests()) {
				this.guestNames.add(g.getFirstName() + " " + g.getLastName());
			}
		}
		if(book.getHotel() != null) {
			this.hotelName = book.getHotel().getHotelName();
		}
		if(book.getRoom() != null) {
			this.roomNumber = book.getRoom().getRoomNumber();
		}
	}
	
	public BookingDto(int bookingId, List<String> guestNames, LocalDate checkIn, LocalDate checkOut, String hotelName,
			int roomNumber) {
		super();
		this.bookingId = bookingId;
		this.guestNames = guestNames;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.hotelName = hotelName;
		this.roomNumber = roomNumber;
	}




	public int getBookingId() {
		return bookingId;
	}




	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}




	public List<String> getGuestNames() {
		return guestNames;
	}




	public void setGuestNames(List<String> guestNames) {
		this.guestNames = guestNames;
	}




	public LocalDate getCheckIn() {
		return checkIn;
	}




	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}




	public LocalDate getCheckOut() {
		return checkOut;
	}




	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}




	public String getHotelName() {
		return hotelName;
	}




	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}




	public int getRoomNumber() {
		return roomNumber;
	}




	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}




	public BookingDto() {
		// TODO Auto-generated constructor stub
	}

}

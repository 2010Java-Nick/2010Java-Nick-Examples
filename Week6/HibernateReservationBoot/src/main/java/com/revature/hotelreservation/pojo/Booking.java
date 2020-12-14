package com.revature.hotelreservation.pojo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class Booking {

	private static int bookingCount;
	
	private int bookingId;
	
	private Guest[] guests;
	
	private LocalDate checkIn;
	
	private LocalDate checkout;
	
	private Hotel hotel;
	
	private Room room;

	public Booking() {
		this(new Guest[1], LocalDate.now(), LocalDate.now().plus(5, ChronoUnit.DAYS), new Hotel(), new Room());
	}

	public Booking(Guest[] guests, LocalDate checkIn, LocalDate checkout, Hotel hotel, Room room) {
		super();
		Booking.bookingCount++;
		this.bookingId = Booking.bookingCount;
		this.guests = guests;
		this.checkIn = checkIn;
		this.checkout = checkout;
		this.hotel = hotel;
		this.room = room;
	}

	public int getBookingId() {
		return bookingId;
	}

	public Guest[] getGuests() {
		return guests;
	}

	public void setGuests(Guest[] guests) {
		this.guests = guests;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDate getCheckout() {
		return checkout;
	}

	public void setCheckout(LocalDate checkout) {
		this.checkout = checkout;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public static int getBookingCount() {
		return bookingCount;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", guests=" + Arrays.toString(guests) + ", checkIn=" + checkIn
				+ ", checkout=" + checkout + ", hotel=" + hotel + ", room=" + room + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookingId;
		result = prime * result + ((checkIn == null) ? 0 : checkIn.hashCode());
		result = prime * result + ((checkout == null) ? 0 : checkout.hashCode());
		result = prime * result + Arrays.hashCode(guests);
		result = prime * result + ((hotel == null) ? 0 : hotel.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		if (bookingId != other.bookingId)
			return false;
		if (checkIn == null) {
			if (other.checkIn != null)
				return false;
		} else if (!checkIn.equals(other.checkIn))
			return false;
		if (checkout == null) {
			if (other.checkout != null)
				return false;
		} else if (!checkout.equals(other.checkout))
			return false;
		if (!Arrays.equals(guests, other.guests))
			return false;
		if (hotel == null) {
			if (other.hotel != null)
				return false;
		} else if (!hotel.equals(other.hotel))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		return true;
	}
	
}

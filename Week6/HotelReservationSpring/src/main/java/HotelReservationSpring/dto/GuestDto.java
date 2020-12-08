package HotelReservationSpring.dto;

import HotelReservationSpring.pojos.Guest;

public class GuestDto {
	
	private int guestId;
	
	private String firstName;
	
	private String lastName;
	
	private int roomNumber;
	
	private String phoneNumber;
	
	private double payment;

	public GuestDto(Guest guest) {
		this(guest.getGuestId(), 
				guest.getFirstName(), 
				guest.getLastName(), 
				-1,
				guest.getPhoneNumber(),
				guest.getPayment());
		if (guest.getRoom() != null) {
			this.roomNumber = guest.getRoom().getRoomNumber();
		}
	}
	
	public GuestDto() {
		super();
	}

	public GuestDto(int guestId, String firstName, String lastName, int roomNumber, String phoneNumber,
			double payment) {
		super();
		this.guestId = guestId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roomNumber = roomNumber;
		this.phoneNumber = phoneNumber;
		this.payment = payment;
	}

	public int getGuestId() {
		return guestId;
	}

	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "GuestDto [guestId=" + guestId + ", firstName=" + firstName + ", lastName=" + lastName + ", roomNumber="
				+ roomNumber + ", phoneNumber=" + phoneNumber + ", payment=" + payment + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + guestId;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(payment);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + roomNumber;
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
		GuestDto other = (GuestDto) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (guestId != other.guestId)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (Double.doubleToLongBits(payment) != Double.doubleToLongBits(other.payment))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (roomNumber != other.roomNumber)
			return false;
		return true;
	}
	
}

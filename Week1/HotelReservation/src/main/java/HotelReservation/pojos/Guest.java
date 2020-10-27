package HotelReservation.pojos;

public class Guest {
	
	//total number of guests every registered
	private static int guestCount;

	private int guestId;
	
	private String name;
	
	private Room room;
	
	private String phoneNumber;
	
	private double payment;

	public Guest(String name, Room room, String phoneNumber, double payment) {
		super();
		Guest.guestCount++;
		this.guestId = Guest.guestCount;
		this.name = name;
		this.room = room;
		this.phoneNumber = phoneNumber;
		this.payment = payment;
	}

	public Guest() {
		this("default guest", new Room(), "xxx-xxxx", 0.0);
	}

	public static int getGuestCount() {
		return guestCount;
	}

	public int getGuestId() {
		return guestId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
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
		return "Guest [guestId=" + guestId + ", name=" + name + ", room=" + room + ", phoneNumber=" + phoneNumber
				+ ", payment=" + payment + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + guestId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(payment);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
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
		Guest other = (Guest) obj;
		if (guestId != other.guestId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(payment) != Double.doubleToLongBits(other.payment))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		return true;
	}
	
}

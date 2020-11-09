package HotelReservationJavalin.pojos;

import java.util.Arrays;

public class Hotel {
	
	private final int MAX_CAPACITY;
	
	private int currentCapacity;
	
	private Room[] rooms;
	
	private int hotelId;
	
	private String hotelName;
	
	public boolean hasCapacity() {
		return currentCapacity < MAX_CAPACITY;
	}
	
	public Hotel() {
		super();
		MAX_CAPACITY = Integer.MAX_VALUE;
	}



	public Hotel(int mAX_CAPACITY, int currentCapacity, Room[] rooms, int hotelId, String hotelName) {
		super();
		MAX_CAPACITY = mAX_CAPACITY;
		this.currentCapacity = currentCapacity;
		this.rooms = rooms;
		this.hotelId = hotelId;
		this.hotelName = hotelName;
	}

	public int getCurrentCapacity() {
		return currentCapacity;
	}

	public void setCurrentCapacity(int currentCapacity) {
		this.currentCapacity = currentCapacity;
	}

	public Room[] getRooms() {
		return rooms;
	}

	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public int getMAX_CAPACITY() {
		return MAX_CAPACITY;
	}

	@Override
	public String toString() {
		return "Hotel [MAX_CAPACITY=" + MAX_CAPACITY + ", currentCapacity=" + currentCapacity + ", rooms="
				+ Arrays.toString(rooms) + ", hotelId=" + hotelId + ", hotelName=" + hotelName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + MAX_CAPACITY;
		result = prime * result + currentCapacity;
		result = prime * result + hotelId;
		result = prime * result + ((hotelName == null) ? 0 : hotelName.hashCode());
		result = prime * result + Arrays.hashCode(rooms);
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
		Hotel other = (Hotel) obj;
		if (MAX_CAPACITY != other.MAX_CAPACITY)
			return false;
		if (currentCapacity != other.currentCapacity)
			return false;
		if (hotelId != other.hotelId)
			return false;
		if (hotelName == null) {
			if (other.hotelName != null)
				return false;
		} else if (!hotelName.equals(other.hotelName))
			return false;
		if (!Arrays.equals(rooms, other.rooms))
			return false;
		return true;
	}
	
}

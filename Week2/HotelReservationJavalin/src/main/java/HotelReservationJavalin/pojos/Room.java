package HotelReservationJavalin.pojos;
/*
 * Object o;
 * if(Math.random() > .5)
 *   o = new Room(); <-- we know exactly which constructor is being run, it is the no args constructor
 * else
 *   o = new Hotel();
 * o.toString(); <-- runtime polymorphism, JVM does not no which impl. of toString() will be run
 * until the program is executed, OVERRIDING
 */
public class Room {
	
	public enum RoomType {
		SINGLE_BED,
		DOUBLE_BED,
		SUITE,
		LUXURY_SUITE
	}
	
	private int beds;
	
	private boolean smoking;
	
	private RoomType roomType;
	
	private boolean roomService;
	
	private int roomNumber;

	public Room() {
		//super(); called implicitly
	}

	public Room(int beds, boolean smoking, RoomType roomType, boolean roomService, int roomNumber) {
		//super(); calls super() implicitly
		this.beds = beds;
		this.smoking = smoking;
		this.roomType = roomType;
		this.roomService = roomService;
		this.roomNumber = roomNumber;
	}

	public int getBeds() {
		return beds;
	}

	public void setBeds(int beds) {
		this.beds = beds;
	}

	public boolean isSmoking() {
		return smoking;
	}

	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public boolean getRoomService() {
		return roomService;
	}

	public void setRoomService(boolean roomService) {
		this.roomService = roomService;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	@Override
	public String toString() {
		return "Room [beds=" + beds + ", smoking=" + smoking + ", roomType=" + roomType + ", roomService=" + roomService
				+ ", roomNumber=" + roomNumber + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + beds;
		result = prime * result + roomNumber;
		result = prime * result + (roomService ? 1231 : 1237);
		result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
		result = prime * result + (smoking ? 1231 : 1237);
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
		Room other = (Room) obj;
		if (beds != other.beds)
			return false;
		if (roomNumber != other.roomNumber)
			return false;
		if (roomService != other.roomService)
			return false;
		if (roomType != other.roomType)
			return false;
		if (smoking != other.smoking)
			return false;
		return true;
	}
	
}

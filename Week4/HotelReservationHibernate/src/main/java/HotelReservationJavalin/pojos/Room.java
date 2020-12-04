package HotelReservationJavalin.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/*
 * Object o;
 * if(Math.random() > .5)
 *   o = new Room(); <-- we know exactly which constructor is being run, it is the no args constructor
 * else
 *   o = new Hotel();
 * o.toString(); <-- runtime polymorphism, JVM does not no which impl. of toString() will be run
 * until the program is executed, OVERRIDING
 */
@Entity
@Table(name = "room")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "roomId")
public class Room {
	
	public enum RoomType {
		SINGLE_BED,
		DOUBLE_BED,
		SUITE,
		LUXURY_SUITE
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id")
	private int roomId;
	
	@Column(name="num_beds")
	private int beds;
	
	@Column(name = "smoking")
	private boolean smoking;
	
	@Transient
	//@Enumerated(EnumType.STRING)
	private RoomType roomType;
	
	@Column(name = "roomservice")
	private boolean roomService;
	
	@Column(name = "room_number")
	private int roomNumber;
	
	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;

	public Room() {
		//super(); called implicitly
	}

	public Room(int roomId, int beds, boolean smoking, RoomType roomType, boolean roomService, int roomNumber,
			Hotel hotel) {
		super();
		this.roomId = roomId;
		this.beds = beds;
		this.smoking = smoking;
		this.roomType = roomType;
		this.roomService = roomService;
		this.roomNumber = roomNumber;
		this.hotel = hotel;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public int getRoomId() {
		return roomId;
	}



	public void setRoomId(int roomId) {
		this.roomId = roomId;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + beds;
		result = prime * result + ((hotel == null) ? 0 : hotel.hashCode());
		result = prime * result + roomId;
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
		if (hotel == null) {
			if (other.hotel != null)
				return false;
		} else if (!hotel.equals(other.hotel))
			return false;
		if (roomId != other.roomId)
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

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", beds=" + beds + ", smoking=" + smoking + ", roomType=" + roomType
				+ ", roomService=" + roomService + ", roomNumber=" + roomNumber + ", hotel=" + hotel + "]";
	}

}

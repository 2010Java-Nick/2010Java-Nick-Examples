package HotelReservationHibernate.Dto;

import java.util.Objects;

import HotelReservationJavalin.pojos.Room;
import HotelReservationJavalin.pojos.Room.RoomType;

public class RoomDto {
    
    private int roomId;
	private int beds;
	private boolean smoking;
	private RoomType roomType;
	private boolean roomService;
	private int roomNumber;
    private String hotelName;

    public RoomDto(Room room) {
        this(
            room.getRoomId(),
            room.getBeds(),
            room.isSmoking(), 
            room.getRoomType(), 
            room.getRoomService(), 
            room.getRoomNumber(),
            room.getHotel().getHotelName()
        );
    }


    public RoomDto() {
    }

    public RoomDto(int roomId, int beds, boolean smoking, RoomType roomType, boolean roomService, int roomNumber, String hotelName) {
        this.roomId = roomId;
        this.beds = beds;
        this.smoking = smoking;
        this.roomType = roomType;
        this.roomService = roomService;
        this.roomNumber = roomNumber;
        this.hotelName = hotelName;
    }

    public int getRoomId() {
        return this.roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getBeds() {
        return this.beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public boolean isSmoking() {
        return this.smoking;
    }

    public boolean getSmoking() {
        return this.smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public RoomType getRoomType() {
        return this.roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public boolean isRoomService() {
        return this.roomService;
    }

    public boolean getRoomService() {
        return this.roomService;
    }

    public void setRoomService(boolean roomService) {
        this.roomService = roomService;
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getHotelName() {
        return this.hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public RoomDto roomId(int roomId) {
        this.roomId = roomId;
        return this;
    }

    public RoomDto beds(int beds) {
        this.beds = beds;
        return this;
    }

    public RoomDto smoking(boolean smoking) {
        this.smoking = smoking;
        return this;
    }

    public RoomDto roomType(RoomType roomType) {
        this.roomType = roomType;
        return this;
    }

    public RoomDto roomService(boolean roomService) {
        this.roomService = roomService;
        return this;
    }

    public RoomDto roomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    public RoomDto hotelName(String hotelName) {
        this.hotelName = hotelName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RoomDto)) {
            return false;
        }
        RoomDto roomDto = (RoomDto) o;
        return roomId == roomDto.roomId && beds == roomDto.beds && smoking == roomDto.smoking && Objects.equals(roomType, roomDto.roomType) && roomService == roomDto.roomService && roomNumber == roomDto.roomNumber && Objects.equals(hotelName, roomDto.hotelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, beds, smoking, roomType, roomService, roomNumber, hotelName);
    }

    @Override
    public String toString() {
        return "{" +
            " roomId='" + getRoomId() + "'" +
            ", beds='" + getBeds() + "'" +
            ", smoking='" + isSmoking() + "'" +
            ", roomType='" + getRoomType() + "'" +
            ", roomService='" + isRoomService() + "'" +
            ", roomNumber='" + getRoomNumber() + "'" +
            ", hotelName='" + getHotelName() + "'" +
            "}";
    }
    
    
}

package HotelReservationJavalin.Dao;

import java.util.List;

import HotelReservationJavalin.pojos.Room;


public interface RoomDao {

    public void createRoom(Room room);
	
	public Room readRoom(int roomId);
	
	public List<Room> readAllRooms();
	
	public Room updateRoom(int roomId, Room room);
	
	public void deleteRoom(Room room);

    
}

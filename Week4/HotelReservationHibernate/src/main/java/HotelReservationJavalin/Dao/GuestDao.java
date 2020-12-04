package HotelReservationJavalin.Dao;

import java.util.List;

import HotelReservationJavalin.pojos.Guest;
import HotelReservationJavalin.pojos.Room;
import HotelReservationJavalin.util.GuestUpdateException;

public interface GuestDao {
	
	public void createGuest(Guest guest);
	
	public Guest readGuest(int guestId);
	
	public List<Guest> readAllGuests();
	
	public Guest updateGuest(int guestId, Guest guest) throws GuestUpdateException;
	
	public void deleteGuest(Guest guest);
	
	public Room getRoomDetailsByGuest(int guestId); 

}

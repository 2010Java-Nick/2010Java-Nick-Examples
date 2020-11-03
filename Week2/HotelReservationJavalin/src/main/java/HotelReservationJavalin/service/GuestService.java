package HotelReservationJavalin.service;

import java.util.List;

import HotelReservationJavalin.pojos.Guest;
import HotelReservationJavalin.pojos.Room;
import HotelReservationJavalin.pojos.Room.RoomType;

public interface GuestService {
	
	public Guest createGuest(Guest guest);
	
	public void checkInGuest(Guest guest);
	
	public void checkOutGuest(Guest guest);
	
	public List<Guest> getAllGuests();
	
	public List<Guest> getAllGuestsByRoomType(RoomType roomType);

}

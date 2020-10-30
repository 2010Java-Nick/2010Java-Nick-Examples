package HotelReservation.service;

import java.util.List;

import HotelReservation.pojos.Guest;
import HotelReservation.pojos.Room;
import HotelReservation.pojos.Room.RoomType;

public interface GuestService {
	
	public Guest createGuest(String name, String phoneNumber, double payment, Room room);
	
	public void checkInGuest(Guest guest);
	
	public void checkOutGuest(Guest guest);
	
	public List<Guest> getAllGuests();
	
	public List<Guest> getAllGuestsByRoomType(RoomType roomType);

}

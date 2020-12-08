package HotelReservationSpring.service;

import java.util.List;

import HotelReservationSpring.pojos.Guest;
import HotelReservationSpring.pojos.Room.RoomType;
import HotelReservationSpring.util.GuestUpdateException;

public interface GuestService {
	
	public Guest createGuest(Guest guest);
	
	public void checkInGuest(Guest guest);
	
	public void checkOutGuest(Guest guest);
	
	public List<Guest> getAllGuests();
	
	public List<Guest> getAllGuestsByRoomType(RoomType roomType);
	
	public void updateGuest(int guestId, Guest guest) throws GuestUpdateException;
	
	public Guest getGuestById(int guestId);

}

package HotelReservationSpring.dao;

import java.util.List;

import HotelReservationSpring.pojos.Guest;
import HotelReservationSpring.util.GuestUpdateException;

public interface GuestDao {
	
	public Guest createGuest(Guest guest);
	
	public Guest readGuest(int guestId);
	
	public List<Guest> readAllGuests();
	
	public Guest updateGuest(int guestId, Guest guest) throws GuestUpdateException;
	
	public void deleteGuest(Guest guest);

}

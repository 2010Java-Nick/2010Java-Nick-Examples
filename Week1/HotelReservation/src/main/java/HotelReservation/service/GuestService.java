package HotelReservation.service;

import java.util.List;

import HotelReservation.pojos.Guest;
import HotelReservation.pojos.Room;

public interface GuestService {
	
	public Guest createGuest(String name, String phoneNumber, double payment, Room room);
	
	public void checkInGuest(Guest guest);
	
	public void checkOutGuest(Guest guest);
	
	public List<Guest> getAllGuests();

}

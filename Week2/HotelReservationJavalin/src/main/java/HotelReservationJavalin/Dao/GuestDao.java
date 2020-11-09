package HotelReservationJavalin.Dao;

import HotelReservationJavalin.pojos.Guest;
import HotelReservationJavalin.util.GuestUpdateException;

public interface GuestDao {
	
	public void createGuest(Guest guest);
	
	public Guest readGuest(int guestId);
	
	public Guest readAllGuests();
	
	public Guest updateGuest(int guestId, Guest guest) throws GuestUpdateException;
	
	public void deleteGuest(Guest guest);

}

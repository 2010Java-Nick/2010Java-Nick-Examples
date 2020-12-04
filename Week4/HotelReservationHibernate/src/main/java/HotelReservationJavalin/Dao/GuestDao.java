package HotelReservationJavalin.Dao;

import java.util.List;

import HotelReservationJavalin.pojos.Guest;
import HotelReservationJavalin.util.GuestUpdateException;

public interface GuestDao {
	
	public void createGuest(Guest guest);
	
	public Guest readGuest(int guestId);
	
	public List<Guest> readAllGuests();
	
	public void updateGuest(int guestId, String firstName, String lastName, String phoneNumber, double payment);
	
	public void deleteGuest(Guest guest);

}

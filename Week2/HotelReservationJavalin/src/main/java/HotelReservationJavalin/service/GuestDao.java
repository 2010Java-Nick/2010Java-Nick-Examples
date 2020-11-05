package HotelReservationJavalin.service;

import HotelReservationJavalin.pojos.Guest;

public interface GuestDao {
	
	public void createGuest(Guest guest);
	
	public Guest readGuest(int guestId);
	
	public Guest readAllGuests();
	
	public Guest updateGuest(int guestId, Guest guest);
	
	public void deleteGuest(Guest guest);

}

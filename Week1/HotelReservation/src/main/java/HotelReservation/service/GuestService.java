package HotelReservation.service;

import HotelReservation.pojos.Guest;

public interface GuestService {
	
	public Guest createGuest();
	
	public void checkInGuest(Guest guest);
	
	public void checkOutGuest(Guest guest);

}

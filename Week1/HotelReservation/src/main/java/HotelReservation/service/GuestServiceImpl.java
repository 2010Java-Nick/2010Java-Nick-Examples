package HotelReservation.service;

import HotelReservation.pojos.Guest;
import HotelReservation.pojos.Room;

public class GuestServiceImpl implements GuestService {
	
	private CustomCacheService<Guest> guestCache = new CustomCacheServiceSimpleInMemory<Guest>();
	
	public void setGuestCache(CustomCacheService<Guest> guestCache) {
		this.guestCache = guestCache;
	}

	public GuestServiceImpl(CustomCacheService<Guest> guestCache) {
		super();
		this.guestCache = guestCache;
	}

	public GuestServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Guest createGuest(String name, String phoneNumber, double payment, Room room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkInGuest(Guest guest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkOutGuest(Guest guest) {
		// TODO Auto-generated method stub
		
	}

}

package HotelReservationJavalin.service;

import java.util.List;

import HotelReservationJavalin.pojos.Guest;
import HotelReservationJavalin.pojos.Room;
import HotelReservationJavalin.pojos.Room.RoomType;

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
	public Guest createGuest(Guest guest) {
		System.out.println("Creating " + guest + " object");
		guestCache.addToCache(guest);
		return guest;
	}

	@Override
	public void checkInGuest(Guest guest) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkOutGuest(Guest guest) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Guest> getAllGuests() {
		return guestCache.retrieveAllItems();
	}

	@Override
	public List<Guest> getAllGuestsByRoomType(RoomType roomType) {

		//Labmda syntax
		// (param list) -> {//code to evaluate}
		// 1. if you have exactly 1 param, you do not need ()
		// guest -> {code...}
		//2. do not need {} if it is only 1 line with return
		//(a, b) -> a+b
		// a -> a.toString()
		
		return guestCache.retrieveMatching(
		/*
		 * (guest) -> { return guest.getRoom().getRoomType().equals(roomType); }
		 */
				guest -> guest.getRoom().getRoomType().equals(roomType));
	}

}

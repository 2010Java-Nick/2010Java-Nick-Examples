package HotelReservationJavalin.service;

import java.util.List;

import HotelReservationJavalin.Dao.GuestDao;
import HotelReservationJavalin.Dao.GuestDaoPostgres;
import HotelReservationJavalin.pojos.Guest;
import HotelReservationJavalin.pojos.Room.RoomType;
import HotelReservationJavalin.util.GuestUpdateException;

public class GuestServiceFullStack implements GuestService {
	
	GuestDao guestDao = new GuestDaoPostgres();

	@Override
	public Guest createGuest(Guest guest) {
		guestDao.createGuest(guest);
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
		return guestDao.readAllGuests();
	}

	@Override
	public List<Guest> getAllGuestsByRoomType(RoomType roomType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateGuest(int guestId, Guest guest) throws GuestUpdateException{
		guestDao.updateGuest(guestId, guest);
	}

}

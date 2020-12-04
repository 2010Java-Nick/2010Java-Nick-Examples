package HotelReservationJavalin.service;

import java.util.List;

import HotelReservationJavalin.Dao.GuestDao;
import HotelReservationJavalin.Dao.GuestDaoHibernate;
import HotelReservationJavalin.pojos.Guest;
import HotelReservationJavalin.pojos.Room.RoomType;
import HotelReservationJavalin.util.GuestUpdateException;

public class GuestServiceHibernate implements GuestService {
	
	GuestDao guestDao = new GuestDaoHibernate();

	@Override
	public Guest createGuest(Guest guest) {
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

	@Override
	public List<Guest> getAllGuests() {
		// TODO Auto-generated method stub
		return guestDao.readAllGuests();
	}

	@Override
	public List<Guest> getAllGuestsByRoomType(RoomType roomType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateGuest(int guestId, String firstName, String lastName, String phoneNumber, double payment) {
		guestDao.updateGuest(guestId, firstName, lastName, phoneNumber, payment);

	}
	
	@Override
	public Guest getGuestById(int guestId) {
		return guestDao.readGuest(guestId);
	}

}

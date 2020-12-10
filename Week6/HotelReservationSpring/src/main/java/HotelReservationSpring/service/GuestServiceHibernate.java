package HotelReservationSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import HotelReservationSpring.dao.GuestDao;
import HotelReservationSpring.pojos.Guest;
import HotelReservationSpring.pojos.Room.RoomType;
import HotelReservationSpring.util.GuestUpdateException;

@Service
public class GuestServiceHibernate implements GuestService {

	// Spring finds a GuestDao from the IOC container and places it here in this
	// class
	// @Autowired
	GuestDao guestDao;

	@Autowired
	@Qualifier(value = "guestDao")
	public void setGuestDao(GuestDao guestDao) {
		this.guestDao = guestDao;
	}

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
		if (false) {
			throw new RuntimeException("Test exception log");
		}
		return guestDao.readAllGuests();
	}

	@Override
	public List<Guest> getAllGuestsByRoomType(RoomType roomType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateGuest(int guestId, Guest guest) throws GuestUpdateException {
		// TODO Auto-generated method stub

	}

	@Override
	public Guest getGuestById(int guestId) {
		return guestDao.readGuest(guestId);
	}

}

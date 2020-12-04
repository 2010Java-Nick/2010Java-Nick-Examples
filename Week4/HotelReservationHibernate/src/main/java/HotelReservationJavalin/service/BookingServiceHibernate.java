package HotelReservationJavalin.service;

import java.util.List;

import HotelReservationJavalin.Dao.BookingDao;
import HotelReservationJavalin.pojos.Booking;

public class BookingServiceHibernate implements BookingService {

	private BookingDao theDao;
	
	public BookingServiceHibernate(BookingDao theDao) {
		this.theDao = theDao;
	}

	@Override
	public List<Booking> getAllBooking() {
		return theDao.readAllBookings();
	}

}

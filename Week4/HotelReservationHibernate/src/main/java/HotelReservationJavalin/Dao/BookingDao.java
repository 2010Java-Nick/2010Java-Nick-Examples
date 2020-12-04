package HotelReservationJavalin.Dao;

import java.util.List;

import HotelReservationJavalin.pojos.Booking;

public interface BookingDao {
	public List<Booking> readAllBookings();
}

package HotelReservationJavalin.controller;

import java.util.ArrayList;
import java.util.List;

import HotelReservationHibernate.Dto.BookingDto;
import HotelReservationJavalin.pojos.Booking;
import HotelReservationJavalin.service.BookingService;
import io.javalin.http.Context;

public class BookingController {
	
	private BookingService serve;
	
	public BookingController(BookingService serve) {
		this.serve = serve;
	}
	
	public void readAllBookings(Context ctx) {
		List<Booking> books = serve.getAllBooking();
		List<BookingDto> theBooks = new ArrayList<>();
		for(Booking b: books) {
			theBooks.add(new BookingDto(b));
		}
		ctx.json(theBooks);
	}
	
}

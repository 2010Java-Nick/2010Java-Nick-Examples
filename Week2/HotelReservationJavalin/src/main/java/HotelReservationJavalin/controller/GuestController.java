package HotelReservationJavalin.controller;

import HotelReservationJavalin.pojos.Guest;
import HotelReservationJavalin.service.GuestService;
import HotelReservationJavalin.service.GuestServiceImpl;
import io.javalin.http.Context;

public class GuestController {
	
	GuestService guestService = new GuestServiceImpl();
	
	public void createGuest(Context ctx) {
		
		System.out.println("Responding to post guest request");
		
		String name = ctx.formParam("name");
		
		String phoneNumber = ctx.formParam("phoneNumber");
		
		Double payment = Double.parseDouble(ctx.formParam("payment"));
		
		Guest guest = new Guest(name, null, phoneNumber, payment);
		
		guestService.createGuest(guest);
		
		ctx.html(Integer.toString(guest.getGuestId()));
		
	}
	
	public void getGuest(Context ctx) {
		//TODO
	}
	
	public void getAllGuests() {
		//TODO
	}

}

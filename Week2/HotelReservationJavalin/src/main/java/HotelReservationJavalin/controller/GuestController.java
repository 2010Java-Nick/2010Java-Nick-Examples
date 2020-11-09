package HotelReservationJavalin.controller;

import HotelReservationJavalin.pojos.Guest;
import HotelReservationJavalin.service.GuestService;
import HotelReservationJavalin.service.GuestServiceFullStack;
import HotelReservationJavalin.util.GuestUpdateException;
import io.javalin.http.Context;

public class GuestController {

	GuestService guestService = new GuestServiceFullStack();

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
		// TODO
	}

	public void getAllGuests() {
		// TODO
	}

	public void updateGuest(Context ctx) {
		
		System.out.println("Responding to put guest request");

		String name = ctx.formParam("name");

		String phoneNumber = ctx.formParam("phoneNumber");

		Double payment = Double.parseDouble(ctx.formParam("payment"));

		Guest guest = new Guest(name, null, phoneNumber, payment);
		
		try {
			guestService.updateGuest(Integer.parseInt(ctx.formParam("id")), guest);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GuestUpdateException e) {
			ctx.status(500);
		}
	}

}

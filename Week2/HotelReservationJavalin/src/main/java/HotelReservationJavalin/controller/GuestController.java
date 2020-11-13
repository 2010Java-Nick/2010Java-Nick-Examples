package HotelReservationJavalin.controller;

import java.util.List;

import HotelReservationJavalin.pojos.Guest;
import HotelReservationJavalin.service.GuestService;
import HotelReservationJavalin.service.GuestServiceFullStack;
import HotelReservationJavalin.util.GuestUpdateException;
import io.javalin.http.Context;
import io.javalin.http.util.JsonEscapeUtil;
import io.javalin.plugin.json.JavalinJson;

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

	public void getAllGuests(Context ctx) {
		List<Guest> guestList = guestService.getAllGuests();
		/*
		 * JSONArray array = new JSONArray(); for (Guest guest : guestList) { JSONObject
		 * json = new JSONObject(); json.put("name", guest.getName());
		 * json.put("phoneNumber", guest.getPhoneNumber()); json.put("payment",
		 * guest.getPayment()); array.add(json); }
		 */
		ctx.json(guestList);
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

package HotelReservationSpring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import HotelReservationSpring.dto.GuestDto;
import HotelReservationSpring.pojos.Guest;
import HotelReservationSpring.service.GuestService;
import HotelReservationSpring.service.GuestServiceHibernate;
import HotelReservationSpring.util.GuestUpdateException;
import io.javalin.http.Context;

@Component
public class GuestController {

	@Autowired
	GuestService guestService;

	public void createGuest(Context ctx) {

		System.out.println("Responding to post guest request");

		String name = ctx.formParam("name");

		String phoneNumber = ctx.formParam("phoneNumber");

		Double payment = Double.parseDouble(ctx.formParam("payment"));

		Guest guest = new Guest(0, name, null, null, phoneNumber, payment);

		guestService.createGuest(guest);

		ctx.html(Integer.toString(guest.getGuestId()));

	}

	public void getGuest(Context ctx) {
		System.out.println("Inside get individual guest");
		String guestId = ctx.pathParam("id");
		Guest guest = guestService.getGuestById(Integer.parseInt(guestId));
		ctx.json(new GuestDto(guest));
	}

	public void getAllGuests(Context ctx) {
		System.out.println("Inside get all guests");
		List<Guest> guestList = guestService.getAllGuests();
		/*
		 * JSONArray array = new JSONArray(); for (Guest guest : guestList) { JSONObject
		 * json = new JSONObject(); json.put("name", guest.getName());
		 * json.put("phoneNumber", guest.getPhoneNumber()); json.put("payment",
		 * guest.getPayment()); array.add(json); }
		 */
		//System.out.println(guestList);
		
		List<GuestDto> guestDtoList = new ArrayList<>();
		
		for (Guest guest : guestList) {
			guestDtoList.add(new GuestDto(guest));
		}
		
		ctx.json(guestDtoList);
	}

	public void updateGuest(Context ctx) {
		
		System.out.println("Responding to put guest request");

		String name = ctx.formParam("name");

		String phoneNumber = ctx.formParam("phoneNumber");

		Double payment = Double.parseDouble(ctx.formParam("payment"));

		Guest guest = new Guest(0, name, null, null, phoneNumber, payment);
		
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

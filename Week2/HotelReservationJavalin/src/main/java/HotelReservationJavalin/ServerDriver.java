package HotelReservationJavalin;

import HotelReservationJavalin.controller.GuestController;
import io.javalin.Javalin;

public class ServerDriver {
	
	private static GuestController guestController = new GuestController();
	private static final String GUEST_PATH = "/guest";
	
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(9090); //sets up and starts our server
		app.get("/hello", ctx -> ctx.html("Hello World"));
		app.post(GUEST_PATH, ctx -> guestController.createGuest(ctx));
		app.put(GUEST_PATH, ctx -> guestController.updateGuest(ctx));
	}

}

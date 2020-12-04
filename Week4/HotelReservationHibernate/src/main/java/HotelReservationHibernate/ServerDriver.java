package HotelReservationHibernate;

import HotelReservationJavalin.controller.AuthController;
import HotelReservationJavalin.controller.GuestController;
import io.javalin.Javalin;

public class ServerDriver {
	
	private static GuestController guestController = new GuestController();
	private static AuthController authController = new AuthController();
	private static final String GUEST_PATH = "/guest";
	private static final String GET_GUEST_PATH = "/guest/:id";
	private static final String LOGIN_PATH = "/login";
	
	public static void main(String[] args) {
		Javalin app = Javalin.create( config -> {
				config.addStaticFiles("/public");
		}).start(9091); //sets up and starts our server
		app.get("/hello", ctx -> ctx.html("Hello World, thanks Jenkins :)"));
		app.post(GUEST_PATH, ctx -> guestController.createGuest(ctx));
		app.put(GUEST_PATH, ctx -> guestController.updateGuest(ctx));
		app.get(GUEST_PATH, ctx -> guestController.getAllGuests(ctx));
		app.get(GET_GUEST_PATH, ctx -> guestController.getGuest(ctx));
		app.post(LOGIN_PATH, ctx -> authController.login(ctx));
		app.get(LOGIN_PATH, ctx -> authController.checkUser(ctx));
//		app.put(GUEST_PATH, ctx -> guestController.updateGuest(ctx));
	}

}

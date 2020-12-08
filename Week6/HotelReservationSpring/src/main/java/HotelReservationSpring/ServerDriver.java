package HotelReservationSpring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import HotelReservationSpring.config.AppConfig;
import HotelReservationSpring.controller.AuthController;
import HotelReservationSpring.controller.GuestController;
import HotelReservationSpring.controller.RoomController;
import io.javalin.Javalin;


public class ServerDriver {
	
	private static GuestController guestController; //= new GuestController();
	private static RoomController roomController = new RoomController();
	private static AuthController authController = new AuthController();
	private static final String GUEST_PATH = "/guest";
	private static final String GET_GUEST_PATH = "/guest/:id";
	private static final String LOGIN_PATH = "/login";
	private static final String ROOM_PATH ="/room";
	
	public static void main(String[] args) {
		
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		guestController = ac.getBean(GuestController.class);
		
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
		app.get(ROOM_PATH, ctx -> roomController.getAllRooms(ctx));
	}

}

package HotelReservationSpring.controller;

import HotelReservationSpring.service.AuthService;
import HotelReservationSpring.service.AuthServiceHardCoded;
import io.javalin.http.Context;

public class AuthController {
	
	private AuthService auth = new AuthServiceHardCoded();
	
	public void login(Context ctx) {
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		boolean authenticated = auth.authenticateUser(username, password);
		if (authenticated) {
			//ctx.status(200);
			ctx.cookieStore("security", auth.createToken(username));
			ctx.redirect("view-guests-http.html");
		} else {
			//ctx.status(401);
			ctx.redirect("login.html?error=failed-login");
		}
	}
	
	public void checkUser(Context ctx) {
		ctx.html(auth.validateToken(ctx.cookieStore("security")));
	}

}

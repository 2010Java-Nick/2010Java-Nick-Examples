package HotelReservationJavalin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private Connection conn;
	
	private static final String url = System.getenv("HOTEL_URL");
	private static final String username = System.getenv("HOTEL_USERNAME");
	private static final String password = System.getenv("HOTEL_PASSWORD");
	
	public Connection createConnection() throws SQLException {
		
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?", "postgres", "R3v@tur3");
		
		return conn;
		
	}
	

}

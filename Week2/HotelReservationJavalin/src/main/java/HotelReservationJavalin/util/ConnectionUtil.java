package HotelReservationJavalin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	/*Singleton - reuse the same copy of an object in memory
	 * check if instance exists
	 * if it does, return that instance
	 * if not, create the instance and return it
	*/
	
	private Connection conn;
	
	private static final String URL = System.getenv("HOTEL_URL");
	private static final String USERNAME = System.getenv("HOTEL_USERNAME");
	private static final String PASSWORD = System.getenv("HOTEL_PASSWORD");
	
	public Connection createConnection() throws SQLException {
		
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		
		return conn;
		
	}
	

}

package HotelReservationJavalin.Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import HotelReservationJavalin.pojos.Guest;
import HotelReservationJavalin.util.ConnectionUtil;

public class GuestDaoPostgres implements GuestDao {

	private Statement statement;
	
	private ConnectionUtil connUtil = new ConnectionUtil();
	
	public void setConnUtil(ConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}

	@Override
	public void createGuest(Guest guest) {
		
		String sql = "insert into guest (guest_first_name, guest_last_name, phone_number, payment) "
				+ "values('"
				+ guest.getName().split(" ")[0]
				+ "', '"
				+ guest.getName().split(" ")[1]
				+ "', '"
				+ guest.getPhoneNumber()
				+ "', "
				+ guest.getPayment()
				+ ")";
		
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Guest readGuest(int guestId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Guest readAllGuests() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Guest updateGuest(int guestId, Guest guest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteGuest(Guest guest) {
		// TODO Auto-generated method stub

	}

}

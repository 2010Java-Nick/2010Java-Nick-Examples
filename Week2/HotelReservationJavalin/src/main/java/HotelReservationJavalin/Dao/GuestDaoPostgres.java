package HotelReservationJavalin.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import HotelReservationJavalin.pojos.Guest;
import HotelReservationJavalin.util.ConnectionUtil;
import HotelReservationJavalin.util.GuestUpdateException;

public class GuestDaoPostgres implements GuestDao {

	private Statement statement;

	private ConnectionUtil connUtil = new ConnectionUtil();

	public void setConnUtil(ConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}

	@Override
	public void createGuest(Guest guest) {

		String sql = "insert into guest (guest_first_name, guest_last_name, phone_number, payment) " + "values('"
				+ guest.getName().split(" ")[0] + "', '" + guest.getName().substring(guest.getName().split(" ")[0].length()) + "', '"
				+ guest.getPhoneNumber() + "', " + guest.getPayment() + ")";

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
	public List<Guest> readAllGuests() {

		String sql = "select * from guest";
		
		List<Guest> guestList = new ArrayList<>();

		try (Connection conn = connUtil.createConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				guestList.add(makeGuest(resultSet));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return guestList;
	}

	@Override
	public Guest updateGuest(int guestId, Guest guest) throws GuestUpdateException {
		/*
		 * String sql = "update guest set " + "guest_first_name = '" +
		 * guest.getName().split(" ")[0] + "', guest_last_name = '" +
		 * guest.getName().split(" ")[1] + "', phone_number = '" +
		 * guest.getPhoneNumber() + "', payment = " + guest.getPayment() +
		 * "where guest_id = " + guestId;
		 * 
		 * try (Connection conn = connUtil.createConnection()) { statement =
		 * conn.createStatement(); statement.executeUpdate(sql); } catch (SQLException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

		String sql = "update guest set guest_first_name = ?, guest_last_name = ?, phone_number = ?, payment = ? where guest_id = ?";

		try (Connection conn = connUtil.createConnection()) {

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, guest.getName().split(" ")[0]);
			pstmt.setString(2, guest.getName().split(" ")[1]);
			pstmt.setString(3, guest.getPhoneNumber());
			pstmt.setDouble(4, guest.getPayment());
			pstmt.setInt(5, guestId);

			Savepoint s1 = conn.setSavepoint();

			int rowsEffected = pstmt.executeUpdate();

			if (rowsEffected != 1) {
				conn.rollback(s1);
				throw new GuestUpdateException("Too many guests updated");
			} else {
				conn.commit();
			}

			conn.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void deleteGuest(Guest guest) {
		// TODO Auto-generated method stub

	}
	
	public Guest makeGuest(ResultSet rs) throws SQLException {
		return new Guest(rs.getInt(1), rs.getString(2) + " " + rs.getString(3), null, rs.getString(4), rs.getDouble(5));
	}

}

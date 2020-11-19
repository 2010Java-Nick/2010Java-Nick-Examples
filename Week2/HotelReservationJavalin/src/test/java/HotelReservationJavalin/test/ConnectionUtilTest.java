package HotelReservationJavalin.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;

import HotelReservationJavalin.util.ConnectionUtil;

public class ConnectionUtilTest {
	
	private ConnectionUtil connectionUtil = new ConnectionUtil();

	@Test
	@Ignore("no connection on vm")
	public void test() throws SQLException {
		Connection conn = connectionUtil.createConnection();
		conn.close();
	}

}

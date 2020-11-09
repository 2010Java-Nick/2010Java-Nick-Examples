package HotelReservationJavalin.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import HotelReservationJavalin.Dao.GuestDaoPostgres;
import HotelReservationJavalin.pojos.Guest;
import HotelReservationJavalin.util.ConnectionUtil;

@RunWith(MockitoJUnitRunner.class)
public class GuestDaoPostgresTest {

	public GuestDaoPostgres guestDao = new GuestDaoPostgres();
	
	@Mock
	private ConnectionUtil connUtil;
	
	@Mock
	private Connection connection;
	
	private Statement stmt;
	
	private Statement spy;
	
	private Connection realConnection;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		realConnection = new ConnectionUtil().createConnection();
		
		//creating a real stmt from a connection
		stmt = realConnection.createStatement(); 
		
		//spying on that real stmt
		spy = Mockito.spy(stmt);
		
		//mock our connection and util, so we will only use the stmt we are spying on
		when(connection.createStatement()).thenReturn(spy);
		when(connUtil.createConnection()).thenReturn(connection);
		
		//set up Dao to use the mocked object
		guestDao.setConnUtil(connUtil);
		
	}

	@After
	public void tearDown() throws Exception {
		
		stmt.executeUpdate("delete from guest where guest_first_name = 'Turkey' AND guest_last_name = 'McTurkey'");
		
		realConnection.close();
		
	}

	@Test
	public void createGuestTest() throws SQLException {
		
		Guest guest = new Guest("Turkey McTurkey", null, "1234567890", 100.0);
		
		guestDao.createGuest(guest);
		
		String sql = "insert into guest (guest_first_name, guest_last_name, phone_number, payment)"
				+ " values('Turkey', 'McTurkey', '1234567890', 100.0)";
		
		verify(spy).executeUpdate(sql);
		
		ResultSet rs = stmt.executeQuery("select * from guest where guest_first_name = 'Turkey' AND guest_last_name = 'McTurkey'");
		
		assertTrue(rs.next());
		
	}

}

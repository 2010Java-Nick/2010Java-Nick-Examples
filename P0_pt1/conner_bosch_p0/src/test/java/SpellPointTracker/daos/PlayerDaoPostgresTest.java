package SpellPointTracker.daos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import SpellPointTracker.pojos.Player;
import SpellPointTracker.util.ConnectionUtil;

@RunWith(MockitoJUnitRunner.class)
public class PlayerDaoPostgresTest {

	@Mock
	private static ConnectionUtil connUtil;
	@Mock
	private static Connection connection;

	private static Connection realConn;
	private static PreparedStatement stmt;
	private static PreparedStatement testStmt;
	private static PreparedStatement spy;

	private static PlayerDaoPostgres playerDao;
	private Player player;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		//Get new connection
		realConn = new ConnectionUtil().createConnection();

		//Reinitialize player test object
		player = new Player(1001, "Dave", "theBarbarian", 6, 2, 1);
		
	}

	@After
	public void tearDown() throws Exception {
		if (stmt != null) {
			stmt.close();
		}
		realConn.close();
	}

	@Test
	public void testCreatePlayerGoodInput() {
		//Prep statement with proper SQL
		String sql = "INSERT INTO player VALUES " 
					+"(?, ?, ?, ?, ?, ?);";
		try {
			initStmtHelper(sql);
		} catch (SQLException e) {
			fail("SQLException thrown: " + e.toString());
		}

		//Create object to be tested with mock connection containing spy
		playerDao = new PlayerDaoPostgres(connUtil);

		try {
			playerDao.createPlayer(player);

			//Verify statement was prepared properly
			verify(spy).setInt(1, player.getId());
			verify(spy).setString(2, player.getUsername());
			verify(spy).setString(3, player.getPassword());
			verify(spy).setInt(4, player.getCurrentPoints());
			verify(spy).setInt(5, player.getCurrentLevel());
			verify(spy).setInt(6, player.getCasterType());

			verify(spy).executeUpdate();

		} catch (Exception e) {
			fail("Exception thrown: " + e);
		} finally {
			//Removal process, post-test
			try {
				testStmt = realConn.prepareStatement("DELETE FROM player "
												+"WHERE player_id = " + Integer.toString(player.getId()));
				testStmt.executeUpdate();
			} catch (SQLException e) {
				fail("TEST ERROR, couldn't properly remove test player!");
			}
		}
	}

	@Test
	public void testReadPlayer() {
		//Insert test player to be read
		//Prep statement with proper SQL
		String sql = "INSERT INTO player VALUES " 
					+"(?, ?, ?, ?, ?, ?);";
		try {
			testStmt = realConn.prepareStatement(sql); 
			testStmt.setInt(1, player.getId());
			testStmt.setString(2, player.getUsername());
			testStmt.setString(3, player.getPassword());
			testStmt.setInt(4, player.getCurrentPoints());
			testStmt.setInt(5, player.getCurrentLevel());
			testStmt.setInt(6, player.getCasterType());
			assertTrue("Error in inserting test player", 1 == testStmt.executeUpdate());
		} catch (SQLException e) {
			fail("SQLException thrown in test set up: " + e.toString());
		}

		//Prep statement with proper SQL
		sql = "SELECT * FROM player " 
			+ "WHERE player_id = ?;";
		try {
			initStmtHelper(sql);
		} catch (SQLException e) {
			fail("SQLException thrown: " + e.toString());
		}

		//Create object to be tested with mock connection containing spy
		playerDao = new PlayerDaoPostgres(connUtil);

		try {
			Player resultPlayer = playerDao.readPlayer(player.getId());

			//Verify statement was prepared and executed properly
			verify(spy).setInt(1, player.getId());
			verify(spy).executeQuery();

			assertTrue("Object returned does not match expected object", player.equals(resultPlayer));
			
		} catch (Exception e) {
			fail("Exception thrown: " + e);
			
		} finally {
			//Removal process, post-test
			try {
				testStmt = realConn.prepareStatement("DELETE FROM player "
												+"WHERE player_id = " + Integer.toString(player.getId()));
				testStmt.executeUpdate();
			} catch (SQLException e) {
				fail("TEST ERROR, couldn't properly remove test player!");
			}
		}
	}

	@Test
	public void testReadAllPlayers() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdatePlayer() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeletePlayer() {
		fail("Not yet implemented");
	}

	/**
	 * Helper method that initializes static variables using the prepared sql string provided
	 * @param sql -Prepared SQL String
	 */
	private void initStmtHelper(String sql) throws SQLException {
			//Prep Mockito Spy
			stmt = realConn.prepareStatement(sql);
			spy = Mockito.spy(stmt);

			//Set standard connection mocking methods
			when(connUtil.createConnection()).thenReturn(connection);
			when(connection.prepareStatement(sql)).thenReturn(spy);
	}

}

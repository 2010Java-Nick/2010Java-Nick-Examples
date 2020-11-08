package SpellPointTracker.daos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

		//Set Dao with mocked ConnectionUtil
		playerDao = new PlayerDaoPostgres(connUtil);

		//Reinitialize player test object
		player = new Player(1010, "Dave", "theBarbarian", 6, 2, 1);
		
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

		} catch (SQLException e) {
			fail("Exception thrown: " + e);
		} finally {
			//Removal process, post-test
			try {
				testStmt = realConn.prepareStatement("DELETE FROM player WHERE player_id = ?;");
				testStmt.setInt(1, player.getId());
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

		try {
			Player resultPlayer = playerDao.readPlayer(player.getId());

			//Verify statement was prepared and executed properly
			verify(spy).setInt(1, player.getId());
			verify(spy).executeQuery();

			assertTrue("Object returned does not match expected object", player.equals(resultPlayer));
			
		} catch (SQLException e) {
			fail("Exception thrown: " + e);

		} finally {
			//Removal process, post-test
			try {
				testStmt = realConn.prepareStatement("DELETE FROM player WHERE player_id = ?;");
				testStmt.setInt(1, player.getId());
				testStmt.executeUpdate();
			} catch (SQLException e) {
				fail("TEST ERROR, couldn't properly remove test player!");
			}
		}
	}

	@Test
	public void testReadAllPlayers() {

		Integer numPlayers = -1;

		//Get current num of players in Database for verification
		String sql = "SELECT COUNT(*) FROM player;";
		try {
			testStmt = realConn.prepareStatement(sql); 
			ResultSet rs = testStmt.executeQuery();
			rs.next();
			numPlayers = rs.getInt(1);
		} catch (SQLException e) {
			fail("SQLException thrown in test set up: " + e.toString());
		}

		//Prep statement with proper SQL
		sql = "SELECT * FROM player;";
		try {
			initStmtHelper(sql);
		} catch (SQLException e) {
			fail("SQLException thrown: " + e.toString());
		}

		try {
			List<Player> allPlayers = playerDao.readAllPlayers();

			//Verify statement was executed properly
			verify(spy).executeQuery();

			//Verify result set returned proper data
			assertTrue("Returned set is not the same size as expected", numPlayers == allPlayers.size());
			for (Player p : allPlayers){
				assertFalse("Id returned 0 for user: " + p.getUsername(), 0 == p.getId());
				assertFalse("Username returned blank for user number: " + p.getId(), "".equals(p.getUsername()));
				assertFalse("Password returned blank for user: " + p.getUsername(), "".equals(p.getPassword()));
				assertFalse("Caster type returned 0 for user: " + p.getUsername(), 0 == p.getCasterType());
			}
		} catch (SQLException e) {
			fail("Exception thrown: " + e);	
		}
	}

	@Test
	public void testUpdatePlayer() {
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
		sql = "UPDATE player SET username = ?, passphrase = ?, " 
			+ "current_points = ?, current_level = ?, caster_id = ? "
			+ "WHERE player_id = ?;";
		try {
			initStmtHelper(sql);
		} catch (SQLException e) {
			fail("SQLException thrown: " + e.toString());
		}

		try {
			//Modify values 
			player.setPassword("smash12");
			player.setCasterType(2);
			playerDao.updatePlayer(player);

			//Verify statement was prepared properly
			verify(spy).setString(1, player.getUsername());
			verify(spy).setString(2, player.getPassword());
			verify(spy).setInt(3, player.getCurrentPoints());
			verify(spy).setInt(4, player.getCurrentLevel());
			verify(spy).setInt(5, player.getCasterType());
			verify(spy).setInt(6, player.getId());


			verify(spy).executeUpdate();

			//Pull modified player object from database for comparison
			testStmt = realConn.prepareStatement("SELECT * FROM player WHERE player_id = ?;");
			testStmt.setInt(1, player.getId());
			ResultSet rs = testStmt.executeQuery();

			rs.next();
			Player modPlayer = new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));

			assertEquals("Database object does not match as modified", player, modPlayer);

		} catch (SQLException e) {
			fail("Exception thrown: " + e);
		} finally {
			//Removal process, post-test
			try {
				testStmt = realConn.prepareStatement("DELETE FROM player WHERE player_id = ?;");
				testStmt.setInt(1, player.getId());
				testStmt.executeUpdate();
			} catch (SQLException e) {
				fail("TEST ERROR, couldn't properly remove test player!");
			}
		}
	}

	@Test
	public void testDeletePlayer() {
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
		sql = "DELETE FROM player "
			+ "WHERE player_id = ?;";
		try {
			initStmtHelper(sql);
		} catch (SQLException e) {
			fail("SQLException thrown: " + e.toString());
		}

		//Test deletePlayer functionality
		try {
			playerDao.deletePlayer(player);

			//Ensure proper methods called
			verify(spy).setInt(1, player.getId());
			verify(spy).executeUpdate();

			//Attempt to delete object that was already deleted (Should throw exception)
			testStmt = realConn.prepareStatement("DELETE FROM player WHERE player_id = ?;");
			testStmt.setInt(1, player.getId());
			assertEquals("Object was not deleted properly", 0, testStmt.executeUpdate());

		} catch (SQLException e) {
			fail("Exception thrown: " + e);

		}  
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

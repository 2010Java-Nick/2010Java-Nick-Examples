package SpellPointTracker.daos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import SpellPointTracker.pojos.Player;

@RunWith(MockitoJUnitRunner.class)
public class PlayerDaoPostgresTest {

	@Mock
	private static Connection connection;
	@Mock
	private static PreparedStatement stmt;

	private static PlayerDaoPostgres playerDao;
	private Player player;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		connection = mock(Connection.class);
		stmt = mock(PreparedStatement.class);
		when(connection.prepareStatement(anyString())).thenReturn(stmt);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		playerDao = new PlayerDaoPostgres(connection);
		player = new Player(1, "Dave", "theBarbarian", 6, 2, 1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreatePlayerGoodInput() {
		try {
		String sql = "INSERT INTO player VALUES " 
					+"(?, ?, ?, ?, ?, ?);";
		when(connection.prepareStatement(sql)).thenReturn(stmt);
		playerDao.createPlayer(player);
		verify(stmt).executeUpdate();
		verify(stmt).close();
		} catch (Exception e) {
			fail("Exception thrown: " + e);
		}
	}

	@Test
	public void testReadPlayer() {
		fail("Not yet implemented");
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

}

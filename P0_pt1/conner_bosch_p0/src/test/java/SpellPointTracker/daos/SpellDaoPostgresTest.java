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

import SpellPointTracker.util.ConnectionUtil;
import SpellPointTracker.pojos.Spell;

@RunWith(MockitoJUnitRunner.class)
public class SpellDaoPostgresTest {

	@Mock
	private ConnectionUtil connUtil;
	@Mock
	private Connection connection;

	private Connection realConn;
	private PreparedStatement stmt;
	private PreparedStatement testStmt;
	private PreparedStatement spy;

	private SpellDaoPostgres spellDao;
	private Spell spell;

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
		//spellDao = new SpellDaoPostgres(connUtil);

		//Initialize spell test object
		//spell = new Spell(1010, "big_cast", 9);

	}

	@After
	public void tearDown() throws Exception {
		if (stmt != null) {
			stmt.close();
		}
		realConn.close();
	}

	@Test
	public void testCreateSpell() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadSpell() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadAllSpells() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateSpell() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteSpell() {
		fail("Not yet implemented");
	}

	/**
	 * Helper method that initializes mock and spy variables using the prepared sql string provided
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

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
		spellDao = new SpellDaoPostgres(connUtil);

		//Initialize spell test object
		spell = new Spell(1010, "big_cast", 9);

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
		try {
			//Prep statement with proper SQL
			String sql = "INSERT INTO spell VALUES "
						+"(?, ?, ?);";

			try {
				initStmtHelper(sql);
			} catch (SQLException e) {
				fail("SQLException thrown in test set up: " + e.toString());
			}

			//Test createSpell functionality
			try {
				spellDao.createSpell(spell);

				//Verify statement was prepared properly
				verify(spy).setInt(1, spell.getId());
				verify(spy).setString(2, spell.getName());
				verify(spy).setInt(3, spell.getLevel());

				verify(spy).executeUpdate();

			} catch (SQLException e) {
				fail("SQLException thrown in creation process: " + e);
			}
		 } finally {
			//Removal process, post-test
			try {
				testStmt = realConn.prepareStatement("DELETE FROM spell WHERE spell_id = ?;");
				testStmt.setInt(1, spell.getId());
				testStmt.executeUpdate();
			} catch (SQLException e) {
				fail("TEST ERROR, could not properly remove spell: " + e);
			}
		}
	}

	@Test
	public void testReadSpell() {
		try {
			//Insert test player to be read
			String sql = "INSERT INTO spell VALUES "
						+"(?, ?, ?);";
			try {
				testStmt = realConn.prepareStatement(sql);
				testStmt.setInt(1, spell.getId());
				testStmt.setString(2, spell.getName());
				testStmt.setInt(3, spell.getLevel());
				assertTrue("Error in inserting test spell", 1 == testStmt.executeUpdate());
			} catch (SQLException e) {
				fail("SQLException thrown in test set up: " + e);
			}

			//Prep statement with proper SQL
			sql = "SELECT * FROM spell "
				+ "WHERE spell_id = ?;";
			try {
				initStmtHelper(sql);
			} catch (SQLException e) {
				fail("SQLException thrown: " + e);
			}

			try {
				Spell resultSpell = spellDao.readSpell(spell.getId());

				//Verify statement was prepared and executed properly
				verify(spy).setInt(1, spell.getId());
				verify(spy).executeQuery();

				assertTrue("Object returned does not match expected object", spell.equals(resultSpell));
			
			} catch (SQLException e) {
				fail("SQLException thrown: " + e);

			} 
		} finally {
			//Removal process, post-test
			try {
				testStmt = realConn.prepareStatement("DELETE FROM spell WHERE spell_id = ?;");
				testStmt.setInt(1, spell.getId());
				testStmt.executeUpdate();
			} catch (SQLException e) {
				fail("TEST ERROR, couldn't properly remove test spell!");
			}
		}
	}

	@Test
	public void testReadAllSpells() {
		
		Integer numSpells = -1;

		//Get current num of spells in Database for verification
		String sql = "SELECT COUNT(*) FROM spell;";
		try{
			testStmt = realConn.prepareStatement(sql);
			ResultSet rs = testStmt.executeQuery();
			rs.next();
			numSpells = rs.getInt(1);
		} catch (SQLException e) {
			fail("SQLException thrown in test setup: " + e);
		}

		//Prep statement with proper SQL
		sql = "SELECT * FROM spell;";
		try {
			initStmtHelper(sql);
		} catch (SQLException e) {
			fail("SQLException thrown: " + e.toString());
		}

		//Test readAllSpells functionality
		try {
			List<Spell> allSpells = spellDao.readAllSpells();

			//Verify statement was excuted properly
			verify(spy).executeQuery();

			//Verify result set returned proper data
			assertTrue("Returned set is not the same size as expected", numSpells == allSpells.size());
			for (Spell s : allSpells){
				assertFalse("Id returned 0 for spell: " + s.getName(), 0 == s.getId());
				assertFalse("Spell name returned blank for spell number: " + s.getId(), "".equals(s.getName()));
				assertFalse("Spell level returned 0 for spell: " + s.getName(), 0 == s.getLevel());
			}
		} catch (SQLException e) {
			fail("SQLException thrown in spellDao.readAllSpells: " + e);	
		}
	}

	@Test
	public void testUpdateSpell() {
		try {
			//Insert test spell to be read
				//Prep statement with proper SQL
			String sql = "INSERT INTO spell VALUES " 
						+"(?, ?, ?);";
			try {
				testStmt = realConn.prepareStatement(sql); 
				testStmt.setInt(1, spell.getId());
				testStmt.setString(2, spell.getName());
				testStmt.setInt(3, spell.getLevel());
				assertTrue("Error in inserting test spell", 1 == testStmt.executeUpdate());
			} catch (SQLException e) {
				fail("SQLException thrown in test set up: " + e.toString());
			}

			//Prep statement with proper SQL
			sql = "UPDATE spell SET spell_name = ?, spell_level = ? "
				+ "WHERE spell_id = ?;";
			try {
				initStmtHelper(sql);
			} catch (SQLException e) {
				fail("SQLException thrown: " + e.toString());
			}

			//Test updatePlayer functionality
			try {
				//Modify values 
				spell.setName("Little cast");
				spell.setLevel(3);
				spellDao.updateSpell(spell);

				//Verify statement was prepared properly
				verify(spy).setString(1, spell.getName());
				verify(spy).setInt(2, spell.getLevel());
				verify(spy).setInt(3, spell.getId());

				verify(spy).executeUpdate();

				//Pull modified player object from database for comparison
				testStmt = realConn.prepareStatement("SELECT * FROM spell WHERE spell_id = ?;");
				testStmt.setInt(1, spell.getId());
				ResultSet rs = testStmt.executeQuery();

				rs.next();
				Spell modSpell = new Spell(rs.getInt(1), rs.getString(2), rs.getInt(3));

				assertEquals("Database object does not match as modified", spell, modSpell);

			} catch (SQLException e) {
				fail("Exception thrown: " + e);
			} 
		} finally {
			//Removal process, post-test
			try {
				testStmt = realConn.prepareStatement("DELETE FROM spell WHERE spell_id = ?;");
				testStmt.setInt(1, spell.getId());
				testStmt.executeUpdate();
			} catch (SQLException e) {
				fail("TEST ERROR, couldn't properly remove test spell!");
			}
		}
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

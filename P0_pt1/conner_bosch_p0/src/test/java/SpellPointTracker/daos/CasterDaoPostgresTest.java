package SpellPointTracker.daos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Array;
import java.sql.CallableStatement;
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
import SpellPointTracker.pojos.Caster;

@RunWith(MockitoJUnitRunner.class)
public class CasterDaoPostgresTest {

	@Mock
	private ConnectionUtil connUtil;
	@Mock
	private Connection connection;

	private Connection realConn;
	private PreparedStatement testStmt;
	
	private PreparedStatement stmt;
	private CallableStatement callStmt;
	
	private PreparedStatement spy1;
	private PreparedStatement spy2;
	private PreparedStatement spy3;
	private CallableStatement callSpy;
	private Array ids;

	private CasterDaoPostgres casterDao;
	private Caster caster;

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
		casterDao = new CasterDaoPostgres(connUtil);

		//Initialize spell test object
		caster = new Caster(1010, "Illithid", false, new Integer[] {1, 2});
	}

	@After
	public void tearDown() throws Exception {
		if (stmt != null) {
			stmt.close();
		}
		realConn.close();
	}

	@Test
	public void testCreateCaster() {
		try {
			//Prep statement with proper SQL
			String sql1 = "INSERT INTO caster VALUES (?, ?, ?); ";
			String sql2 = "CALL insert_caster_spell_list(?, ?); ";
			try {
				initStmtHelper(sql1);
				initCallableHelper(sql2);
			} catch (SQLException e) {
				fail("SQLException thrown in test setup: " + e);
			}

			//Test createCaster functionality
			try {
				casterDao.createCaster(caster);

				//Verify prepared statement was called properly
				verify(spy1).setInt(1, caster.getId());
				verify(spy1).setString(2, caster.getName());
				verify(spy1).setBoolean(3, caster.getHalfCaster());
				verify(spy1).executeUpdate();

				//Verify callable statement was called properly
				verify(callSpy).setInt(1, caster.getId());
				verify(callSpy).setArray(2, ids);
				verify(callSpy).execute();

			} catch (SQLException e) {
				fail("SQLException thrown in creation process: " + e);
			}
		} finally {
			//Removal process, post-test
			try {
				testStmt = realConn.prepareStatement("DELETE FROM caster WHERE caster_id = ?;");
				testStmt.setInt(1, caster.getId());
				testStmt.executeUpdate();
			} catch (SQLException e) {
				fail("TEST ERROR, could not properly remove caster: " + e);
			}
		}
	}

	@Test
	public void testReadCaster() {
		try {
			//Insert test caster to be read
			String sql = "INSERT INTO caster VALUES "
						+"(?, ?, ?); "
						+"INSERT INTO caster_spell VALUES "
						+"(?, ?), (?, ?);";
			try {
				testStmt = realConn.prepareStatement(sql);
				testStmt.setInt(1, caster.getId());
				testStmt.setString(2, caster.getName());
				testStmt.setBoolean(3, caster.getHalfCaster());
				testStmt.setInt(4, caster.getId());
				testStmt.setInt(5, caster.getSpellIds()[0]);
				testStmt.setInt(6, caster.getId());
				testStmt.setInt(7, caster.getSpellIds()[1]);
				assertTrue("Error in inserting test caster", 1 == testStmt.executeUpdate());
			} catch (SQLException e) {
				fail("SQLException thrown in test setup: " + e);
			}

			//Prep statement with proper SQL
			sql = "SELECT c.caster_id, c.caster_name, c.half_caster, cs.spell_id FROM caster c "
				+ "INNER JOIN caster_spell cs " 
				+ "ON c.caster_id = cs.caster_id " 
				+ "WHERE c.caster_id = ?";

			try {
				initStmtHelper(sql);
			} catch (SQLException e ) {
				fail ("SQLException thrown; " + e);
			}

			try {
				Caster resultCaster = casterDao.readCaster(caster.getId());

				//Verify statement was prepared and executed properly
				verify(spy1).setInt(1, caster.getId());
				verify(spy1).executeQuery();

				assertTrue("Object returned does not match expected object", caster.equals(resultCaster));
			
			} catch (SQLException e) {
				fail("SQLException was thrown: " + e);
			}
		} finally {
			//Removal process, post-test
			try {
				testStmt = realConn.prepareStatement("DELETE FROM caster WHERE caster_id = ?;");
				testStmt.setInt(1, caster.getId());
				testStmt.executeUpdate();
			} catch (SQLException e) {
				fail("TEST ERROR, could not properly remove caster: " + e);
			}
		}
	}

	@Test
	public void testReadAllCasters() {
		
		Integer numCasters = -1;

		//Get current num of casters in Database for verification
		String sql = "SELECT COUNT(*) FROM caster;";
		try {
			testStmt = realConn.prepareStatement(sql);
			ResultSet rs = testStmt.executeQuery();
			rs.next();
			numCasters = rs.getInt(1);
		} catch (SQLException e) {
			fail("SQLException thrown in test setup: " + e);
		}

		//Prep statement with proper SQL
		sql = "SELECT c.caster_id, c.caster_name, c.half_caster, cs.spell_id FROM caster c "
			+ "INNER JOIN caster_spell cs " 
			+ "ON c.caster_id = cs.caster_id ";
		try {
			initStmtHelper(sql);
		} catch (SQLException e) {
			fail("SQLException thrown: " + e);
		}
		
		//Test readAllCasters functionality
		try {
			List<Caster> allCasters = casterDao.readAllCasters();

			//Verify statement was excuted properly
			verify(spy1).executeQuery();

			//Verify result set returned proper data
			assertTrue("Returned set is not the same size as expected", numCasters == allCasters.size());
			for (Caster c : allCasters) {
				assertFalse("Id returned 0 for caster: " + c.getName(), 0 == c.getId());
				assertFalse("Caster name returned blank for caster number: " + c.getId(), "".equals(c.getName()));
				assertFalse("Caster spell list returned empty for caster: " + c.getName(), 0 == c.getSpellIds().length);
			}
		} catch (SQLException e) {
			fail("SQLException thrown in casterDao.readAllCasters: " + e);
		}
	}

	@Test
	public void testUpdateCaster() {
		try {
			//Insert test caster to be updated
			String sql = "INSERT INTO caster VALUES "
						+"(?, ?, ?); "
						+"INSERT INTO caster_spell VALUES "
						+"(?, ?), (?, ?);";
			try {
				testStmt = realConn.prepareStatement(sql);
				testStmt.setInt(1, caster.getId());
				testStmt.setString(2, caster.getName());
				testStmt.setBoolean(3, caster.getHalfCaster());
				testStmt.setInt(4, caster.getId());
				testStmt.setInt(5, caster.getSpellIds()[0]);
				testStmt.setInt(6, caster.getId());
				testStmt.setInt(7, caster.getSpellIds()[1]);
				assertTrue("Error in inserting test caster", 1 == testStmt.executeUpdate());
			} catch (SQLException e) {
				fail("SQLException thrown in test setup: " + e);
			}

			//Prep statement with proper SQL
			sql = "UPDATE caster SET caster_name = ?, half_caster = ?" 
				+"WHERE caster_id = ?;";
			try {
				initStmtHelper(sql);
			} catch (SQLException e) {
				fail("SQLException thrown: " + e);
			}

			//Test updateCaster functionality
			try {
				//Modify values
				caster.setName("Mage Knight");
				caster.setHalfCaster(true);
				casterDao.updateCaster(caster);

				//Verify statement was prepared and executed properly
				verify(spy1).setString(1, caster.getName());
				verify(spy1).setBoolean(2, caster.getHalfCaster());
				verify(spy1).setInt(3, caster.getId());
				verify(spy1).executeUpdate();

				//Pull modified caster object from database for comparison
				testStmt = realConn.prepareStatement("SELECT * FROM caster WHERE caster_id = ?;");
				testStmt.setInt(1, caster.getId());
				ResultSet rs = testStmt.executeQuery();

				rs.next();
				Caster modCaster = new Caster(rs.getInt(1), rs.getString(2), rs.getBoolean(3), caster.getSpellIds());

				assertEquals("Database object does not match as modified", caster, modCaster);

			} catch (SQLException e) {
				fail("SQLException thrown from testing updateCaster: " + e);
			}

		} finally {
			//Removal process, post-test
			try {
				testStmt = realConn.prepareStatement("DELETE FROM caster WHERE caster_id = ?;");
				testStmt.setInt(1, caster.getId());
				testStmt.executeUpdate();
			} catch (SQLException e) {
				fail("TEST ERROR, could not properly remove caster: " + e);
			}
		}
	}

	@Test
	public void testUpdateCasterSpells() {
		try {
			//Insert test caster to be read
			String sql = "INSERT INTO caster VALUES "
						+"(?, ?, ?); "
						+"INSERT INTO caster_spell VALUES "
						+"(?, ?), (?, ?);";
			try {
				testStmt = realConn.prepareStatement(sql);
				testStmt.setInt(1, caster.getId());
				testStmt.setString(2, caster.getName());
				testStmt.setBoolean(3, caster.getHalfCaster());
				testStmt.setInt(4, caster.getId());
				testStmt.setInt(5, caster.getSpellIds()[0]);
				testStmt.setInt(6, caster.getId());
				testStmt.setInt(7, caster.getSpellIds()[1]);
				assertTrue("Error in inserting test caster", 1 == testStmt.executeUpdate());
			} catch (SQLException e) {
				fail("SQLException thrown in test setup: " + e);
			}

			//Prep statements with proper SQL
			try {
				sql = "SELECT spell_id FROM caster_spell WHERE caster_id = ?;";
				initStmtHelper(sql);
				sql = "DELETE FROM caster_spell WHERE caster_id = ? AND spell_id = ?;";
				initStmtHelper2(sql);
				sql = "INSERT INTO caster_spell VALUES (?, ?);";
				initStmtHelper3(sql);
			} catch (SQLException e) {
				fail("SQLException thrown: " + e);
			}

			//Test updateCaster functionality
			try {
				//Modify values
				casterDao.updateCasterSpells(caster.getId(), new Integer[]{2, 3});

				//Verify statement was select statement called
				verify(spy1).setInt(1, caster.getId());
				verify(spy1).executeQuery();

				//Verify delete statement was built and called
				verify(spy2).setInt(1, caster.getId());
				verify(spy2).setInt(2, 1);
				verify(spy2).addBatch();
				verify(spy2).executeBatch();

				//Verify insert statement was built and called
				verify(spy3).setInt(1, caster.getId());
				verify(spy3).setInt(2, 3);
				verify(spy3).addBatch();
				verify(spy3).executeBatch();


				//Pull modified caster object from database for comparison
				testStmt = realConn.prepareStatement("SELECT * FROM caster WHERE caster_id = ?;");
				testStmt.setInt(1, caster.getId());
				ResultSet rs = testStmt.executeQuery();

				rs.next();
				Caster modCaster = new Caster(rs.getInt(1), rs.getString(2), rs.getBoolean(3), caster.getSpellIds());

				assertEquals("Database object does not match as modified", caster, modCaster);

			} catch (SQLException e) {
				fail("SQLException thrown from testing updateCaster: " + e);
			}
		} finally {
			//Removal process, post-test
			try {
				testStmt = realConn.prepareStatement("DELETE FROM caster WHERE caster_id = ?;");
				testStmt.setInt(1, caster.getId());
				testStmt.executeUpdate();
			} catch (SQLException e) {
				fail("TEST ERROR, could not properly remove caster: " + e);
			}
		}
	}

	@Test
	public void testDeleteCaster() {
		try {
			//Insert test caster to be read
			String sql = "INSERT INTO caster VALUES "
						+"(?, ?, ?); "
						+"INSERT INTO caster_spell VALUES "
						+"(?, ?), (?, ?);";
			try {
				testStmt = realConn.prepareStatement(sql);
				testStmt.setInt(1, caster.getId());
				testStmt.setString(2, caster.getName());
				testStmt.setBoolean(3, caster.getHalfCaster());
				testStmt.setInt(4, caster.getId());
				testStmt.setInt(5, caster.getSpellIds()[0]);
				testStmt.setInt(6, caster.getId());
				testStmt.setInt(7, caster.getSpellIds()[1]);
				assertTrue("Error in inserting test caster", 1 == testStmt.executeUpdate());
			} catch (SQLException e) {
				fail("SQLException thrown in test setup: " + e);
			}

			//Prep statements with proper SQL
			sql = "DELETE FROM caster " 
				 +"WHERE caster_id = ?;";
			try {
				initStmtHelper(sql);
			} catch (SQLException e) {
				fail("SQLException thrown: " + e);
			}

			//Test deleteCaster functionality
			try {
				casterDao.deleteCaster(caster);

				//Ensure proper methods are called
				verify(spy1).setInt(1, caster.getId());
				verify(spy1).executeUpdate();

			} catch (SQLException e) {
				fail("SQLException thrown from testing deleteCaster: " + e);
			}
		} finally {
			//Attempt to delete object that was already deleted (Should throw exception)
			try {
				testStmt = realConn.prepareStatement("DELETE FROM caster WHERE caster_id = ?;");
				testStmt.setInt(1, caster.getId());
				assertEquals("Object was not deleted properly", 0, testStmt.executeUpdate());
			} catch (SQLException e) {}
		}
	}

	/**
	 * Helper method that initializes mock and spy variables using the prepared sql string provided
	 * @param sql -Prepared SQL String
	 */
	private void initStmtHelper(String sql) throws SQLException {
		//Prep Mockito Spy
		stmt = realConn.prepareStatement(sql);
		spy1 = Mockito.spy(stmt);


		//Set standard connection mocking methods
		when(connUtil.createConnection()).thenReturn(connection);
		when(connection.prepareStatement(sql)).thenReturn(spy1);
	}

	/**
	 * Helper method that initializes mock and spy variables using the prepared sql string provided
	 * @param sql -Prepared SQL String
	 */
	private void initStmtHelper2(String sql) throws SQLException {
		//Prep Mockito Spy
		stmt = realConn.prepareStatement(sql);
		spy2 = Mockito.spy(stmt);


		//Set standard connection mocking methods
		when(connUtil.createConnection()).thenReturn(connection);
		when(connection.prepareStatement(sql)).thenReturn(spy2);
	}

	/**
	 * Helper method that initializes mock and spy variables using the prepared sql string provided
	 * @param sql -Prepared SQL String
	 */
	private void initStmtHelper3(String sql) throws SQLException {
		//Prep Mockito Spy
		stmt = realConn.prepareStatement(sql);
		spy3 = Mockito.spy(stmt);


		//Set standard connection mocking methods
		when(connUtil.createConnection()).thenReturn(connection);
		when(connection.prepareStatement(sql)).thenReturn(spy3);
	}

	private void initCallableHelper(String sql) throws SQLException {
		//Prep Mockito callSpy
		callStmt = realConn.prepareCall(sql);
		callSpy = Mockito.spy(callStmt);

		when(connUtil.createConnection()).thenReturn(connection);
		when(connection.prepareCall(sql)).thenReturn(callSpy);

		ids = realConn.createArrayOf("int4", caster.getSpellIds());
		when(connection.createArrayOf("int4", caster.getSpellIds())).thenReturn(ids);

	}
 
}

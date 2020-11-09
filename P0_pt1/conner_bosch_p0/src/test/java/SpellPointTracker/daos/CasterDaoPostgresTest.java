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
	
	private PreparedStatement spy;
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
				verify(spy).setInt(1, caster.getId());
				verify(spy).setString(2, caster.getName());
				verify(spy).setBoolean(3, caster.getHalfCaster());
				verify(spy).executeUpdate();

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
				verify(spy).setInt(1, caster.getId());
				verify(spy).executeQuery();

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
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCaster() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteCaster() {
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

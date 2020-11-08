package SpellPointTracker.daos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
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
	private PreparedStatement stmt;
	private PreparedStatement testStmt;
	private PreparedStatement spy;

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
		caster = new Caster(1010, "Illithid", false, new int[] {1, 2});
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
			String sql = "BEGIN; INSERT INTO caster VALUES "
						+"(?, ?, ?); "
						+"CALL insert_caster_spell_list(?, ARRAY?); " 
						+"COMMIT; ";
			try {
				initStmtHelper(sql);
			} catch (SQLException e) {
				fail("SQLException thrown in test setup: " + e);
			}

			//Test createCaster functionality
			try {
				casterDao.createCaster(caster);

				//Verify statement was prepared properly
				verify(spy).setInt(1, caster.getId());
				verify(spy).setString(2, caster.getName());
				verify(spy).setBoolean(3, false);

				//Verify statement set caster_spell values properly
				verify(spy).setInt(4, caster.getId());
				verify(spy).setString(5, Arrays.toString(caster.getSpellIds()));

				verify(spy).executeUpdate();

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
		fail("Not yet implemented");
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

}

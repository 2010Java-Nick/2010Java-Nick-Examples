package SpellPointTracker.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionUtil {
    
    private Connection conn;

    private static Logger Log = Logger.getLogger("connectionLog");

    public Connection createConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?", "postgres", "password");
            return conn;
        } catch (SQLException e) {
            Log.warn("Threw SQL Exception while attempting to get connection: " + e);
            return null;
        }
    }

}

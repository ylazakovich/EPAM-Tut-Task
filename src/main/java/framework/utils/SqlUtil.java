package framework.utils;

import framework.Log;
import framework.PropertyReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Helps SqlManager to init PostgreSQLDriver
 * and set connect to database
 *
 * @author Yaroslav Lazakovich
 * @version 1.2
 */
public class SqlUtil extends SqlManager {
    private static final String DB_URL = PropertyReader.getProperty("db.url");
    private static final String USER = PropertyReader.getProperty("db.login");
    private static final String PASS = PropertyReader.getProperty("db.password");
    private static Log log = Log.getInstance();

    private static Connection connection = null;

    private static void initPostgreSQLDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            log.error("loc.err.db.con");
        }
    }

    protected static Connection setConnect() throws SQLException {
        initPostgreSQLDriver();
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
        return connection;
    }

    protected static Connection getConnection() throws SQLException {
        if (connection == null) {
            setConnect();
        }
        return connection;
    }
}

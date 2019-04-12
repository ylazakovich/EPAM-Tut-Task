package framework.dataFactory.utils.sql;

import framework.PropertyReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlUtil extends SqlManager {
    private static final String DB_URL = PropertyReader.getProperty("db.url");
    private static final String USER = PropertyReader.getProperty("db.login");
    private static final String PASS = PropertyReader.getProperty("db.password");
    private static Connection connection;

    private static void initPostgreSQLDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
        }
        System.out.println("PostgreSQL JDBC Driver successfully connected");

    }

    private static void isConnect() {
        if (connection != null) {
            System.out.println("You successfully connected to database now" + "\n");
        } else {
            System.out.println("Failed to make connection to database" + "\n");
        }
    }

    protected static Connection setConnect() throws SQLException {
        initPostgreSQLDriver();
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        isConnect();
        return connection;
    }

    protected static Connection getConnection() throws SQLException {
        if (connection == null) {
            setConnect();
        }
        return connection;
    }
}

package framework.sql;

import framework.factory.PropertyReader;

import java.sql.*;

public class SqlManager {
    private static final String DB_URL = PropertyReader.getProperty("db.url");
    private static final String USER = PropertyReader.getProperty("db.login");
    private static final String PASS = PropertyReader.getProperty("db.password");
    private static Connection connection;
    private static ResultSet rs;

    private static void initPostgreSQLDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
        }
        System.out.println("PostgreSQL JDBC Driver successfully connected");
    }

    private static void setConnect() {
        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
        }
        isConnect();
    }

    private static void isConnect() {
        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }

    private static Connection getConnection() {
        return connection;
    }

    public static void getUserEmail(int userId) {
        String SQL = "SELECT email "
                + "FROM users "
                + "WHERE id = " + String.valueOf(userId) + ";";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            rs = pstmt.executeQuery();
            System.out.println(rsResult(rs));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static String rsResult(ResultSet rs) throws SQLException {
        String result = null;
        while (rs.next()) {
            result = rs.getString("email");
        }
        rs.close();
        return result;
    }


    public static void main(String[] args) {
        initPostgreSQLDriver();
        setConnect();
        getUserEmail(1);
    }

}

package framework.factory.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlManager {
    private static final Connection connection = SqlUtil.setConnect();
    private static ResultSet rs;

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
        getUserEmail(1);
    }

}

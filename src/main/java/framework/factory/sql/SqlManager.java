package framework.factory.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlManager {
    private static ResultSet rs;

    private static String rsResult(ResultSet rs, String columnName) throws SQLException {
        String result = null;
        while (rs.next()) {
            result = rs.getString(columnName);
        }
        rs.close();
        return result;
    }

    private void valueOfColumn(int userId, String columnName) {
        String SQL = "SELECT " + columnName
                + " FROM users "
                + "WHERE id = " + String.valueOf(userId) + ";";
        try (Connection conn = SqlUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            rs = pstmt.executeQuery();
            System.out.println(rsResult(rs, columnName));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

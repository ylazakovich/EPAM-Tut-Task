package framework.factory.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlManager {
    private static ResultSet rs;
    private static String email;
    private static String password;

    private static void rsResult(ResultSet rs) throws SQLException {
        while (rs.next()) {
            email = rs.getString("email");
            password = rs.getString("password");
        }
    }

    public static void selectLine(int userId) {
        String SQL = "SELECT email, password" +
                " FROM users "
                + "WHERE id = " + String.valueOf(userId) + ";";
        try {
            Connection conn = SqlUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            rsResult(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }
}

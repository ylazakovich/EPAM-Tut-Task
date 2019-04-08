package framework.factory.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlManager {
    private static ResultSet rs;
    private static String email;
    private static String password;
    private static int count;

    private static void getUserLine(ResultSet rs) throws SQLException {
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
            getUserLine(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static int getSizeDB() {
        String SQL = "SELECT email FROM users;";
        try {
            Connection conn = SqlUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();
            count = rs.last() ? rs.getRow() : 0;
            rs.beforeFirst();
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

}

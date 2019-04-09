package framework.dataFactory.utils.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlManager {
    private static ResultSet rs;
    private static String field_1;
    private static String field_2;
    private static int count;

    private static void getValueOfFields(ResultSet rs) throws SQLException {
        while (rs.next()) {
            field_1 = rs.getString(1);
            field_2 = rs.getString(2);
        }
    }

    private static void getValueOfField(ResultSet rs) throws SQLException {
        while (rs.next()) {
            field_1 = rs.getString(1);
        }
    }

    public static void selectUser(int userId, String field_1, String field_2, String table) throws SQLException {
        String SQL = "SELECT " + field_1 + "," + field_2 +
                " FROM " + table + " WHERE id = " + String.valueOf(userId) + ";";
            Connection conn = SqlUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
        getValueOfFields(rs);
            rs.close();
            pstmt.close();

    }

    public static int getSizeTable(String field_1, String table) throws SQLException {
        String SQL = "SELECT " + field_1 + " FROM " + table + ";";
            Connection conn = SqlUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();
            count = rs.last() ? rs.getRow() : 0;
            rs.beforeFirst();
            rs.close();
            pstmt.close();
        return count;
    }

    public static String getField_1() {
        return field_1;
    }

    public static String getField_2() {
        return field_2;
    }

    public static void downConnect() throws SQLException {
        Connection connection = SqlUtil.getConnection();
        if (connection != null) {
            connection.close();
        }
    }

}

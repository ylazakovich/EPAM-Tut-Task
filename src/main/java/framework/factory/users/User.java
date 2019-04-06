package framework.factory.users;

import framework.factory.sql.SqlManager;

public class User {
    private static int count = 1;
    private String userName;
    private String password;

    public User() {
        SqlManager.selectLine(count++);
        userName = SqlManager.getEmail();
        password = SqlManager.getPassword();
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}

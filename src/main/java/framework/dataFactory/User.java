package framework.dataFactory;

import framework.DataFactory;

public class User extends DataFactory {
    private String email;
    private String password;

    public User() {
    }

    public User(String userName, String password) {
        this.email = userName;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}

package framework.factory;

import framework.factory.users.User;

import java.util.ArrayList;
import java.util.List;

public class DataStash {
    private static final String DATA_TYPE = PropertyReader.getProperty("dataType");
    private static final String XML = "xml";
    private static final String SQL = "sql";
    private static final String CSV = "csv";

    private static final List<User> users = new ArrayList<>();

    private static String userName;
    private static String password;

    private static List<User> getUserList() {
        switch (DATA_TYPE.toLowerCase()) {
            case SQL:
                break;
            case XML:
                break;
            case CSV:
                break;
        }

        return users;
    }

    private static List<User> getListBySQL() {

        return null;
    }


}

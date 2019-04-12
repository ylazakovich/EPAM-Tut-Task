package framework.dataFactory;

import framework.PropertyReader;
import framework.dataFactory.utils.UserListGenerator;

import java.util.ArrayList;
import java.util.List;

public class DataFactory {
    private static final String DATA_TYPE = PropertyReader.getProperty("dataType");

    public static List<User> getUserList() {
        List<User> users = new ArrayList<>();
        switch (DATA_TYPE.toLowerCase()) {
            case "sql":
                UserListGenerator.getUserListBySQL(users);
                break;
            case "xml":
                UserListGenerator.getUserListByXML(users);
                break;
            case "csv":
                UserListGenerator.getUserListByCSV(users);
                break;
            default:
                UserListGenerator.getUserListBySQL(users);
        }
        return users;
    }


}

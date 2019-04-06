package framework.factory.users;

import framework.factory.PropertyReader;

import java.util.List;

public class DataStash {
    private static final String dataType = PropertyReader.getProperty("dataType");
    private static List<User> list;

    public static List<User> getList() {
        return list;
    }
}

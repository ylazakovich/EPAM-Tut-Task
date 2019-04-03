package framework;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private final InputStream path = this.getClass().getClassLoader().getResourceAsStream("config.properties");

    private static final Object lock = new Object();
    private static PropertyReader instance;

    private String url;
    private String browserName;
    private long shortTimeOut;
    private long longTimeOut;
    private String host;
    private String sqlLogin;
    private String sqlPassword;


    protected static PropertyReader getInstance() {
        if (instance == null) {
            synchronized (lock) {
                instance = new PropertyReader();
                instance.loadData();
            }
        }
        return instance;
    }

    private void loadData() {
        Properties properties = new Properties();
        try {
            properties.load(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration properties file cannot be found");
        } catch (IOException e) {
            System.err.println("Bad canonical path");
        }

        url = properties.getProperty("url");
        browserName = properties.getProperty("browserName");
        shortTimeOut = Long.parseLong(properties.getProperty("shortTimeOut"));
        longTimeOut = Long.parseLong(properties.getProperty("longTimeOut"));
        host = properties.getProperty("db.host");
        sqlLogin = properties.getProperty("db.login");
        sqlPassword = properties.getProperty("db.password");
    }

    public String getUrl() {
        return url;
    }

    public String getBrowserName() {
        return browserName;
    }

    public long getShortTimeOut() {
        return shortTimeOut;
    }

    public long getLongTimeOut() {
        return longTimeOut;
    }
}

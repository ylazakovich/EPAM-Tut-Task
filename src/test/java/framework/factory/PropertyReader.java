package framework.factory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private final InputStream path = this.getClass().getClassLoader().getResourceAsStream("config.properties");

    private static final Object lock = new Object();
    private static PropertyReader instance;

    private Properties properties;

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
        properties = new Properties();
        try {
            properties.load(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration properties file cannot be found" + path);
        } catch (IOException e) {
            System.err.println("Bad canonical path");
        }
    }

    public String getProperty(String property) {
        return properties.getProperty(property);
    }
}

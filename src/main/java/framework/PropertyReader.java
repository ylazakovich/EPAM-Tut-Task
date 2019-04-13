package framework;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static Log log = Log.getInstance();
    private static final Object lock = new Object();
    private InputStream path = this.getClass().getClassLoader().getResourceAsStream("config.properties");
    private static PropertyReader instance;
    private static Properties properties;

    public static PropertyReader getInstance() {
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
            log.error("loc.err.properties.not.found");
        } catch (IOException e) {
            log.error("loc.err.properties.file.path");
        }
    }

    public static String getProperty(String property) {
        getInstance();
        return properties.getProperty(property);
    }
}

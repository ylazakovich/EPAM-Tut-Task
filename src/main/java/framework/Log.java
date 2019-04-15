package framework;

import framework.utils.UTF8Control;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

public class Log {
    public static final String LOG_DELIMITER = "::";
    private static final String SEPARATOR = "\n\r=============TEST END=============\n\r";
    private static final Logger LOG = LogManager.getLogger(Log.class.getName());
    private static int step;
    private static Log instance = null;
    private static Locale locale = new Locale("en");
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("localization/" + locale.toString().toLowerCase(), new UTF8Control());

    public static synchronized Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void step(String message) {
        LOG.info(("\r\n----------------STEP " + (step++) + "  " + message + "----------------\r\n"));
    }

    public void initStep(int steps) {
        step = steps;
    }

    public String getLogLoc(final String key) {
        return resourceBundle.getString(key);
    }

    public void info(final String message) {
        LOG.info(message);
    }

    public void warn(String message) {
        LOG.warn(message);
    }

    public void error(String message) {
        LOG.error(getLogLoc(message));
    }

    public void fatal(String message) {
        LOG.fatal(message);
    }

    public void debug(String message) {
        LOG.debug(message);
    }

    public void makeSeparator() {
        LOG.info(SEPARATOR);
    }

}

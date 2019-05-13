package framework;

import framework.utils.UTF8Control;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Log4j2
 *
 * @author Yaroslav Lazakovich
 * @version 1.2
 */
public class Log{
    public static final String LOG_DELIMITER = "::";
    private static final Logger LOG = LogManager.getLogger(Log.class.getName());
    private static final String SEPARATOR = "\n\r======================TEST END======================\n\r";
    private static Locale locale = new Locale(PropertyReader.getPropertyOrDefault("locale", "en"));
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("localization/" + locale.toString().toLowerCase(), new UTF8Control());
    private static Log instance = null;
    private static int step;

    public static synchronized Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void step(String message) {
        LOG.info(("\n\r----------------STEP " + (++step) + "  " + message + "----------------\n\r"));
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

    public String getLogLoc(final String key) {
        return resourceBundle.getString(key);
    }

}

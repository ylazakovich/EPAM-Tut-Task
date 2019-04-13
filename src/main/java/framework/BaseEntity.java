package framework;

import framework.browserFactory.BrowserFactory;
import framework.utils.SqlManager;
import framework.utils.Waiter;
import framework.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.sql.SQLException;

/**
 * Class BaseEntity is a parent class for Test class
 * Entity keep the instance via BrowserFactory.getInstance
 *
 * @author Yaroslav Lazakovich
 * @version 1.0 01 Apr 2019
 */


public abstract class BaseEntity {
    protected static Log logger = Log.getInstance();
    private static int step = 1;
    protected WebDriver driver;

    protected static String getLoc(final String key) {
        return logger.getLogLoc(key);
    }

    @BeforeClass
    public void before() {
        logger.initStep(step);
        driver = BrowserFactory.getInstance().getDriver();
        WebDriverManager.maximize(driver);
        Waiter.implicitWait(driver);
    }

    @AfterClass
    public void after() throws SQLException {
        WebDriverManager.close(driver);
        SqlManager.downConnect();
    }

    protected abstract String formatLogMsg(String message);

    protected void info(final String message) {
        logger.info(formatLogMsg(message));
    }

    protected void warn(final String message) {
        logger.warn(formatLogMsg(message));
    }

    protected void error(final String message) {
        logger.error(formatLogMsg(message));
    }

    protected void fatal(final String message) {
        logger.fatal(formatLogMsg(message));
    }
}

package framework;

import framework.dataFactory.User;
import framework.utils.SqlManager;
import framework.utils.Waiter;
import framework.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.sql.SQLException;
import java.util.List;

public abstract class BaseEntity {
    protected static Log logger = Log.getInstance();
    private static int step = 1;
    protected WebDriver driver;
    private List<User> users = DataFactory.getUserList();
    private String subject = "This is subject";
    private String message = "SSL: This is text";

    protected static String getLoc(final String key) {
        return logger.getLogLoc(key);
    }

    @BeforeClass
    public void before() {
        logger.initStep(step);
        driver = BrowserFactory.getInstance().getDriver();
        WebDriverManager.maximize(driver);
        Waiter.fluentWait(driver);
    }

    @AfterClass
    public void after() throws SQLException {
        WebDriverManager.close(driver);
        SqlManager.downConnect();
        logger.makeSeparator();
    }

    public List<User> getUsers() {
        return users;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public WebDriver getDriver() {
        return driver;
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





package framework;

import framework.utils.Waiter;
import framework.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Holds all main tests
 * (BrowserFactory, BaseTest, BasePage, BaseElement)
 *
 * @author Yaroslav Lazakovich
 * @version 1.2
 */
public abstract class BaseEntity {
    protected static WebDriver driver;

    protected static Log logger = Log.getInstance();

    @BeforeMethod
    public void before() {
        driver = BrowserFactory.getInstance().getDriver();
        WebDriverManager.maximize(driver);
        WebDriverManager.openUrl(driver);
        Waiter.implicitWait(driver);
        logger.info(logger.getLogLoc("loc.test.start"));
    }

    @AfterMethod
    public void turnDown() {
        WebDriverManager.close(driver);
        logger.makeSeparator();
    }
}





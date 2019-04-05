package framework;

import framework.factory.BrowserFactory;
import framework.factory.WebDriverManager;
import framework.factory.sql.SqlManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class BaseEntity {
    private final BrowserFactory factory = BrowserFactory.getInstance();

    private final WebDriverManager webDriverManager = factory.getWebDriverManager();
    private final SqlManager sqlManager = factory.getSqlManager();
    private WebDriver driver = factory.getDriver();

    @BeforeClass
    public void before() {
        webDriverManager.maximize(driver);
    }

    @AfterClass
    public void after() {
        webDriverManager.close(driver);
    }
}

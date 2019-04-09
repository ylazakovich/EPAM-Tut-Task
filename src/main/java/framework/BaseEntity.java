package framework;

import framework.browserFactory.BrowserFactory;
import framework.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class BaseEntity {
    private final BrowserFactory factory = BrowserFactory.getInstance();

    private WebDriverManager webDriverManager = factory.getWebDriverManager();
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

package framework;

import framework.factory.BrowserFactory;
import framework.factory.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class BaseEntity {
    private final BrowserFactory factory = BrowserFactory.getInstance();

    private WebDriverManager manager = factory.getManager();
    private WebDriver driver = factory.getDriver();

    @BeforeClass
    public void before() {
        manager.maximize(driver);
    }

    @AfterClass
    public void after() {
        manager.close(driver);
    }
}

package framework;

import framework.factory.BrowserFactory;
import framework.factory.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class BaseEntity {
    private final BrowserFactory factory = BrowserFactory.getInstance();

    private DriverManager manager = factory.getManager();
    private WebDriver driver = factory.getDriver();

    @BeforeClass
    public void before() {
        manager.maximize(driver);
    }

    @AfterClass
    public void after() {
        if (driver != null) {
            manager.close(driver);
        }
    }
}

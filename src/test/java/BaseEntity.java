import framework.BrowserFactory;
import framework.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class BaseEntity {
    private BrowserFactory factory = BrowserFactory.getInstance();
    private WebDriver driver;
    private DriverManager manager;

    @BeforeClass
    public void before() {
        driver = factory.getDriver();
        manager = factory.getManager();
    }

    @AfterClass
    public void after() {
        if (driver != null) {
            manager.close(driver);
        }
    }
}

import framework.BrowserFactory;
import framework.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class BaseEntity {
    private final BrowserFactory factory = BrowserFactory.getInstance();
    private DriverManager manager;
    private WebDriver driver;

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

import framework.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static framework.BrowserFactory.close;


public class BaseEntity {
    private BrowserFactory factory = BrowserFactory.getInstance();
    private WebDriver driver;

    @BeforeClass
    public void before() {
        driver = factory.getDriver();
    }

//    @AfterClass
//    public void after() {
//        close();
//    }
}

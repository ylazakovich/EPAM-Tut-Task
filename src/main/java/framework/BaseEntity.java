package framework;

import framework.browserFactory.BrowserFactory;
import framework.browserFactory.utils.WebDriverManager;
import framework.dataFactory.utils.sql.SqlManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.sql.SQLException;


public class BaseEntity {
    private final BrowserFactory factory = BrowserFactory.getInstance();

    private WebDriver driver = factory.getDriver();

    @BeforeClass
    public void before() {
        WebDriverManager.maximize(driver);
    }

    @AfterClass
    public void after() throws SQLException {
//        WebDriverManager.close(driver);
        SqlManager.downConnect();
    }
}

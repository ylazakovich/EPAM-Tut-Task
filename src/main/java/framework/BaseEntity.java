package framework;

import framework.browserFactory.BrowserFactory;
import framework.utils.WebDriverManager;
import framework.utils.sql.SqlManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.sql.SQLException;


public class BaseEntity {
    private final BrowserFactory factory = BrowserFactory.getInstance();

    private WebDriverManager webDriverManager = factory.getWebDriverManager();
    private WebDriver driver = factory.getDriver();

    @BeforeClass
    public void before() {
        webDriverManager.maximize(driver);
    }

    @AfterClass
    public void after() throws SQLException {
        webDriverManager.close(driver);
        SqlManager.downConnect();
    }
}

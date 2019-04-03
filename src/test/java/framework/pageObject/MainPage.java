package framework.pageObject;

import framework.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class MainPage extends BasePage {
    private static By uniqueElement = By.xpath("//a[@class='enter']");
    private final BrowserFactory factory = BrowserFactory.getInstance();
    private String message = "The main page hasn't logIn button";
    private WebDriver driver = factory.getDriver();

    public MainPage() {
        super(uniqueElement);
        Assert.assertEquals(isOpen(uniqueElement), true, message);
    }
}

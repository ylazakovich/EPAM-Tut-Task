package framework.pageObject;

import org.openqa.selenium.By;
import org.testng.Assert;

public class MainPage extends BasePage {
    private static By uniqueElement = By.xpath("//a[@class='enter']");
    private String message = "The main page hasn't logIn button";

    public MainPage() {
        super(uniqueElement);
        Assert.assertEquals(isOpen(uniqueElement), true, message);
    }
}

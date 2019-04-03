package framework.pageObject;

import org.openqa.selenium.By;
import org.testng.Assert;

public class MainPage extends BasePage {
    private static By uniqueElement = By.xpath("//div[@class='l-outer']");

    public MainPage() {
        super(uniqueElement);
        Assert.assertTrue(isOpen(uniqueElement));
    }
}

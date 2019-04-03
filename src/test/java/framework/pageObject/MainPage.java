package framework.pageObject;

import org.openqa.selenium.By;

public class MainPage extends BasePage {
    private static By uniqueElement = By.xpath("//a[@class='enter']");

    public MainPage() {
        super(uniqueElement);
    }
}

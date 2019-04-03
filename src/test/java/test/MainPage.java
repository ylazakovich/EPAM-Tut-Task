package test;

import org.openqa.selenium.By;
import pageObject.BasePage;

public class MainPage extends BasePage {
    private static By uniqueElement = By.xpath("//a[@class='enter']");

    public MainPage() {
        super(uniqueElement);
    }
}

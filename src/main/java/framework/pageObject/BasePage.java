package framework.pageObject;

import framework.browserFactory.BrowserFactory;
import framework.elements.Button;
import framework.utils.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public abstract class BasePage {
    private final BrowserFactory factory = BrowserFactory.getInstance();
    private WebDriver driver = factory.getDriver();

    private By locator;
    private Button button;

    public BasePage(By locator) {
        Waiter.fluentWait(getDriver(), locator);
        init(locator);
        Assert.assertEquals(true, isOpen(locator));
    }

    public boolean isOpen(By titleLocator) {
        this.button = new Button(titleLocator);
        return button.isEnable();
    }

    public void init(By pageLocator) {
        this.locator = pageLocator;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void escapeFromADB() {
        WebElement body = getDriver().findElement(By.tagName("body"));
        body.sendKeys(Keys.SPACE);
    }
}

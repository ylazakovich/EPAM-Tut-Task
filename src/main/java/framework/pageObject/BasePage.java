package framework.pageObject;

import framework.browserFactory.BrowserFactory;
import framework.browserFactory.utils.Waiter;
import framework.elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public abstract class BasePage {
    private final BrowserFactory factory = BrowserFactory.getInstance();
    private WebDriver driver = factory.getDriver();

    private By locator;
    private Button button;

    public BasePage(By locator) {
        Waiter.fluentWait(driver, locator);
        PageFactory.initElements(driver, this);
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

}

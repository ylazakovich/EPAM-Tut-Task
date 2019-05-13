package framework;

import framework.elements.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


/**
 * Realizes PageObject pattern
 *
 * @author Yaroslav Lazakovich
 * @version 1.2
 */

public abstract class BasePage extends BaseEntity {
    private By pageLocator;
    private String title;
    private Component component;

    public BasePage(By locator, String tittle) {
        init(locator, tittle);
        logger.info(logger.getLogLoc("loc.open.page") + " [" + tittle + "]");
        Assert.assertTrue(isOpen());
    }

    public boolean isOpen() {
        this.component = new Component(pageLocator);
        return component.isDisplayed();
    }

    private void init(By locator, String title) {
        this.pageLocator = locator;
        this.title = title;
    }

    public void escapeFromADB() {
        WebElement body = driver.findElement(By.tagName("body"));
        body.sendKeys(Keys.SPACE);
    }
}

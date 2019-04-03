package framework.elements;

import framework.BaseEntity;
import framework.factory.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public abstract class BaseElement extends BaseEntity {
    private final BrowserFactory factory = BrowserFactory.getInstance();

    private WebElement element;
    private By by;
    private Actions actions;
    private WebDriver driver = factory.getDriver();

    public BaseElement() {
    }

    public BaseElement(By by) {
        this.by = by;
    }

    public WebElement getElement(By by) {
        return by != null ? driver.findElement(by) : element;
    }

    public List<WebElement> getElements(By by) {
        return driver.findElements(by);
    }

    public String getElementText(WebElement element) {
        element = getElement(by);
        if (isEnable()) {
            return element.getText();
        } else {
            return "";
        }
    }

    public boolean isEnable() {
        try {
            element = getElement(by);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void moveToElement() {
        actions = new Actions(driver);
        actions.moveToElement(getElement(by)).build().perform();
    }
}

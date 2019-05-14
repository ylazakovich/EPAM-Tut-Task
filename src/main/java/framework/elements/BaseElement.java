package framework.elements;

import framework.BaseEntity;
import framework.BrowserFactory;
import framework.Log;
import framework.utils.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

/**
 * Has basic features for webElements
 *
 * @author Yaroslav Lazakovich
 * @version 1.0
 */
public abstract class BaseElement extends BaseEntity {
    private WebElement element;
    private String name;
    private By by;

    public BaseElement(By by) {
        this.by = by;
    }

    public BaseElement(String name, By by) {
        this.name = name;
        this.by = by;
    }

    public WebElement getElement() {
        waitForIsElementPresent();
        return by != null ? driver.findElement(by) : element;
    }

    public String getElementText() {
        element = getElement();
        if (isDisplayed()) {
            return element.getText();
        } else {
            return "";
        }
    }

    public List<WebElement> getElements() {
        waitForIsElementPresent();
        return driver.findElements(by);
    }

    public List<String> getTextElements() {
        waitForIsElementPresent();
        List<String> text = new ArrayList<>();
        for (WebElement webElement :
                getElements()) {
            text.add(webElement.getText());
        }
        return text;
    }

    public boolean isDisplayed() {
        try {
            element = getElement();
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void moveToElementAndClick() {
        Actions actions = new Actions(driver);
        actions
                .moveToElement(getElement())
                .click(getElement())
                .build()
                .perform();
    }

    public void click() {
        getElement().click();
    }

    private void waitForIsElementPresent() {
        Waiter.presenceOfElementLocated(by);
    }
}
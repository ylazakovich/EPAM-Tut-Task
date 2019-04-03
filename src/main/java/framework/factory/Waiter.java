package framework.factory;

import framework.BaseEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Waiter extends BaseEntity {
    private static final long timeOut = Long.parseLong(PropertyReader.getProperty("shortTimeOut"));
    private static final long longTimeOut = Long.parseLong(PropertyReader.getProperty("longTimeOut"));
    private static final long timeMilliseconds = Long.parseLong(PropertyReader.getProperty("timeMilliseconds"));

    public static void implicitWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    public static void explicitWait(WebDriver driver, By by) {
        new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static FluentWait<WebDriver> fluentWaitEx(WebDriver driver, By by) {
        FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).
                withTimeout(longTimeOut, TimeUnit.SECONDS)
                .pollingEvery(timeMilliseconds, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        if (by != null) {
            fluentWait.until(ExpectedConditions.elementToBeClickable(by));
        }
        return fluentWait;
    }

    public static void fluentWait(WebDriver driver, By by) {
        fluentWaitEx(driver, by);
    }

    public static FluentWait<WebDriver> fluentWait(WebDriver driver) {
        return fluentWaitEx(driver, null);
    }
}

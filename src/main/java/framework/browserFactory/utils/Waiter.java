package framework.browserFactory.utils;

import framework.BaseEntity;
import framework.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Waiter extends BaseEntity {
    private static final long timeOut = Long.parseLong(PropertyReader.getProperty("shortTimeOut"));
    private static final Duration longTimeOut = Duration.ofSeconds(Long.parseLong(PropertyReader.getProperty("longTimeOut")));
    private static final Duration timeMilliseconds = Duration.ofMillis(Long.parseLong(PropertyReader.getProperty("timeMilliseconds")));

    public static void implicitWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    public static void explicitWait(WebDriver driver, By by) {
        new WebDriverWait(driver, longTimeOut.getSeconds()).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static FluentWait<WebDriver> fluentWaitEx(WebDriver driver, By by) {
        FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).
                withTimeout(longTimeOut)
                .pollingEvery(timeMilliseconds)
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
package framework.utils;

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
    private static final Duration TIME_MILLISECONDS = Duration.ofMillis(Long.parseLong(PropertyReader.getProperty("timeMilliseconds")));
    private static final Duration LONG_TIME_OUT = Duration.ofSeconds(Long.parseLong(PropertyReader.getProperty("longTimeOut")));
    private static final long TIME_OUT = Long.parseLong(PropertyReader.getProperty("shortTimeOut"));

    public static void implicitWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(TIME_OUT, TimeUnit.SECONDS);
    }

    public static void explicitWait(WebDriver driver, By by) {
        new WebDriverWait(driver, LONG_TIME_OUT.getSeconds()).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static FluentWait<WebDriver> fluentWaitEx(WebDriver driver, By by) {
        FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).
                withTimeout(LONG_TIME_OUT)
                .pollingEvery(TIME_MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        if (by != null) {
            fluentWait.until(ExpectedConditions.presenceOfElementLocated(by));
        }
        return fluentWait;
    }

    public static void fluentWait(WebDriver driver, By by) {
        fluentWaitEx(driver, by);
    }

    public static FluentWait<WebDriver> fluentWait(WebDriver driver) {
        return fluentWaitEx(driver, null);
    }

    @Override
    protected String formatLogMsg(String message) {
        return message;
    }
}
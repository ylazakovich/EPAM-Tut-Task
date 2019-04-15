package framework.utils;

import framework.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class WebDriverManager {
    private static Log log = Log.getInstance();

    public static void openUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public static void maximize(WebDriver driver) {
        driver.manage().window().maximize();
    }

    public static void refresh(WebDriver driver) {
        driver.navigate().refresh();
    }

    public static void close(WebDriver driver) {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (WebDriverException exception) {
            log.error("loc.err.wd.ex");
        } finally {
            driver.quit();
        }
    }

}

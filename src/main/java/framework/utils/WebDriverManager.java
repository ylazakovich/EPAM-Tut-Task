package framework.utils;

import org.openqa.selenium.WebDriver;

public class WebDriverManager {

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
        if (driver != null) {
            driver.quit();
        }
    }

}

package framework.factory;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    public void openUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public void maximize(WebDriver driver) {
        driver.manage().window().maximize();
    }

    public void refresh(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void close(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }

}

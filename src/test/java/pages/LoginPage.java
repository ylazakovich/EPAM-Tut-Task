package pages;

import framework.BasePage;
import framework.Log;
import framework.PropertyReader;
import framework.elements.Component;
import framework.utils.Waiter;
import framework.utils.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;


public class LoginPage extends BasePage {
    private Component loginForm, logOutComponent;

    public LoginPage() {
        super(By.id("search_from_str"), "Login Page");
    }

    @Step("Log out from profile")
    public void logOut() {
        logger.step("logout current profile");
        loginForm = new Component(By.xpath("//a[contains(@class, 'enter')]"));
        loginForm.moveToElementAndClick();
        logOutComponent = new Component(By.xpath("//a[contains(@href, 'logout')]"));
        logOutComponent.moveToElementAndClick();
    }

    @Step("Go to Mail Page")
    public MailPage goToEmail() {
        logger.step("go to email page");
        WebDriverManager.openUrl(driver, PropertyReader.getProperty("mailUrl"));
        return new MailPage();
    }
}

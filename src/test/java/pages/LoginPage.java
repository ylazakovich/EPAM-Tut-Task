package pages;

import framework.BasePage;
import framework.PropertyReader;
import framework.elements.Button;
import framework.utils.Waiter;
import framework.utils.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;


public class LoginPage extends BasePage {
    private static By loginPageLocator = By.id("search_from_str");
    private By loginFormLocator = By.xpath("//a[contains(@class, 'enter')]");
    private Button loginForm = new Button(loginFormLocator);
    private By logOutLocator = By.xpath("//a[contains(@href, 'logout')]");
    private Button logOutButton = new Button(logOutLocator);

    public LoginPage() {
        super(loginPageLocator);
    }

    @Step("Log out from profile")
    public void logOut() {
        loginForm.moveToElementAndClick();
        Waiter.fluentWait(getDriver(), logOutLocator);
        logOutButton.moveToElementAndClick();
    }

    public static By getLoginPageLocator() {
        return loginPageLocator;
    }

    @Step("Go to Mail Page")
    public MailPage goToEmail() {
        WebDriverManager.openUrl(getDriver(), PropertyReader.getProperty("mailUrl"));
        Waiter.explicitWait(getDriver(), MailPage.getMailPageLocator());
        return new MailPage();
    }
}

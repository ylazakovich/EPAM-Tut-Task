package pages;

import framework.PropertyReader;
import framework.elements.Button;
import framework.pageObject.BasePage;
import framework.utils.Waiter;
import framework.utils.WebDriverManager;
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

    public void logOut() {
        loginForm.moveToElementAndClick();
        Waiter.fluentWait(getDriver(), logOutLocator);
        logOutButton.moveToElementAndClick();
    }

    public static By getLoginPageLocator() {
        return loginPageLocator;
    }

    public MailPage goToEmail() {
        WebDriverManager.openUrl(getDriver(), PropertyReader.getProperty("mailUrl"));
        Waiter.explicitWait(getDriver(), MailPage.getMailPageLocator());
        return new MailPage();
    }
}

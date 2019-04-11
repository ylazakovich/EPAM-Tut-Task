package pages;

import framework.PropertyReader;
import framework.browserFactory.utils.Waiter;
import framework.browserFactory.utils.WebDriverManager;
import framework.elements.Button;
import framework.pageObject.BasePage;
import org.openqa.selenium.By;


public class LoginPage extends BasePage {
    private static By uniqLocator = By.id("search_from_str");

    private By loginFormLocator = By.xpath("//a[contains(@class, 'enter')]");
    private Button loginForm = new Button(loginFormLocator);
    private By logOutLocator = By.xpath("//a[contains(@href, 'logout')]");
    private Button logOutButton = new Button(logOutLocator);

    public LoginPage() {
        super(uniqLocator);
    }

    public void logOut() {
        loginForm.moveToElement();
        Waiter.fluentWait(getDriver(), logOutLocator);
        logOutButton.moveToElement();
    }

    public static By getUniqLocator() {
        return uniqLocator;
    }

    public MailPage goToEmail() {
        WebDriverManager.openUrl(getDriver(), PropertyReader.getProperty("mailUrl"));
        Waiter.explicitWait(getDriver(), MailPage.getMailLocator());
        return new MailPage();
    }
}

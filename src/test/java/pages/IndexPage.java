package pages;

import framework.BasePage;
import framework.Log;
import framework.dataFactory.User;
import framework.elements.Button;
import framework.elements.Input;
import framework.utils.Waiter;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class IndexPage extends BasePage {
    private static Log logger = Log.getInstance();
    private static By indexPageLocator = By.xpath("//a[@class='enter']");
    private By loginFormLocator = By.xpath("//a[contains(@class, 'enter')]");
    private By userNameLocator = By.xpath("//div[@class='i-holder']/input[@name='login']");
    private By passwordLocator = By.xpath("//div[@class='i-holder']/input[@name='password']");
    private By submitLocator = By.xpath("//div[@class='b-hold']/input[@type='submit']");
    private Button loginForm = new Button(loginFormLocator);
    private Button submit = new Button(submitLocator);
    private Input userName = new Input(userNameLocator);
    private Input password = new Input(passwordLocator);


    public IndexPage() {
        super(indexPageLocator);
    }

    public static By getIndexPageLocator() {
        return indexPageLocator;
    }

    @Step("Login step with user: {0}")
    public LoginPage authorization(User user) {
        logger.step("Log in: " + user.getEmail());
        escapeFromADB();
        loginForm.moveToElementAndClick();
        Waiter.fluentWait(getDriver(), userNameLocator);
        userName.sendKeys(userNameLocator, user.getEmail());
        password.sendKeys(passwordLocator, user.getPassword());
        submit.moveToElementAndClick();
        Waiter.fluentWait(getDriver(), LoginPage.getLoginPageLocator());
        return new LoginPage();
    }
}

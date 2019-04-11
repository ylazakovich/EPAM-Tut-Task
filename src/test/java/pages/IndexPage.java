package pages;

import framework.browserFactory.utils.Waiter;
import framework.dataFactory.User;
import framework.elements.Button;
import framework.elements.Input;
import framework.pageObject.BasePage;
import org.openqa.selenium.By;

public class IndexPage extends BasePage {
    private By loginFormLocator = By.xpath("//a[contains(@class, 'enter')]");
    private Button loginForm = new Button(loginFormLocator);
    private By userNameLocator = By.xpath("//div[@class='i-holder']/input[@name='login']");
    private Input userName = new Input(userNameLocator);
    private By passwordLocator = By.xpath("//div[@class='i-holder']/input[@name='password']");
    private Input password = new Input(passwordLocator);
    private By submitLocator = By.xpath("//div[@class='b-hold']/input[@type='submit']");
    private Button submit = new Button(submitLocator);

    private static By uniqueElement = By.xpath("//a[@class='enter']");
    private static By registrationLocator = By.xpath("//a[contains(@href,'https://profile.tut.by/newtut.html')]");

    public IndexPage() {
        super(uniqueElement);
    }

    public static By getUniqueElement() {
        return uniqueElement;
    }

    public LoginPage authorization(User user) {
        loginForm.moveToElement();
        Waiter.fluentWait(getDriver(), userNameLocator);
        userName.sendKeys(userNameLocator, user.getEmail());
        password.sendKeys(passwordLocator, user.getPassword());
        submit.moveToElement();
        Waiter.fluentWait(getDriver(), LoginPage.getUniqLocator());
        return new LoginPage();
    }
}

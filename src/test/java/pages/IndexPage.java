package pages;

import framework.browserFactory.utils.Waiter;
import framework.dataFactory.User;
import framework.elements.Button;
import framework.elements.Input;
import framework.pageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class IndexPage extends BasePage {
    private static By indexPageLocator = By.xpath("//a[@class='enter']");

    private By loginFormLocator = By.xpath("//a[contains(@class, 'enter')]");
    private Button loginForm = new Button(loginFormLocator);
    private By userNameLocator = By.xpath("//div[@class='i-holder']/input[@name='login']");
    private Input userName = new Input(userNameLocator);
    private By passwordLocator = By.xpath("//div[@class='i-holder']/input[@name='password']");
    private Input password = new Input(passwordLocator);
    private By submitLocator = By.xpath("//div[@class='b-hold']/input[@type='submit']");
    private Button submit = new Button(submitLocator);

    public IndexPage() {
        super(indexPageLocator);
    }

    public static By getIndexPageLocator() {
        return indexPageLocator;
    }

    public LoginPage authorization(User user) {
        escapeFromADB();
        loginForm.moveToElement();
        Waiter.fluentWait(getDriver(), userNameLocator);
        userName.sendKeys(userNameLocator, user.getEmail());
        password.sendKeys(passwordLocator, user.getPassword());
        submit.moveToElement();
        Waiter.fluentWait(getDriver(), LoginPage.getLoginPageLocator());
        return new LoginPage();
    }

    private void escapeFromADB() {
        WebElement body = getDriver().findElement(By.tagName("body"));
        body.sendKeys(Keys.SPACE);
    }
}

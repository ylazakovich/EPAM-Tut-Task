package pages;

import framework.dataFactory.User;
import framework.pageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    @FindBy(xpath = "//a[contains(@class, 'enter')]")
    private WebElement loginForm;
    @FindBy(xpath = "//a[contains(@href, 'logout')]")
    private WebElement logOutButton;
    @FindBy(xpath = "//div[@class='i-holder']/input[@name='login']")
    private WebElement userName;
    @FindBy(xpath = "//div[@class='i-holder']/input[@name='password']")
    private WebElement password;
    @FindBy(xpath = "//div[@class='b-hold']/input[@type='submit']")
    private WebElement submit;

    private static By uniqueElement = By.xpath("//a[@class='enter']");

    public MainPage() {
        super(uniqueElement);
    }

    private void activateLogIn() {
        loginForm.click();
    }

    public void authorization(User user) {
        activateLogIn();
        userName.clear();
        userName.sendKeys(user.getUserName());
        password.clear();
        password.sendKeys(user.getPassword());
        submit.click();
    }


}

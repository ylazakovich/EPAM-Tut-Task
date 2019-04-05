package pages;

import framework.pageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    private static By uniqLocator = By.xpath("//span[@class='uname']");

    @FindBy(xpath = "//span[@class='uname']")
    private WebElement logForm;
    @FindBy(xpath = "//a[contains(@href, 'logout')]")
    private WebElement logOutButton;

    public LoginPage() {
        super(uniqLocator);
    }

    private void activateLogOutForm() {
        logForm.click();
    }

    public void logOut() {
        activateLogOutForm();
        logOutButton.click();
    }
}

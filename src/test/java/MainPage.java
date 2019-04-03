import framework.factory.BrowserFactory;
import framework.pageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    private final BrowserFactory factory = BrowserFactory.getInstance();
    private WebDriver driver = factory.getDriver();

    @FindBy(xpath = "//a[@class='enter']")
    private WebElement loginButton;
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
        loginButton.click();
    }

    public void authorization(String name, String pass) {
        activateLogIn();
        userName.sendKeys(name);
        password.sendKeys(pass);
        submit.click();
    }


}

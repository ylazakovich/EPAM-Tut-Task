package pages;

import framework.pageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MailPage extends BasePage {
    private static By mailLocator = By.xpath("//div[@class='mail-User-Name']");

    @FindBy(xpath = "//div[contains(@data-key,'view=folders')]/a[2]")
    private WebElement sentFolder;

    @FindBy(xpath = "//div[contains(@class,'mail-Message')]/span[2]")
    private WebElement messageContent;

    public MailPage() {
        super(mailLocator);
    }

    public static By getMailLocator() {
        return mailLocator;
    }

    public void goToSentFolder() {
        sentFolder.click();
    }


}

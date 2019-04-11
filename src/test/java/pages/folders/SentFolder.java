package pages.folders;

import framework.PropertyReader;
import framework.browserFactory.utils.Waiter;
import framework.browserFactory.utils.WebDriverManager;
import framework.elements.Title;
import framework.pageObject.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.LoginPage;

public class SentFolder extends BasePage {
    private static By sentFolderLocator = By.xpath("локатор");

    // Sent Folder
    private By recipientEmails = By.xpath("//span[@class='mail-MessageSnippet-FromText']");
    private By subjectColumn = By.xpath("//span[contains(@class,'mail') and contains(@class, 'Item_subject')]/span[@title]");
    private Title recipientTitles = new Title(recipientEmails);
    private Title subjectTitles = new Title(subjectColumn);


    public SentFolder() {
        super(sentFolderLocator);
    }

    public static By getSentFolderLocator() {
        return sentFolderLocator;
    }

    public SentFolder assertSentMessage(String recipientEmail, String expectedSubject) {
        String actualEmail = recipientTitles.getTextElements(recipientEmails).get(0);
        Assert.assertEquals(actualEmail, recipientEmail);

        String actualSubject = subjectTitles.getTextElements(subjectColumn).get(0);
        Assert.assertEquals(actualSubject, expectedSubject);
        return this;
    }

    public LoginPage goToLoginPage() {
        WebDriverManager.openUrl(getDriver(), PropertyReader.getProperty("url"));
        Waiter.fluentWait(getDriver(), LoginPage.getLoginPageLocator());
        return new LoginPage();
    }
}

package pages;

import framework.PropertyReader;
import framework.browserFactory.utils.Waiter;
import framework.browserFactory.utils.WebDriverManager;
import framework.elements.Label;
import framework.elements.Title;
import framework.pageObject.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class FolderPage extends BasePage {
    private static By folderLocator = By.xpath("//div[@class='mail-Layout-Content']");

    // Inbox Folder
    private By messageInboxFolder = By.xpath("//div[contains(@class,'mail-MessagesList')]");
    private By messageLine = By.xpath("//div[@class='mail-MessageSnippet-Content']");
    private Label inboxFolderLabel;

    // Sent Folder
    private By recipientEmails = By.xpath("//span[@class='mail-MessageSnippet-FromText']");
    private By subjectColumn = By.xpath("//span[contains(@class,'mail') and contains(@class, 'Item_subject')]/span[@title]");
    private Title recipientTitles = new Title(recipientEmails);
    private Title subjectTitles = new Title(subjectColumn);


    public FolderPage() {
        super(folderLocator);
    }

    public static By getFolderLocator() {
        return folderLocator;
    }

    public LoginPage goToLoginPage() {
        WebDriverManager.openUrl(getDriver(), PropertyReader.getProperty("url"));
        Waiter.fluentWait(getDriver(), LoginPage.getLoginPageLocator());
        return new LoginPage();
    }

    public MessagePage goToFirstMessage() {
        Label inboxFolderLabel = new Label(messageInboxFolder);
        inboxFolderLabel.getElements(messageLine).get(0).click();
        Waiter.fluentWait(getDriver(), MessagePage.getMessagePageLocator());
        return new MessagePage();
    }

    public FolderPage assertSentMessage(String recipientEmail, String expectedSubject) {
        String actualEmail = recipientTitles.getTextElements(recipientEmails).get(0);
        Assert.assertEquals(actualEmail, recipientEmail);

        String actualSubject = subjectTitles.getTextElements(subjectColumn).get(0);
        Assert.assertEquals(actualSubject, expectedSubject);
        return this;
    }
}

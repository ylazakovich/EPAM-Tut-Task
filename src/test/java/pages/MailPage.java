package pages;

import framework.PropertyReader;
import framework.browserFactory.utils.Waiter;
import framework.browserFactory.utils.WebDriverManager;
import framework.elements.Label;
import framework.elements.Title;
import framework.pageObject.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class MailPage extends BasePage {
    private static By mailPageLocator = By.xpath("//div[@class='mail-User-Name']");

    private By nestedLocator = By.xpath("//div[@data-key='view=folders']");
    private By nestedItem = By.xpath("//span[@class='mail-NestedList-Item-Name']");
    private Label nestedList;

    // Inbox Folder
    private By messageInboxFolder = By.xpath("//div[contains(@class,'mail-MessagesList')]");
    private By messageLine = By.xpath("//div[@class='mail-MessageSnippet-Content']");
    private Label inboxFolderLabel;

    // Sent Folder
    private By recipientEmails = By.xpath("//span[@class='mail-MessageSnippet-FromText']");
    private By subjectColumn = By.xpath("//span[contains(@class,'mail') and contains(@class, 'Item_subject')]/span[@title]");
    private Title recipientTitles, subjectTitles;

    public MailPage() {
        super(mailPageLocator);
    }

    public static By getMailPageLocator() {
        return mailPageLocator;
    }

    public LoginPage goToLoginPage() {
        WebDriverManager.openUrl(getDriver(), PropertyReader.getProperty("url"));
        Waiter.fluentWait(getDriver(), LoginPage.getLoginPageLocator());
        return new LoginPage();
    }

    public MailPage goToSentFolder() {
        nestedList = new Label(nestedLocator);
        nestedList.getElements(nestedItem).get(1).click();
        Waiter.explicitWait(getDriver(), recipientEmails);
        return this;
    }

    public MessagePage goToFirstMessage() {
        Label inboxFolderLabel = new Label(messageInboxFolder);
        inboxFolderLabel.getElements(messageLine).get(0).click();
        Waiter.fluentWait(getDriver(), MessagePage.getMessagePageLocator());
        return new MessagePage();
    }

    public MailPage assertSentMessage(String recipientEmail, String expectedSubject) {
        recipientTitles = new Title(recipientEmails);
        String actualEmail = recipientTitles.getTextElements(recipientEmails).get(0);
        Assert.assertEquals(actualEmail, recipientEmail);

        subjectTitles = new Title(subjectColumn);
        String actualSubject = subjectTitles.getTextElements(subjectColumn).get(0);
        Assert.assertEquals(actualSubject, expectedSubject);
        return this;
    }

}

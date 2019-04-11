package pages;

import framework.PropertyReader;
import framework.browserFactory.utils.Waiter;
import framework.browserFactory.utils.WebDriverManager;
import framework.elements.Label;
import framework.pageObject.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class MailPage extends BasePage {
    private static By mailLocator = By.xpath("//div[@class='mail-User-Name']");

    // Sent Folder
    private static By nestedLocator = By.xpath("//div[@data-key='view=folders']");
    private static By nestedItem = By.xpath("//span[@class='mail-NestedList-Item-Name']");
    private static Label nestedList;

    private static By recipientEmails = By.xpath("//span[@class='mail-MessageSnippet-FromText']");
    private static By subjectColumn = By.xpath("//span[contains(@class,'mail') and contains(@class, 'Item_subject')]/span[@title]");
    private static Label recipientLabel;
    private static Label subjectLabel;

    // Inbox Folder
    private static By messageInboxFolder = By.xpath("//div[contains(@class,'mail-MessagesList')]");
    private static By messageLine = By.xpath("//div[@class='mail-MessageSnippet-Content']");
    private static Label inboxFolderLabel;


    public MailPage() {
        super(mailLocator);
    }

    public static By getMailLocator() {
        return mailLocator;
    }

    public MailPage goToSentFolder() {
        nestedList = new Label(nestedLocator);
        nestedList.getElements(nestedItem).get(1).click();
        Waiter.fluentWait(getDriver(), recipientEmails);
        recipientLabel = new Label(recipientEmails);
        subjectLabel = new Label(subjectColumn);
        return this;

    }

    public MessagePage goToFirstMessage() {
        Label inboxFolderLabel = new Label(messageInboxFolder);
        inboxFolderLabel.getElements(messageLine).get(0).click();
        Waiter.fluentWait(getDriver(), MessagePage.getSenderLocator());
        return new MessagePage();
    }

    public MailPage assertSentMessage(String recipientEmail, String expectedSubject) {
        String actualEmail = recipientLabel.getElements(recipientEmails).get(0).getAttribute("title");
        Assert.assertEquals(actualEmail, recipientEmail);

        String actualSubject = subjectLabel.getElements(subjectColumn).get(0).getText();
        Assert.assertEquals(actualSubject, expectedSubject);
        return this;
    }

    public LoginPage goToLoginPage() {
        WebDriverManager.openUrl(getDriver(), PropertyReader.getProperty("url"));
        Waiter.fluentWait(getDriver(), LoginPage.getUniqLocator());
        return new LoginPage();
    }


}

package pages;

import framework.BasePage;
import framework.Log;
import framework.PropertyReader;
import framework.elements.Label;
import framework.elements.Title;
import framework.utils.Waiter;
import framework.utils.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class MailPage extends BasePage {
    private static Log logger = Log.getInstance();
    private static By mailPageLocator = By.xpath("//div[@class='mail-User-Name']");
    // Menu links of the folders
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

    @Step("Go to Login Page")
    public LoginPage goToLoginPage() {
        logger.step("return to login page");
        WebDriverManager.openUrl(getDriver(), PropertyReader.getProperty("url"));
        Waiter.fluentWait(getDriver(), LoginPage.getLoginPageLocator());
        return new LoginPage();
    }

    @Step("Go to Sent Folder")
    public MailPage goToSentFolder() {
        logger.step("go to Sent folder");
        nestedList = new Label(nestedLocator);
        nestedList.getElements(nestedItem).get(1).click();
        Waiter.explicitWait(getDriver(), recipientEmails);
        return this;
    }

    @Step("Go to 1st message of the inbox folder")
    public MessagePage goToFirstMessage() {
        logger.step("open 1st message in inbox folder");
        Label inboxFolderLabel = new Label(messageInboxFolder);
        inboxFolderLabel.getElements(messageLine).get(0).click();
        Waiter.explicitWait(getDriver(), MessagePage.getMessagePageLocator());
        return new MessagePage();
    }

    @Step("Check the recipient and the subject of message\n" +
            "recipient: {0}\n subject of the message: {1}")
    public MailPage verifyMessage(String recipientEmail, String expectedSubject) {
        logger.step("assert recipient of message and subject of message");
        recipientTitles = new Title(recipientEmails);
        String actualEmail = recipientTitles.getTextElements(recipientEmails).get(0);
        Assert.assertEquals(actualEmail, recipientEmail);

        subjectTitles = new Title(subjectColumn);
        String actualSubject = subjectTitles.getTextElements(subjectColumn).get(0);
        Assert.assertEquals(actualSubject, expectedSubject);
        return this;
    }

}

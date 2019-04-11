package pages;

import framework.PropertyReader;
import framework.browserFactory.utils.Waiter;
import framework.browserFactory.utils.WebDriverManager;
import framework.elements.Label;
import framework.pageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class MailPage extends BasePage {
    private static By mailLocator = By.xpath("//div[@class='mail-User-Name']");

    // Sent Folder
    private static By recipientEmails = By.xpath("//div[@class='mail-MessageSnippet-Content']/..//span[@class='mail-MessageSnippet-FromText']");
    private static By subjectColumn = By.xpath("//span[contains(@class,'mail') and contains(@class, 'Item_subject')]/span[@title]");
    private static Label recipientLabel;
    private static Label subjectLabel;

    // Inbox Folder
    private static By messageInboxFolder = By.xpath("//div[contains(@class,'mail-MessagesList')]");
    private static Label inboxFolderLabel = new Label(messageInboxFolder);
    private static By innerMessageLocator = By.xpath("//div[contains(@data-key,'view=messages-item-inner')]");
    private static By messageLine = By.xpath("//div[@class='mail-MessageSnippet-Content']");
    private static Label innerMessageLabel;

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

    public MailPage goToSentFolder() {
        sentFolder.click();
        Waiter.fluentWait(getDriver(), recipientEmails);
        recipientLabel = new Label(recipientEmails);
        subjectLabel = new Label(subjectColumn);
        return this;

    }

    public MessagePage goToFirstMessage() {
        inboxFolderLabel.getElements(messageLine).get(0).click();
        if (inboxFolderLabel.getElements(messageLine).get(0).isSelected()) {
            Waiter.fluentWait(getDriver(), innerMessageLocator);
            innerMessageLabel = new Label(innerMessageLocator);
            System.out.println(innerMessageLabel.getElements().size());
            innerMessageLabel.getElements().get(0).click();
        }
        return new MessagePage();
    }

    public MailPage assertSentMessage(String recipientEmail, String expectedSubject) {
        String actualEmail = recipientLabel.getTextElements(recipientEmails).get(1);
        Assert.assertEquals(actualEmail, recipientEmail);

        String actualSubject = subjectLabel.getTextElements(subjectColumn).get(1);
        Assert.assertEquals(actualSubject, expectedSubject);
        return this;
    }

    public LoginPage goToLoginPage() {
        WebDriverManager.openUrl(getDriver(), PropertyReader.getProperty("url"));
        return new LoginPage();
    }


}

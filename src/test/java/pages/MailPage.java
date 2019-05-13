package pages;

import framework.BasePage;
import framework.Log;
import framework.PropertyReader;
import framework.elements.Component;
import framework.elements.Label;
import framework.elements.Title;
import framework.utils.Waiter;
import framework.utils.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class MailPage extends BasePage {
    // Menu links of the folders
    private Label nestedList;
    // Inbox Folder
    private Component messageLine;
    // Sent Folder
    private Title recipientTitles, subjectTitles;

    public MailPage() {
        super(By.xpath("//div[@class='mail-User-Name']"), "Mail Page");
    }

    @Step("Go to Login Page")
    public LoginPage goToLoginPage() {
        logger.step("return to login page");
        WebDriverManager.openUrl(driver, PropertyReader.getProperty("url"));
        return new LoginPage();
    }

    @Step("Go to Sent Folder")
    public MailPage goToSentFolder() {
        logger.step("go to Sent folder");
        nestedList = new Label(By.xpath("//div[@data-key='view=folders']"));
        nestedList.getElements().get(1).click();
        return this;
    }

    @Step("Go to 1st message of the inbox folder")
    public MessagePage goToFirstMessage() {
        logger.step("open 1st message in inbox folder");
        Label inboxFolderLabel = new Label(By.xpath("//div[contains(@class,'mail-MessagesList')]"));
        messageLine = new Component(By.xpath("//div[@class='mail-MessageSnippet-Content']"));
        messageLine.getElements().get(0).click();
        return new MessagePage();
    }

    @Step("Check the recipient and the subject of message\n" +
            "recipient: {0}\n subject of the message: {1}")
    public MailPage verifyMessage(String recipientEmail, String expectedSubject) {
        logger.step("assert recipient of message and subject of message");
        recipientTitles = new Title(By.xpath("//span[@class='mail-MessageSnippet-FromText']"));
        String actualEmail = recipientTitles.getTextElements().get(0);
        Assert.assertEquals(actualEmail, recipientEmail);

        subjectTitles = new Title(By.xpath("//span[contains(@class,'mail') and contains(@class, 'Item_subject')]/span[@title]"));
        String actualSubject = subjectTitles.getTextElements().get(0);
        Assert.assertEquals(actualSubject, expectedSubject);
        return this;
    }

}

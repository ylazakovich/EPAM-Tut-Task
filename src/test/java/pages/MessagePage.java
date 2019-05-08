package pages;

import framework.BasePage;
import framework.Log;
import framework.elements.Button;
import framework.elements.TextBox;
import framework.elements.Title;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class MessagePage extends BasePage {
    private static Log logger = Log.getInstance();
    private static By messagePageLocator = By.xpath("//div[@class='mail-Message-Sender']");
    private By senderLocator = By.xpath("//div[@class='mail-Message-Sender']/span[contains(@class,'mail-Message-Sender-Name')]");
    private By messageLocator = By.xpath("//div[@class='mail-Message-Body-Content mail-Message-Body-Content_plain']");
    private By deleteToolLocator = By.xpath("//span[contains(@class,'toolbar-item-title-delete')]");
    private Title senderTitle;
    private TextBox messageBox;
    private Button deleteToolButton;

    public MessagePage() {
        super(messagePageLocator);
    }

    public static By getMessagePageLocator() {
        return messagePageLocator;
    }

    @Step("Check the sender email and the message\n" +
            "sender: {0}\n message: {1}\"")
    public MessagePage assertMessage(String expectedEmail, String expectedMessage) {
        logger.step("assert sender email and inbox message");
        senderTitle = new Title(messagePageLocator);
        String actualSenderEmail = senderTitle.getElement(senderLocator).getText();
        Assert.assertEquals(actualSenderEmail, expectedEmail);

        messageBox = new TextBox(messageLocator);
        String actualTextMessage = messageBox.getElementText(messageBox.getElement(messageLocator));
        Assert.assertEquals(actualTextMessage, expectedMessage);
        deleteToolButton = new Button(deleteToolLocator);
        deleteToolButton.moveToElementAndClick();
        return this;
    }
}

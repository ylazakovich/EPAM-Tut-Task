package pages;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.TextBox;
import framework.elements.Title;
import org.openqa.selenium.By;
import org.testng.Assert;

public class MessagePage extends BasePage {
    private static By messagePageLocator = By.xpath("//div[@class='mail-Message-Sender']");

    private By senderLocator = By.xpath("//div[@class='mail-Message-Sender']/span[contains(@class,'mail-Message-Sender-Name')]");
    private Title senderTitle;
    private By messageLocator = By.xpath("//div[@class='mail-Message-Body-Content mail-Message-Body-Content_plain']");
    private TextBox messageBox;
    private By deleteToolLocator = By.xpath("//span[contains(@class,'toolbar-item-title-delete')]");
    private Button deleteToolButton;


    public MessagePage() {
        super(messagePageLocator);
    }

    public static By getMessagePageLocator() {
        return messagePageLocator;
    }

    public MessagePage assertMessage(String expectedEmail, String expectedMessage) {
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

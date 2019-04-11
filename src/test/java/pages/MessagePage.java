package pages;

import framework.elements.TextBox;
import framework.elements.Title;
import framework.pageObject.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class MessagePage extends BasePage {
    private static By messagePageLocator = By.xpath("//div[@class='mail-Message-Sender']");

    private Title senderHeader = new Title(messagePageLocator);
    private By messageLocator = By.xpath("//div[@class='mail-Message-Body-Content mail-Message-Body-Content_plain']");
    private TextBox messageBox = new TextBox(messageLocator);


    public MessagePage() {
        super(messagePageLocator);
    }

    public static By getMessagePageLocator() {
        return messagePageLocator;
    }

    public MessagePage assertMessage(String expectedEmail, String expectedMessage) {
        String actualSenderEmail = senderHeader.getElementText(senderHeader.getElement(messagePageLocator));
        Assert.assertEquals(actualSenderEmail, expectedEmail);

        String actualTextMessage = messageBox.getElementText(messageBox.getElement(messageLocator));
        Assert.assertEquals(actualTextMessage, expectedMessage);
        return this;
    }
}

package pages;

import framework.elements.TextBox;
import framework.elements.Title;
import framework.pageObject.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class MessagePage extends BasePage {
    private static By senderLocator = By.xpath("//div[@class='mail-Message-Sender']");
    private static Title senderHeader = new Title(senderLocator);
    private static By messageLocator = By.xpath("//div[@class='mail-Message-Body-Content mail-Message-Body-Content_plain']");
    private static TextBox messageBox = new TextBox(messageLocator);


    public MessagePage() {
        super(senderLocator);
    }

    public static By getSenderLocator() {
        return senderLocator;
    }

    public MessagePage assertMessage(String expectedEmail, String expectedMessage) {
        String actualSenderEmail = senderHeader.getElementText(senderHeader.getElement(senderLocator));
        Assert.assertEquals(actualSenderEmail, expectedEmail);

        String actualTextMessage = messageBox.getElementText(messageBox.getElement(messageLocator));
        Assert.assertEquals(actualTextMessage, expectedMessage);
        return this;
    }
}

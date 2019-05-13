package pages;

import framework.BasePage;
import framework.Log;
import framework.elements.Component;
import framework.elements.TextBox;
import framework.elements.Title;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class MessagePage extends BasePage {
    private Title senderTitle;
    private TextBox messageBox;
    private Component deleteToolComponent;

    public MessagePage() {
        super(By.xpath("//div[@class='mail-Message-Body-Content mail-Message-Body-Content_plain']"), "Message Page");
    }

    @Step("Check the sender email and the message\n" +
            "sender: {0}\n message: {1}\"")
    public MessagePage assertMessage(String expectedEmail, String expectedMessage) {
        logger.step("assert sender email and inbox message");
        senderTitle = new Title(By.xpath("//div[@class='mail-Message-Sender']/span[contains(@class,'mail-Message-Sender-Name')]"));
        String actualSenderEmail = senderTitle.getElementText();
        Assert.assertEquals(actualSenderEmail, expectedEmail);

        messageBox = new TextBox(By.xpath("//div[@class='mail-Message-Body-Content mail-Message-Body-Content_plain']"));
        String actualTextMessage = messageBox.getElementText();
        Assert.assertEquals(actualTextMessage, expectedMessage);
        deleteToolComponent = new Component(By.xpath("//span[contains(@class,'toolbar-item-title-delete')]"));
        deleteToolComponent.moveToElementAndClick();
        return this;
    }
}

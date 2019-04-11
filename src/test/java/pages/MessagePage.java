package pages;

import framework.pageObject.BasePage;
import org.openqa.selenium.By;

public class MessagePage extends BasePage {
    private static By MessagegLocator = By.xpath("//div[@class='mail-Message-Sender']");

    public MessagePage() {
        super(MessagegLocator);
    }

    public static By getMessagegLocator() {
        return MessagegLocator;
    }
}

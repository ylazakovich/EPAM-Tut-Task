package pages.folders;

import framework.PropertyReader;
import framework.browserFactory.utils.Waiter;
import framework.browserFactory.utils.WebDriverManager;
import framework.elements.Label;
import framework.pageObject.BasePage;
import org.openqa.selenium.By;
import pages.LoginPage;
import pages.MessagePage;

public class InboxFolder extends BasePage {
    private static By inboxFolderLocator = By.xpath("локатор");

    private By messageInboxFolder = By.xpath("//div[contains(@class,'mail-MessagesList')]");
    private By messageLine = By.xpath("//div[@class='mail-MessageSnippet-Content']");
    private Label inboxFolderLabel;


    public InboxFolder() {
        super(inboxFolderLocator);
    }

    public static By getInboxFolderLocator() {
        return inboxFolderLocator;
    }

    public MessagePage goToFirstMessage() {
        Label inboxFolderLabel = new Label(messageInboxFolder);
        inboxFolderLabel.getElements(messageLine).get(0).click();
        Waiter.fluentWait(getDriver(), MessagePage.getMessagePageLocator());
        return new MessagePage();
    }

    public LoginPage goToLoginPage() {
        WebDriverManager.openUrl(getDriver(), PropertyReader.getProperty("url"));
        Waiter.fluentWait(getDriver(), LoginPage.getLoginPageLocator());
        return new LoginPage();
    }
}

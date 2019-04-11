package pages;

import framework.browserFactory.utils.Waiter;
import framework.elements.Label;
import framework.pageObject.BasePage;
import org.openqa.selenium.By;
import pages.folders.SentFolder;

public class MailPage extends BasePage {
    private static By mailPageLocator = By.xpath("//div[@class='mail-User-Name']");

    private By nestedLocator = By.xpath("//div[@data-key='view=folders']");
    private By nestedItem = By.xpath("//span[@class='mail-NestedList-Item-Name']");
    private Label nestedList;

    public MailPage() {
        super(mailPageLocator);
    }

    public static By getMailPageLocator() {
        return mailPageLocator;
    }

    public SentFolder goToSentFolder() {
        nestedList = new Label(nestedLocator);
        nestedList.getElements(nestedItem).get(1).click();
        Waiter.fluentWait(getDriver(), SentFolder.getSentFolderLocator());
        return new SentFolder();
    }

}

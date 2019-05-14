package tests;

import allure.TestListener;
import framework.Log;
import framework.mail.JavaMail;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Link;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.IndexPage;

@Listeners({TestListener.class})
@Epic("Regression Tests")

public class MainTest extends BaseTest {
    protected static Log logger = Log.getInstance();
    private IndexPage page;

    @Link("https://www.tut.by/")
    @Link(name = "allure", type = "mylink")

    @Test
    @Description("1. Sent email (JavaMail)\n" + "2. Checks recipient of message\n" + "3. Checks message")
    public void runTest() {
        logger.info(logger.getLogLoc("loc.test.start"));
        page = new IndexPage();

        JavaMail.send(sender, recipient.getEmail(), subject, message);
        page.authorization(sender)
                .goToEmail().goToSentFolder().verifyMessage(recipient.getEmail(), subject)
                .goToLoginPage().logOut();

        page.authorization(recipient).
                goToEmail().goToFirstMessage().assertMessage(sender.getEmail(), message);

        logger.info(logger.getLogLoc("loc.test.end"));
    }
}

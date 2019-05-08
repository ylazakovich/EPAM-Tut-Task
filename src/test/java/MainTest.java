import allure.TestListener;
import framework.BaseTest;
import framework.Log;
import framework.dataFactory.User;
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
    private User sender = getUsers().get(0);
    private User recipient = getUsers().get(1);
    private String subject = getMessage();
    private String message = getSubject();
    private IndexPage page = new IndexPage();

    @Link("https://www.tut.by/")
    @Link(name = "allure", type = "mylink")

    @Test
    @Description("1. Sent email (JavaMail)\n" + "2. Checks recipient of message\n" + "3. Checks message")
    public void runTest() {
        logger.info(getLoc("loc.test.start"));
        JavaMail.send(sender, recipient.getEmail(), subject, message);

        page.authorization(sender)
                .goToEmail().goToSentFolder().verifyMessage(recipient.getEmail(), subject)
                .goToLoginPage().logOut();

        page.authorization(recipient).
                goToEmail().goToFirstMessage().assertMessage(sender.getEmail(), message);
        logger.info(getLoc("loc.test.end"));
    }
}

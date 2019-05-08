import allure.TestListener;
import framework.BaseTest;
import framework.DataFactory;
import framework.dataFactory.User;
import framework.mail.JavaMail;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Link;
import org.testng.annotations.Listeners;
import pages.IndexPage;

import java.util.List;

@Listeners({TestListener.class})
@Epic("Regression Tests")

public class MainTest extends BaseTest {

    @Override
    @Link("https://www.tut.by/")
    @Link(name = "allure", type = "mylink")
    @Description("Send e-mail from acc1 to acc2 (use Java Mail API) with text, sent it to acc2 via to/cc/bcc (all 3 options)\n" +
            "Log in to acc1 from UI. Check e-mail in Sent present\n" +
            "Log in to acc2 from UI. Check e-mail in Inbox and check text has valid message\n")
    public void run() {
        List<User> users = DataFactory.getUserList();

        User sender = users.get(0);
        User recipient = users.get(1);

        String subject = "This is subject";
        String message = "SSL: This is text";

        IndexPage page = new IndexPage();

        JavaMail.send(sender, recipient.getEmail(), subject, message);

        page.authorization(sender)
                .goToEmail().goToSentFolder().verifyMessage(recipient.getEmail(), subject)
                .goToLoginPage().logOut();

        page.authorization(recipient).
                goToEmail().goToFirstMessage().assertMessage(sender.getEmail(), message);

    }
}

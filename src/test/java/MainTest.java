import allure.TestListener;
import framework.BaseTest;
import framework.DataFactory;
import framework.dataFactory.User;
import framework.mail.JavaMail;
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

import framework.BaseTest;
import framework.dataFactory.DataFactory;
import framework.dataFactory.User;
import framework.mail.JavaMail;
import pages.IndexPage;

import java.util.List;

public class MainTest extends BaseTest {

    @Override
    public void run() {
        List<User> users = DataFactory.getUserList();

        User sender = users.get(0);
        User recipient = users.get(1);

        String subject = "This is subject";
        String message = "SSL: This is text";

        IndexPage page = new IndexPage();

        JavaMail.send(sender, recipient.getEmail(), subject, message);

        page.authorization(sender)
                .goToEmail().goToSentFolder().assertSentMessage(recipient.getEmail(), subject)
                .goToLoginPage().logOut();

        page.authorization(recipient).
                goToEmail().goToFirstMessage().assertMessage(sender.getEmail(), message);

    }
}

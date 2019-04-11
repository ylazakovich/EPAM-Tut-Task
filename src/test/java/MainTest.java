import framework.BaseEntity;
import framework.dataFactory.DataFactory;
import framework.dataFactory.User;
import framework.mail.JavaMail;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.IndexPage;

import java.util.List;


public class MainTest extends BaseEntity {

    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{{
                DataFactory.getUserList()
        }
        };
    }


    @Test(dataProvider = "getUsers")
    public void run(List<User> users) {
        User sender = users.get(0);
        User recipient = users.get(1);

        String subject = "This is subject";
        String message = "SSL: This is text[2]";

        IndexPage page = new IndexPage();

        JavaMail.send(sender, recipient.getEmail(), subject, message);

        page.authorization(sender).
                goToEmail().goToSentFolder().assertSentMessage(recipient.getEmail(), subject)
                .goToLoginPage().logOut();

        page.authorization(recipient).goToEmail().goToFirstMessage();

    }

}

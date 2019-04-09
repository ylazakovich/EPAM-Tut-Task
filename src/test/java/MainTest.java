import framework.BaseEntity;
import framework.dataFactory.DataFactory;
import framework.dataFactory.User;
import framework.mail.JavaMail;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MailPage;
import pages.MainPage;

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
        String message = "SSL: This is text body";

        MainPage mainPage;
        LoginPage loginPage;
        MailPage mailPage;

        JavaMail.send(sender, recipient.getEmail(), subject, message);

        mainPage = new MainPage();
        loginPage = mainPage.authorization(sender);
        mailPage = loginPage.goToEmail();
//        MyPage.MainPage().authorization(sender);
//        MyPage.LoginPage().goToEmail();
//        MyPage.MailPage().goToSentFolder();


    }

}

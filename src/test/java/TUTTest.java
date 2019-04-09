import framework.BaseEntity;
import framework.dataFactory.DataFactory;
import framework.dataFactory.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;

import java.util.List;


public class TUTTest extends BaseEntity {

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

        MainPage mainPage = new MainPage();
        mainPage.authorization(sender);
        LoginPage loginPage = new LoginPage();
        loginPage.logOut();

    }

}

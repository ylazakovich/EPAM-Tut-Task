import framework.BaseEntity;
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
//                DataStash.getUserList()
        }
        };
    }


    @Test(dataProvider = "getUsers")
    public void run(List<User> userList) {
        MainPage mainPage = new MainPage();
        mainPage.authorization(userList.get(0));
        LoginPage loginPage = new LoginPage();
        loginPage.logOut();


    }

}

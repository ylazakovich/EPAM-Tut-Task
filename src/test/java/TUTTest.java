import framework.BaseEntity;
import framework.factory.users.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;

public class TUTTest extends BaseEntity {

    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{
                {
                        new User(),
                        new User()
                }
        };
    }


    @Test(dataProvider = "getUsers")
    public void run(User acc1, User acc2) {
        MainPage mainPage = new MainPage();
        mainPage.authorization(acc1.getUserName(), acc1.getPassword());
        LoginPage loginPage = new LoginPage();
        loginPage.logOut();


    }

}

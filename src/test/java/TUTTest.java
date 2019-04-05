import framework.BaseEntity;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;


public class TUTTest extends BaseEntity {

    @DataProvider(name = "getUser")
    public Object[][] getUser() {
        return new Object[][]{
                {
                        new User("rotf10corp@tut.by", "qq3858122")
                }
        };
    }


    @Test(dataProvider = "getUser")
    public void logInTest(User user)

    {
        MainPage mainPage = new MainPage();
        mainPage.authorization(user.getUserName(), user.getPassword());
        LoginPage loginPage = new LoginPage();
        loginPage.logOut();
    }

}

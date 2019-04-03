import framework.BaseEntity;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TUTTest extends BaseEntity {

    @DataProvider(name = "account")
    public static Object[][] account() {
        return new Object[][]{
                {"rotf10corp@tut.by", "qq3858122"}
        };
    }

    @Test(dataProvider = "account")
    public void run(String user, String pass) {
        MainPage mainPage = new MainPage();
        mainPage.authorization(user, pass);
    }
}

import framework.BaseEntity;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TUTTest extends BaseEntity {

    @Test(testName = "logIn Test")
    @Parameters({"username", "password"})
    public void logInTest(String username, String password)

    {
        MainPage mainPage = new MainPage();
        mainPage.authorization(username, password);
    }

}

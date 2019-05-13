package pages;

import framework.BasePage;
import framework.User;
import framework.elements.Component;
import framework.elements.Input;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class IndexPage extends BasePage {
    private Component loginForm, submit;
    private Input userName, password;


    public IndexPage() {
        super(By.xpath("//a[@class='enter']"), "Index Page");
    }

    @Step("Login step with user: {0}")
    public LoginPage authorization(User user) {
        logger.step("Log in: " + user.getEmail());
        escapeFromADB();
        loginForm = new Component(By.xpath("//a[contains(@class, 'enter')]"));
        loginForm.moveToElementAndClick();
        userName = new Input(By.xpath("//div[@class='i-holder']/input[@name='login']"));
        password = new Input(By.xpath("//div[@class='i-holder']/input[@name='password']"));
        userName.sendKeys(user.getEmail());
        password.sendKeys(user.getPassword());
        submit = new Component(By.xpath("//div[@class='b-hold']/input[@type='submit']"));
        submit.moveToElementAndClick();
        return new LoginPage();
    }
}

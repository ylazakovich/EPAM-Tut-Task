package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Component extends BaseElement {
    private static WebElement element;

    public Component(By by) {
        super(by);
    }

    public void click() {
        moveToElementAndClick();
    }

}

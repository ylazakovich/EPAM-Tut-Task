package framework.elements;

import org.openqa.selenium.By;

public class Input extends BaseElement {
    public Input(By by) {
        super(by);
    }

    public void sendKeys(String string) {
        getElement().clear();
        getElement().sendKeys(string);
    }
}

package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Button extends BaseElement {
    private static WebElement element;

    public Button(By by) {
        super(by);
    }

    public void click() {
        moveToElementAndClick();
    }

    @Override
    protected String getElementType() {
        return getLoc("loc.button");
    }
}

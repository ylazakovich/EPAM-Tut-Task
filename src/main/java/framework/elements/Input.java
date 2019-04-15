package framework.elements;

import org.openqa.selenium.By;

public class Input extends BaseElement {
    public Input(By by) {
        super(by);
    }

    public void sendKeys(By by, String string) {
        getElement(by).clear();
        getElement(by).sendKeys(string);
    }

    @Override
    protected String getElementType() {
        return getLoc("loc.input");
    }
}

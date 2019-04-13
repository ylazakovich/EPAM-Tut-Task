package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Label extends BaseElement {
    private List<String> labelList;

    public Label(By by) {
        super(by);
    }

    public List<String> getTextElements(By by) {
        labelList = new ArrayList<>();
        for (WebElement webElement :
                super.getElements(by)) {
            labelList.add(getElementText(webElement));
        }
        return labelList;
    }

    @Override
    protected String getElementType() {
        return getLoc("loc.label");
    }
}

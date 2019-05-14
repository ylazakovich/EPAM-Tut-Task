package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Title extends BaseElement {
    private List<String> titleList;

    public Title(By by) {
        super(by);
    }

    public List<String> getTextElements(By by) {
        titleList = new ArrayList<>();
        for (WebElement webElement :
                super.getElements()) {
            titleList.add(getElementText());
        }
        return titleList;
    }
}

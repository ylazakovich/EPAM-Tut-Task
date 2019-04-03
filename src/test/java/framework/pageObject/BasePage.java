package framework.pageObject;

import framework.elements.Button;
import org.openqa.selenium.By;


public abstract class BasePage {
    private By locator;
    private Button button;


    public BasePage(By locator) {
        init(locator);
        isOpen(locator);
    }

    public boolean isOpen(By titleLocator) {
        this.button = new Button(titleLocator);
        return button.isEnable();
    }

    public void init(By pageLocator) {
        this.locator = pageLocator;
    }
}

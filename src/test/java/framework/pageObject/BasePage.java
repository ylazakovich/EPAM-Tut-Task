package framework.pageObject;

import org.openqa.selenium.By;


public class BasePage {
    private By locator;
//    private Button button;


    private BasePage(By locator) {
        init(locator);
        isOpen(locator);
    }

    private boolean isOpen(By titleLocator) {
//        this.button = new Button(titleLocator);
//        return button.isEnable();
        return false;
    }

    private void init(By pageLocator) {
        this.locator = pageLocator;
    }
}

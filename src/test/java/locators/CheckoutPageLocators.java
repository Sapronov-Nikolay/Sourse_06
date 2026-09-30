package locators;

import org.openqa.selenium.By;

/**
 * Локаторы страниц оформления заказа.
 */
public final class CheckoutPageLocators {
    
    private CheckoutPageLocators() {}
    
    public static final By FIRST_NAME = By.id("first-name");
    public static final By LAST_NAME = By.id("last-name");
    public static final By POSTAL_CODE = By.id("postal-code");
    public static final By CONTINUE_BUTTON = By.id("continue");
    public static final By FINISH_BUTTON = By.id("finish");
    public static final By COMPLETE_HEADER = By.className("complete-header");
    public static final By TITLE = By.cssSelector("[data-test='title']");
}

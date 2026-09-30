package locators;

import org.openqa.selenium.By;

/**
 * Локаторы страницы корзины.
 */
public final class CartPageLocators {
    
    private CartPageLocators() {}
    
    public static final By CART_LINK = By.cssSelector("[data-test='shopping-cart-link']");
    public static final By CART_BADGE = By.cssSelector("[data-test='shopping-cart-badge']");
    public static final By CHECKOUT_BUTTON = By.id("checkout");
    public static final By CART_LIST = By.className("cart_list");
    public static final By CONTINUE_SHOPPING = By.id("continue-shopping");
    public static final By PRODUCT_NAMES = By.className("inventory_item_name");
    public static final By TITLE = By.cssSelector("[data-test='title']");
}

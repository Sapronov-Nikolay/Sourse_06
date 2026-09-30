package locators;

import org.openqa.selenium.By;

public final class ProductsPageLocators {
    
    private ProductsPageLocators() {}
    
    public static final By CART_ICON = By.id("shopping_cart_container");
    public static final By PRODUCTS_TITLE = By.cssSelector("[data-test='title']");
    
    public static By addToCartButton(String slug) {
        return By.cssSelector("[data-test='add-to-cart-" + slug + "']");
    }
    
    public static By removeButton(String slug) {
        return By.cssSelector("[data-test='remove-" + slug + "']");
    }
}

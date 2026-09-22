package locators;

import org.openqa.selenium.By;

public final class ProductsPageLocators {
    
    private ProductsPageLocators() {}

    // Ярлычок корзины в углу окна
    public static final By CART_ICON = By.id("shopping_cart_container");
    // Заголовок "Products" - признак успешного входа
    public static final By PRODUCTS_TITLE = By.cssSelector("[data-test='title']");

    // Добавить товар в корзину по его "слагу" (короткому имени, например "sauce-labs-backpack")
    public static By addToCartButton(String slug) {
        return By.cssSelector("[data-test='add-to-cart-" + slug + "']");
    }

    // Удалить товар из корзины (кнопка "Remove")
    public static By removeButton(String slug) {
        return By.cssSelector("[data-test='remove-" + slug + "']");
    }
}

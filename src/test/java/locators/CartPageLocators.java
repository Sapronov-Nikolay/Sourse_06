package locators;

import org.openqa.selenium.By;

/**
 * Локаторы страницы корзины.
 */
public final class CartPageLocators {
    
    private CartPageLocators() {}
    
    // Ссылка на корзину (иконка)
    public static final By CART_LINK = By.cssSelector("[data-test='shopping-cart-link']");
    // Счётчик товаров (красная плашка). Если товаров нет - элемента нет.
    public static final By CART_BADGE = By.cssSelector("[data-test='shopping-cart-badge']");
    // Кнопка оформления заказа.
    public static final By CHECKOUT_BUTTON = By.id("checkout");
    // Список товаров в корзине (контейнер)
    public static final By CART_LIST = By.className("cart_list");
    // Кнопка для перехода к выбору товаров обратно
    public static final By CONTINUE_SHOPPING = By.id("continue-shopping");
    // Наименование товара в списке товаров
    public static final By PRODUCT_NAMES = By.className("inventory_item_name");
    // Заголовок страницы продуктов
    public static final By TITLE = By.cssSelector("[data-test='title']");
}

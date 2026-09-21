package pages;

import io.qameta.allure.Step;
import locators.CartPageLocators;
import locators.ProductsPageLocators;
import org.openqa.selenium.WebDriver;

/**
 * Page Object страницы товаров.
 */
public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("1. Проверить, что открыта страница товаров")
    public boolean isPageOpened() { return isDisplayed(ProductsPageLocators.PRODUCTS_TITLE); }

    @Step("2. Получить заголовок страницы")
    public String getPageTitle() { return getText(ProductsPageLocators.PRODUCTS_TITLE); }
    
    @Step("3. Добавить товар '{slug}' в корзину")
    public void addToCart(String slug) { click(ProductsPageLocators.addToCartButton(slug)); }
    
    @Step("4. Удалить товар '{slug}' из корзины")
    public void removeFromCart(String slug) { click(ProductsPageLocators.removeButton(slug)); }
    
    @Step("5. Получить количество товаров в корзине")
    public int getCartBadgeCount() {
        if (!isDisplayed(CartPageLocators.CART_BADGE)) {
            return 0;
        }
        return Integer.parseInt(getText(CartPageLocators.CART_BADGE));
    }
    
    @Step("6. перейти в корзину")
    public void switchToCart() { click(ProductsPageLocators.CART_ICON); }
}

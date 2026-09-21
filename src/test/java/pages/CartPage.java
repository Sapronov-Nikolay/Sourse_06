package pages;

import io.qameta.allure.Step;
import locators.CartPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import java.util.ArrayList;
import java.util.List;

/**
 * Page Object страницы корзины.
 */
public class CartPage extends BasePage {
    
    public CartPage(WebDriver driver) {
        super(driver);
    }
    
    @Step("1. Проверить, что открыта корзина")
    public boolean isPageOpened() {
        return isDisplayed(CartPageLocators.TITLE);
    }
    
    @Step("2. Получить список названий товаров в корзине")
    public List<String> getProductsNames() {
        wait.until(ExpectedCondition.visibilityOfElementLocated(CartPageLocators.CART_LIST));
        List<WebElement> products = driver.findElements(CartPageLocators.PRODUCT_NAMES);
        List<String> names = new ArrayList<>();
        for (WebElement product : products) {
            names.add(product.getText());
        }
        return names;
    }
    
    @Step("3. Перейти к оформлению заказа")
    public void clickCheckout() {
        click(CartPageLocators.CHECKOUT_BUTTON);
    }
}

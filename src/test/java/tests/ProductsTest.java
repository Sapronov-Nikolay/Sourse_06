package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductsPage;

/** Проверки страницы товаров */
@Epic("Swag Labs")
@Feature("Страница товаров")
public class ProductsTest extends BaseTest {
    
    @Test(description = "Добавление товара в корзину увеличивает счётчик", priority = 1)
    @Story("Добавление товара в корзину")
    @Severity(SeverityLevel.BLOCKER)
    public void addProductToCartTest() {
        loginAsStandardUser();
        
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addToCart("sauce-labs-backpack");
        
        Assert.assertEquals(productsPage.getCartBadgeCount(), 1, "В корзине должен быть 1 товар");
    }
    
    @Test(description = "Удаление товара из корзины уменьшает счётчик", priority = 2)
    @Story("Удаление товара из корзины")
    @Severity(SeverityLevel.NORMAL)
    public void removeProductFromCartTest() {
        loginAsStandardUser();
        
        ProductsPage productsPage = new ProductsPage(driver);
        // Сначала добавлением - чтобы кнопка "Add to cart" сменилась на "Remove"
        productsPage.addToCart("sauce-labs-backpack");
        Assert.assertEquals(productsPage.getCartBadgeCount(), 1, "Перед удалением в корзине должен быть 1 товар");
        // Теперь удаляем товар
        productsPage.removeFromCart("sauce-labs-backpack");
        Assert.assertEquals(productsPage.getCartBadgeCount(), 0, "После удаления корзина должна быть пустой");
    }
}

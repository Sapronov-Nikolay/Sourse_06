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
    
    @Test(description = "Добавление товара вкорзину увеличивает счётчик")
    @Story("Добавление товара в корзину")
    @Severity(SeverityLevel.BLOCKER)
    public void addProductToCartTest() {
        loginAsStandardUser();
        
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addToCart("sauce-labs-backpack");
        
        Assert.assertEquals(productsPage.getCartBadgeCount(), 1, "В корзине должен быть 1 товар");
    }
}

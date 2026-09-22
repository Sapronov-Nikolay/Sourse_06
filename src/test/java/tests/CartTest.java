package tests;

import enums.TitleNaming;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ProductsPage;

import java.util.List;

@Epic("Swag Lags")
@Feature("Cart & Checkout")
public class CartTest extends BaseTest {
    
    private static final String FIRST_NAME = "Николай";
    private static final String LAST_NAME = "Сапронов";
    private static final String POSTAL_CODE = "111672";
    
    @Test(description = "Проверка содержимого корзины", priority = 1)
    @Story("Содержимое корзины")
    @Severity(SeverityLevel.CRITICAL)
    public void checkGoodsInCartTest() {
        loginAsStandardUser();  // Логинимся стандартным пользователем из BaseTest
        
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addToCart("sauce-labs-backpack");
        productsPage.addToCart("test.allthethings()-t-shirt-(red)");
        productsPage.addToCart("sauce-labs-bolt-t-shirt");
        productsPage.switchToCart();
        
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isPageOpened(), "Корзина не открылась");
        
        List<String> goods = cartPage.getProductsNames();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(goods.isEmpty(), "Корзина пустая");
        softAssert.assertEquals(goods.size(), 3, "Должно быть 3 товара");
        softAssert.assertTrue(goods.contains("Sauce Labs Backpack"));
        softAssert.assertTrue(goods.contains("Test.allTheThings() T-Shirt (Red)"));
        softAssert.assertTrue(goods.contains("Sauce Labs Bolt T-Shirt"));
        softAssert.assertAll();
    }
    
    @Test(description = "Полный E2E-цикл оформления заказа", priority = 2)
    @Story("Оформление заказа")
    @Severity(SeverityLevel.BLOCKER)
    public void checkoutFillFlowTest() {
        loginAsStandardUser();  // Логинимся стандартным пользователем из BaseTest
        
        // Добавляем один товар и переходим в корзину.
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addToCart("sauce-labs-backpack");
        productsPage.switchToCart();
        
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();   // Нажимаем на кнопку Checkout на странице корзины
        
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assert.assertEquals(checkoutPage.getPageTitle(), TitleNaming.CHECKOUT_INFO.getTitle());
        
        checkoutPage.fillCheckoutInfo(FIRST_NAME, LAST_NAME, POSTAL_CODE);
        checkoutPage.clickContinue();
        Assert.assertEquals(checkoutPage.getPageTitle(), TitleNaming.CHECKOUT_OVERVIEW.getTitle());
        
        checkoutPage.clickFinish();
        Assert.assertEquals(checkoutPage.getPageTitle(), TitleNaming.CHECKOUT_COMPLETE.getTitle());
        Assert.assertEquals(checkoutPage.getCompleteHeaderText(), "Thank you for your order!");
    }
    
}

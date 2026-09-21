
package tests;

import enums.TitleNaming;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import user.UserFactory;

/** Этот класс содержит ПОЗИТИВНЫЕ тесты на логин и пароль.
 * Проверяем, что все 5 верных пользователя действительно могут зайти на страницу Products и обрести там своё счастье.
 */
@Epic("Swag Labs")
@Feature("ВВод логина и пароля")
public class LoginPositiveTests extends BaseTest {
    
    @Test(description = "Успешный вход пользователя standard_user")
    @Story("Вход валидного standard_user")
    @Severity(SeverityLevel.CRITICAL)
    public void standardUserLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            UserFactory.standardUser().getUsername(),
            UserFactory.standardUser().getPassword()
        );
        ProductsPage productsPage = new ProductsPage(dtiver);
        Assert.assertTrue(productsPage.isPageOpened(), "Страница Products не открылась");
        Assert.assertEquals(productsPage.getPageTitle(), TitleNaming.PRODUCTS.getTitle());
    }
    
    @Test(description = "Успешный вход пользователя problem_user")
    @Story("Вход валидного problem_user")
    @Severity(SeverityLevel.CRITICAL)
    public void problemUserLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            UserFactory.problemUser().getUsername(),
            UserFactory.problemUser().getPassword()
        );
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isPageOpened(), "Страница Products не открылась");
    }
    
    @Test(description = "Успешный вход пользователя performance_glitch_user")
    @Story("Вход валидного performance_glitch_user")
    @Severity(SeverityLevel.CRITICAL)
    public void performanceGlitchUserLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            UserFactory.performanceGlitchUser().getUsername(),
            UserFactory.emptyUsernameValidPassword().getPassword()
        );
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isPageOpened(), "Страница Products не открылась");
    }
    
    @Test(description = "Успешный вход пользователя error_user")
    @Story("Вход валидного error_user")
    @Severity(SeverityLevel.CRITICAL)
    public void errorUserLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            UserFactory.errorUser().getUsername(),
            UserFactory.errorUser().getPassword()
        );
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isPageOpened(), "Страница Products не открылась");
    }
    
    @Test(description = "Успешный вход пользователя visual_user")
    @Story("Вход валидного visual_user")
    @Severity(SeverityLevel.CRITICAL)
    public void visualUserLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            UserFactory.invalidUser().getUsername(),
            UserFactory.invalidUser().getPassword()
        );
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isPageOpened(), "Страница Products не открылась");
    }
}

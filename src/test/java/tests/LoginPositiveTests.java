
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
        loginAs(UserFactory.standardUser());
        
        Assert.assertTrue(productsPage.isPageOpened(), "Страница Products не открылась");
        Assert.assertEquals(productsPage.getPageTitle(), TitleNaming.PRODUCTS.getTitle());
    }
    
    @Test(description = "Успешный вход пользователя problem_user")
    @Story("Вход валидного problem_user")
    @Severity(SeverityLevel.CRITICAL)
    public void problemUserLoginTest() {
        loginAs(UserFactory.problemUser());
        
        Assert.assertTrue(productsPage.isPageOpened(), "Страница Products не открылась");
    }
    
    @Test(description = "Успешный вход пользователя performance_glitch_user")
    @Story("Вход валидного performance_glitch_user")
    @Severity(SeverityLevel.CRITICAL)
    public void performanceGlitchUserLoginTest() {
        loginAs(UserFactory.performanceGlitchUser());
        
        Assert.assertTrue(productsPage.isPageOpened(), "Страница Products не открылась");
    }
    
    @Test(description = "Успешный вход пользователя error_user")
    @Story("Вход валидного error_user")
    @Severity(SeverityLevel.CRITICAL)
    public void errorUserLoginTest() {
        loginAs(UserFactory.errorUser());
        
        Assert.assertTrue(productsPage.isPageOpened(), "Страница Products не открылась");
    }
    
    @Test(description = "Успешный вход пользователя visual_user")
    @Story("Вход валидного visual_user")
    @Severity(SeverityLevel.CRITICAL)
    public void visualUserLoginTest() {
        loginAs(UserFactory.visualUser());
        
        Assert.assertTrue(productsPage.isPageOpened(), "Страница Products не открылась");
    }
}

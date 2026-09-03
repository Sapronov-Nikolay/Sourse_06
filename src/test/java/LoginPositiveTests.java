import org.testng.Assert;
import org.testng.annotations.Test;

/** Этот класс содержит ПОЗИТИВНЫЕ тесты на логин. Проверяем что пользователи действительно могут зайти. */
public class LoginPositiveTests extends BaseTest {

    private LoginPage loginPage;

    // -------------------------------------------------------------------------
    // ТЕСТ 4: Успешный вход: верный логин → верный пароль
    // -------------------------------------------------------------------------
    @Test
    public void standardUserLoginTest() {
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(loginPage.isProductsPageDisplayed(), "Не удалось залогиниться!");
    }

    // -------------------------------------------------------------------------
    // ТЕСТ 5: Успешный вход в проблемную страницу
    // -------------------------------------------------------------------------
    @Test
    public void problemUserLoginTest() {
        loginPage = new LoginPage(driver);
        loginPage.login("problem_user", "secret_sauce");
        Assert.assertTrue(loginPage.isProductsPageDisplayed(), "Не удалось залогиниться!");
    }
}

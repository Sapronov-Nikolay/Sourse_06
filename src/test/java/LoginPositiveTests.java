import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Этот класс содержит ПОЗИТИВНЫЕ тесты на логин.
 * Проверяем что пользователи действительно могут зайти.
 */
public class LoginPositiveTests extends BaseTest {

    /** Переменная, которая будет хранить экземпляр класса страницы логина */
    private LoginPage loginPage;

    // -------------------------------------------------------------------------
    // ТЕСТ 4: Успешный вход: верный логин → верный пароль
    // -------------------------------------------------------------------------
    @Test
    public void standardUserLoginTest() {
        // Создаём объект страницы, передавая в него драйвер из базового файла BaseTest
        loginPage = new LoginPage(driver);

        // Шаг 1. Вводим данные стандартного пользователя
        loginPage.login("standard_user", "secret_sauce");

        // Шаг 2. проверяем, что после логина мы попали на страницу с товарами
        Assert.assertTrue(loginPage.isProductsPageDisplayed(), "Не удалось залогиниться!");
    }

    // -------------------------------------------------------------------------
    // ТЕСТ 5: Успешный вход в проблемную страницу
    // -------------------------------------------------------------------------
    @Test
    public void problemUserLoginTest() {
        loginPage = new LoginPage(driver);

        // Шаг 1. Вводим данные problem_user и верный пароль secret_sauce
        loginPage.login("problem_user", "secret_sauce");

        // Шаг 2. Проверяем, что вход выполнен, даже если контент отображается с проблемами
        Assert.assertTrue(loginPage.isProductsPageDisplayed(), "Не удалось залогиниться!");
    }
}

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Этот класс содержит НЕГАТИВНЫЕ проверки на форму логина.
 * Он наследуется от BaseTest, поэтому у него автоматически есть драйвер,
 * и он сам запускает браузер (setUp) и закрывает его (tearDown).
 * По сути (setUp) и (tearDown) отрабатывают как дежурные.
 * Тесты это работники - они приходят на работу, а в здании уже всё включено (setUp)
 * Они отработав, уходят с работы и (tearDown) выключает за ними свет, закрывает все двери на замки.
 * В ООП это удобно. Тесты не парятся, кто будет подготавливать им рабочее место и закрывать всё после их работы.
 * Как в Макдоналдсе или ресторане - каждый выполняет свою задачу.
 */
public class LoginNegativeTests extends BaseTest {

        private LoginPage loginPage;

        // -------------------------------------------------------------------------
        // ТЕСТ 1: Заблокированный пользователь: неверный логин → верный пароль
        // -------------------------------------------------------------------------
        @Test
        public void lockedOutUserTest() {
                loginPage = new LoginPage(driver);
                loginPage.login("locked_out_user", "secret_sauce");
                Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Ошибка не появилась в поле, где должна была быть");
                Assert.assertEquals(loginPage.getErrorMessageText(),
                        "Epic sadface: Sorry, this user has been locked out.",
                        "Текст ошибки не совпал"
                );
        }

        // -------------------------------------------------------------------------
        // ТЕСТ 2: Все поля пустые (нажимаем кнопку, не вводя логин и пароль)
        // -------------------------------------------------------------------------
        @Test
        public void emptyFieldsTest() {
                loginPage = new LoginPage(driver);
                loginPage.clickLoginButton();
                Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Ошибка не появилась на экране");
                Assert.assertEquals(loginPage.getErrorMessageText(),
                        "Epic sadface: Username is required",
                        "Текст ошибки не совпал"
                );
        }

        // -------------------------------------------------------------------------
        // ТЕСТ 3: Неудачный вход: верный логин → неверный пароль
        // -------------------------------------------------------------------------
        @Test
        public void invalidCredentialsTest() {
                loginPage = new LoginPage(driver);
                loginPage.login("standard_user", "wrong_password");
                Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Ошибка не появилась на экране");
                Assert.assertEquals(loginPage.getErrorMessageText(),
                        "Epic sadface: Username and password do not match any user in this service",
                        "Текст ошибки не совпал"
                );
        }

        // -------------------------------------------------------------------------
        // ТЕСТ 4: Все поля неверные: неверный логин и неверный пароль.
        // -------------------------------------------------------------------------
        @Test
        public void allFieldsInvalidTest() {
                loginPage = new LoginPage(driver);
                loginPage.login("invalid", "invalidPassword");
                Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Ошибка не появилась на экране");
                Assert.assertEquals(loginPage.getErrorMessageText(),
                        "Epic sadface: Username and password do not match any user in this service",
                        "Текст ошибки не совпал"
                );
        }
}

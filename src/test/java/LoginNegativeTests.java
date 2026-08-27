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

        /** Переменная, которая будет хранить экземпляр класса страницы логина */
        private LoginPage loginPage;

        // -------------------------------------------------------------------------
        // ТЕСТ 1: Заблокированный пользователь: неверный логин → верный пароль
        // -------------------------------------------------------------------------
        @Test
        public void lockedOutUserTest() {
                // Создаём объект страницы, передавая в него драйвер из базового файла BaseTest
                loginPage = new LoginPage(driver);

                // Шаг 1. Вводим логин и пароль, нажимаем кнопку входа
                // Всё это делает SMART-METHOD login() внутри страницы
                loginPage.login("locked_out_user", "secret_sauce");

                // Шаг 2. Проверяем, что элемент с ошибкой виден на экране
                // Если isErrorMessageDisplayed() вернёт false, тест упадёт с сообщением,
                // которое тут передано вторым аргументом
                Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Ошибка не появилась в поле, где должна была быть");

                // Шаг 3. Проверяем точный текст ошибки
                // assertEquals(фактическое, ожидаемое, сообщение при падении)
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

                // Шаг 1. Находим кнопку входа, ничего не вводя.
                // Это проверяет, что сайт корректно обрабатывает пустые формы.
                loginPage.clickLoginButton();

                // Шаг 2. Проверяем, что ошибка появилась
                Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Ошибка не появилась на экране");

                // Шаг 3. Проверяем, что текст ошибки конкретный про пустые поля.
                Assert.assertEquals(loginPage.getErrorMessageText(),
                        "Epic sadface: Username is required",
                        "Текст ошибки не совпал"
                );
        }

        // -------------------------------------------------------------------------
        // ТЕСТ 3: Ну дачный вход: верный логин → неверный пароль
        // -------------------------------------------------------------------------
        @Test
        public void invalidCredentialsTest() {
                loginPage = new LoginPage(driver);

                // Шаг 1. Вводим корректный логин, но неправильный пароль
                loginPage.login("standard_user", "wrong_password");

                // Шаг 2. Проверяем, что ошибка появилась
                Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Ошибка не появилась на экране");

                // Шаг 3. Проверяем, что текст ошибки сообщает о неверных данных
                Assert.assertEquals(loginPage.getErrorMessageText(),
                        "Epic sadface: Username and password do not match any user in this service",
                        "Текст ошибки не совпал"
                );
        }
}

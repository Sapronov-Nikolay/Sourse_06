import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest {
    // @Test - метка для TestNG. Она говорит: "Запусти этот метод как тест".
    // Аналог в Python: просто функция test_... в pytest.
    @Test
    public void LockedOutUserTest() {
        // 1. Создаём экземпляр браузера (открываем окошко Chrome)
        // В Python это: driver = Chrome()
        WebDriver browser = new ChromeDriver();

        // 2. Переходим на страницу целевого сайта.
        // Аналог в Python: driver.get("https://www.saucedemo.com/")
        browser.get("https://www.saucedemo.com/");

        // 3. вводим логин заведомо неправильный логин пользователя
        // findElement(By.id(...)) - находим элемент по его id.
        // sendKeys(...) - вводим текст в поле
        // Аналог в Python: driver.find_element(By.ID, "user_name").send_keys("locked_out_user")
        browser.findElement(By.id("user-name")).sendKeys("locked_out_user");

        // 4. Вводим верный пароль.
        browser.findElement(By.id("password")).sendKeys("secret_sauce");

        // 5. Нажимаем кнопку ввода.
        // click() - эмулирует реальный клик мышкой - (по умолчанию левой кнопки мыши)
        // Аналог в Python: driver.find_element(By.ID, "login-button").click()
        browser.findElement(By.id("login-button")).click();

        // 6. Ищем элемент с сообщением об ошибке.
        // В вёрстке используется CSS-селектор по атрибуту data-test="error"
        // Это часто надёжно, так как такие селектора редко меняются при правке вёрстки
        // Квадратные скобки означают, что ищем атрибут.
        // Аналог в Python: By.CSS_SELECTOR, "[data-test='error']"
        WebElement errorMessage = browser.findElement(By.cssSelector("[data-test='error']"));

        // 7. Проверяем, виден ли элемент на экране.
        // isDisplayed() возвращает true, если элемент есть и он видим пользователю.
        // Если элемент скрыт (display:none) или ещё не отрисован, будет false.
        boolean isVisible = errorMessage.isDisplayed();

        // 8. Получаем текст ошибки, который видит пользователь.
        // getText() возвращает видимый текст внутри элемента (как element.text в Python)
        String errorText = errorMessage.getText();

        // 9. Первая проверка: убеждаемся, что сообщение об ошибке действительно появилось.
        // Assert.assertTrue(условие, сообщение_при_ошибке).
        // Если isVisible == false, тест упадёт, и в консоль ведётся другой текст.
        // Аналог в Python: assert is_visible, "Ошибка не появилась на экране"
        Assert.assertTrue(isVisible, "Ошибка не появилась на экране");

        // 10. Вторая проверка: сверяем сам текст ошибки если таковая отобразилась.
        // Assert.assertEquals(фактическое, ожидаемое, сообщение_при_ошибке)
        // Если тексты не совпадут, тест упадёт.
        // Аналог в Python: assert error_text == expected_text
        Assert.assertEquals(
                errorText,
                "Epic sadface: Sorry, this user has been locked out.",
                "Текст ошибки не совпал"
        );

        // 11. Обязательно закрываем браузер в конце теста.
        // quit() полностью закрывает браузер и все его окна, освобождает ресурсы.
        // Если забыть эту строчку, процессы Chrome останутся висеть в фоне и быстро съедят всю память.
        // Аналог в Python: driver.quit()
        browser.quit();
    }
}

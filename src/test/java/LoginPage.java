import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Класс, который описывает страницу логина (Page Object Model).
 * Суть этого в том, что мы храним локаторы (селекторы элементов) и методы взаимодействия с ними в одном месте.
 * Почему это хорошо?
 * 1. Если сайт изменится, мы правим код только в одном классе, а тесты трогать не придётся
 * 2. Код тестов становится читабельным. Тесты не перегружены массой кода.
 * 3. Соблюдается принцип инкапсуляции - мы разделяем обязанности между блоками и видами кода.
 */
public class LoginPage {
    // Храним экземпляр WebDriver, который передан из теста
    private WebDriver driver;

    // ---- ЛОКАТОРЫ ----
    // By - это класс, который позволяет описать способ поиска элемента.
    // By.id("...") - тут, например, мы ищем элемент по его уникальному атрибуту id.
    private By usernameField = By.id("user-name");

    // By.cssSelector(...) - ищем элемент по CSS-селектору.
    // В данном случае мы ищем элемент с атрибутом data-test="error".
    // Это надёжный локатор, потому что атрибуты data-test часто специально добавляют для тестов.
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    // Локатор для проверки успешного логина
    private By productsTitle = By.cssSelector("[data-test='title']");

    /**
     * Конструктор класса.
     * Он принимает WebDriver, который будет использоваться для поиска элементов.
     * Это необходимо, так как без драйвера мы не можем взаимодействовать со страницей.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Метод для ввода логина (usernsme).
     * Он инкапсулирует логику поиска поля и ввода туда текста.
     */
    public void enterUsername(String username) {
        // findElement - это команда драйверу: найди элемент по локатору.
        // sendKeys - вводит заданный текст в найденное поле.
        driver.findElement(usernameField).sendKeys(username);
    }

    /**
     * Метод для ввода пароля.
     */
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    /**
     * Метод для клика по кнопке логина.
     * Нам надо его не только использовать других методах и тестах, а тут создать, как бы палец, кликающий по кнопке.
     */
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    /**
     * SMART-METHOD (комбинированный).
     * Этот метод объединяет три действия в одно.
     * Его удобно вызывать из тестов, чтобы не повторять три строки каждый раз.
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    /**
     * Проверяем, виден ли элемент с ошибкой на экране
     * Возвращаем boolean.
     */
    public boolean isErrorMessageDisplayed() {
        // isDisplayed() - возвращает true, если элемент видим пользователю.
        // Если элемент скрыт (display: none) или не отрисован - вернёт false.
        return driver.findElement(errorMessage).isDisplayed();
    }

    /**
     * Получаем текст ошибки.
     * Возвращаем String, который пользователь видит на экране.
     */
    public String getErrorMessageText() {
        // getText() - возвращает видимый текст внутри элемента.
        return driver.findElement(errorMessage).getText();
    }

    /**
     * Проверяем успешный вход в кабинет
     * Если видно заголовок "Products" на экране, значит страница прогрузилась
     */
    public boolean isProductsPageDisplayed() {
        // Если элемента нет или он скрыт - вернётся false.
        return driver.findElement(productsTitle).isDisplayed();
    }
}

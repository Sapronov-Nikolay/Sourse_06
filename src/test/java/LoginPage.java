import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Класс, который описывает страницу логина (Page Object Model).
 * Суть этого в том, что мы храним локаторы (селекторы элементов) и методы взаимодействия с ними в одном месте.
 */
public class LoginPage {
    private final WebDriver driver;
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");
    private final By productsTitle = By.cssSelector("[data-test='title']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    /** SMART-METHOD (комбинированный). */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }

    public String getErrorMessageText() {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isProductsPageDisplayed() {
        return driver.findElement(productsTitle).isDisplayed();
    }
}

package pages;

import io.qameta.allure.Step;
import locators.LoginPageLocators;
import org.openqa.selenium.WebDriver;

/**
 * Класс, который описывает страницу логина (Page Object Model).
 * Суть этого в том, что мы храним локаторы (селекторы элементов) и методы взаимодействия с ними в одном месте.
 */
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("1. Ввести логин: {username}")
    public LoginPage enterUsername(String username) {
        sendKeys(LoginPageLocators.USERNAME_FIELD, username);
        return this;
    }

    @Step("2. Ввести пароль: {password}")
    public LoginPage enterPassword(String password) {
        sendKeys(LoginPageLocators.PASSWORD_FIELD, password);
        return this;
    }
    
    @Step("3. Нажать кнопку входа {login-button}")
    public LoginPage clickLoginButton() {
        click(LoginPageLocators.LOGIN_BUTTON);
        return this;
    }

    /** SMART-METHOD (комбинированный): вводим логин, пароль и кликаем. */
    public LoginPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return this;
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(LoginPageLocators.ERROR_MESSAGE);
    }

    public String getErrorMessageText() {
        return getText(LoginPageLocators.ERROR_MESSAGE);
    }
}

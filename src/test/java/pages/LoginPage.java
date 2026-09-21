package pages;

import io.qameta.allure.Step;
import locators.LoginPageLocators;
import org.openqa.selenium.By;
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
    public void enterUsername(String username) {
        sendKeys(LoginPageLocators.USERNAME_FIELD, username);
    }

    @Step("2. Ввести пароль: {password}")
    public void enterPassword(String password) {
        sendKeys(LoginPageLocators.PASSWORD_FIELD, password);
    }
    
    
    @Step("3. Нажать кнопку входа {login-button}")
    public void clickLoginButton() {
        click(LoginPageLocators.LOGIN_BUTTON);
    }

    /** SMART-METHOD (комбинированный): вводим логин, пароль и кликаем. */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(LoginPageLocators.ERROR_MESSAGE);
    }

    public String getErrorMessageText() {
        return getText(LoginPageLocators.ERROR_MESSAGE);
    }
}

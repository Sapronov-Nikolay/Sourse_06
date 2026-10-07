package locators;

import org.openqa.selenium.By;

/**
 * Локаторы страницы логина.
 */
public final class LoginPageLocators {
    
    private LoginPageLocators(){}
    
    public static final By USERNAME_FIELD = By.id("user-name");
    public static final By PASSWORD_FIELD = By.id("password");
    public static final By LOGIN_BUTTON = By.id("login-button");
    public static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");
}

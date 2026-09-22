package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import user.User;
import user.UserFactory;

/**
 * Негативные проверки авторизации.
 * Всего 14 тестов:
 *   1. Заблокированный пользователь
 *   2. Неверный логин + верный пароль
 *   3–8. Шесть валидных логинов + неверный пароль (DataProvider)
 *   9. Неверный логин + неверный пароль
 *  10–14. Пустые и комбинированные поля (DataProvider)
 */
@Epic("Swag Labs")
@Feature("ввод логина и пароля")
public class LoginNegativeTests extends BaseTest {
    
    // ГРУППА 1: Заблокированный пользователь
    @Test(description = "Заблокированный пользователь: верный логин и верный пароль", priority = 1)
    @Story("Вариант валидных данных, которым нельзя")
    @Severity(SeverityLevel.BLOCKER)
    public void lockedOutUserTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            UserFactory.lockedOutUser().getUsername(),
            UserFactory.lockedOutUser().getPassword()
        );
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Сообщение об ошибке не появилось");
        Assert.assertEquals(loginPage.getErrorMessageText(),
            "Epic sadface: Sorry, this user has been locked out.");
    }
    
    // ГРУППА 2: Неверный логин + верный пароль
    @Test(description = "Неверный логин и верный пароль", priority = 2)
    @Story("Валидация неверного логина с верным паролем")
    @Severity(SeverityLevel.CRITICAL)
    public void invalidUsernameValidPasswordTest() {
        LoginPage loginPage = new LoginPage(driver);
        User user = UserFactory.invalidUsernameValidPassword();
        loginPage.login(user.getUsername(), user.getPassword());
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),"Сообщение об ошибке не появилось");
        Assert.assertEquals(loginPage.getErrorMessageText(),
            "Epic sadface: Username and password do not match any user in this service");
    }
    
    // ГРУППА 3: Шесть валидных логинов + неверный пароль (DataProvider)
    @DataProvider(name = "allValidUsers")
    public Object[][] allValidUsers() {
        return new Object[][] {
            {UserFactory.standardUser()},
            {UserFactory.problemUser()},
            {UserFactory.lockedOutUser()},
            {UserFactory.performanceGlitchUser()},
            {UserFactory.errorUser()},
            {UserFactory.visualUser()},
        };
    }
    
    @Test(
        description = "Валидный логин + неверный пароль",
        dataProvider = "allValidUsers",
        priority = 3
    )
    @Story("Валидный логин и неверный пароль")
    @Severity(SeverityLevel.CRITICAL)
    public void validUserWrongPasswordTest(User user) {
        LoginPage loginPage = new LoginPage(driver);
        
        // Берём валидный логин из фабрики, но подменяем пароль на неверный
        loginPage.login(user.getUsername(), "error_password");
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
            "Ошибка не появилась для пользователя: " + user.getUsername());
        Assert.assertEquals(loginPage.getErrorMessageText(),
            "Epic sadface: Username and password do not match any user in this service");
    }
    
    // ГРУППА 5: Пустые и комбинированные поля (DataProvider)
    /**
     * DataProvider возвращает: логин, пароль, ожидаемое сообщение.
     * Каждая строка — отдельный тест в Allure-отчёте.
     */
    @DataProvider(name = "emptyFieldCombinations")
    public Object[][] emptyFieldCombinations(){
        return new Object[][] {
            //  логин,              пароль,             ожидаемая ошибка
            {"",                    "",                 "Epic sadface: Username is required"},
            {"",                    "secret_sauce",     "Epic sadface: Username is required"},
            {"standard_user",       "",                 "Epic sadface: Password is required"},
            {"",                    "error_password",   "Epic sadface: Username is required"},
            {"invalid_user",        "",                 "Epic sadface: Password is required"},
        };
    }
    @Test(
        description = "Пустые и комбинированные поля",
        dataProvider = "emptyFieldCombinations",
        priority = 5
    )
    @Story("Валидация пустых и комбинированных полей")
    public void emptyAndCombinedFieldsTest(String username, String password, String expectedError) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
            "Ошибка не появилась. Логин: '" + username + "', пароль: '" + password + "'");
        Assert.assertEquals(loginPage.getErrorMessageText(), expectedError,
            "Неверный текст ошибки. Логин: '" + username + "', пароль: '" + password + "'");
    }
}

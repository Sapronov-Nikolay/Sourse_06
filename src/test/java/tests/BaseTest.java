package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;
import user.User;
import user.UserFactory;
import utils.PropertyReader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/** Базовый класс для всех тестов. setUp() готовит браузер перед тестом, tearDown() закрывает его после теста. */
public abstract class BaseTest {

    protected WebDriver driver;
    
    // Page Object'ы создаются один раз в BaseTest
    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;
    
    /** Перед тестами */
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("start-maximized");
        
        // Отключаем менеджер паролей Chrome и предупреждение об утечке данных
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);           // отключить "Save password?"
        prefs.put("profile.password_manager_enabled", false);     // отключить менеджер паролей
        prefs.put("profile.password_manager_leak_detection", false); // отключить "Смените пароль"
        options.setExperimentalOption("prefs", prefs);
        options.addArguments(
            "--disable-features=PasswordLeakDetection");  // Отключаем фичу проверки паролей на уровне движка
        
        driver = new ChromeDriver(options);
        driver.get(PropertyReader.getProperty("saucedemo.url"));
        
        // Создаём Page Object'ы после открытия сайта
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }
    
    protected void loginAs(User user) {
        loginPage.login(user.getUsername(), user.getPassword());
    }
    
    protected void loginAs(String username, String password) {
        loginPage.login(username, password);
    }
    
    protected void loginAsStandardUser() {
        loginAs(UserFactory.standardUser());
    }
    
    /** После тестов */
    @AfterMethod
    public void tearDown(ITestResult result) {
        // Если тест упал (FAILURE), делаем скриншот.
        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            takeScreenshot(result.getName());
        }
        if (driver != null) {
            driver.quit();
        }
    }

    /** Метод для снятия скриншота при падении теста и записи фактического результата */
    public void takeScreenshot(String testName) {
        try {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            
            File screenshotsDir = new File("screenshots");
            if (!screenshotsDir.exists() && !screenshotsDir.mkdirs()) {
                throw new IOException("Не удалось создать папку screenshots");
            }
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH.mm.ss");
            String timestamp = LocalDateTime.now().format(formatter);
            String safeTestName = testName.replaceAll("[^a-zA-Zа-яА-Я0-9_-]", "_");
            String fileName = String.format("screenshot_%s_%s.png", safeTestName, timestamp);
            
            File destination = new File(screenshotsDir, fileName);
            Files.write(destination.toPath(), screenshotBytes);
            
            // Добавляем тот же скриншот в Allure
            Allure.addAttachment("Screenshot: " + fileName, new ByteArrayInputStream(screenshotBytes));
            
        } catch (IOException e) {
            System.out.println("\uD83D\uDEAB Ошибка при сохранении скриншота: " + e.getMessage());
        }
    }
}

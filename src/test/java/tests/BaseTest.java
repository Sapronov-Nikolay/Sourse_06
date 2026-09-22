package tests;

import user.User;
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
import pages.LoginPage;
import user.UserFactory;
import utils.PropertyReader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Базовый класс для всех тестов. setUp() готовит браузер перед тестом, tearDown() закрывает его после теста. */
public abstract class BaseTest {

    protected WebDriver driver;
    
    /** Перед тестами */
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("start-maximized");
        
        driver = new ChromeDriver(options);
        driver.get(PropertyReader.getProperty("saucedemo.url"));
    }
    
    /** Авторизация стандартным пользователем для тестов корзины и checkout. */
    protected void loginAsStandardUser() {
        LoginPage loginPage = new LoginPage(driver);
        User user = UserFactory.standardUser();
        loginPage.login(user.getUsername(), user.getPassword());
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

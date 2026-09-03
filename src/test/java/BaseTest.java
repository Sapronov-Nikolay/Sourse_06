import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Базовый класс для всех тестов. От отвечает за инфраструктуру: Запускает браузер, его настраивает и закрывает. */
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Если тест упал (FAILURE), делаем скриншот.
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getName());
        }
        if (driver != null) {
            driver.quit();
        }
    }

    /**Метод для снятия скриншота и записи фактического результата*/
    public void takeScreenshot(String testName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            File screenshotsDir = new File("screenshots");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdirs();
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH.mm.ss");
            String timeStamp = LocalDateTime.now().format(formatter);
            File destFile = new File(screenshotsDir, testName + "_" + timeStamp + ".png");
            Files.copy(srcFile.toPath(), destFile.toPath());
            System.out.println("❌ ТЕСТ УПАЛ: \" + testName");
            System.out.println("\uD83D\uDCF8 Скриншот сохранён: \" + destFile.getAbsolutePath()");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении скриншота: " + e.getMessage());
        }
    }
}

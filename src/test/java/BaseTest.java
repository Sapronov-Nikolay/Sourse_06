import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Базовый класс для всех тестов.
 * От отвечает за инфраструктуру: Запускает браузер, его настраивает и закрывает.
 * Это нужно, чтобы не писать один и тот же код много раз в каждом тесте заново.
 * Мы выносим это в отдельный класс, а тесты просто наследуются от него и получают готовую "умелку".
 */

public abstract class BaseTest {

    /** protected - означает, что переменная наследуется только теми тестами для которых предназначена*/
    protected WebDriver driver;

    /**
     * @BeforeMethod - анотация TestNG
     * Метод с ней гарантированно выполнится первым - перед тестовым методом (@Test).
     * Это гарантирует, что всё что он делает запустится и подготовится для работы теста
     * Тест "проснётся", а уже всё готово для его прохождения
     */
    @BeforeMethod
    public void setUp() {
        // 1. Создаём объект настроек Chrome
        ChromeOptions options = new ChromeOptions();
        // Добавляем этот аргумент, чтобы браузер не открывался
        options.addArguments("--headless");
        // 2. Добавляем аргумент: разворачиваем окно браузера на весь экран
        options.addArguments("start-maximized");
        // 3. Передаём настройки в конструктор ChromeDriver - теперь браузер откроется максимальным
        driver = new ChromeDriver(options);
        // 4. Переходим на страницу логина
        driver.get("https://www.saucedemo.com/");
    }

    /**
     * @AfterMethod - аннотация TestNG.
     * Метод выполняется после @Test, чтобы прибраться за собой в памяти компьютера.
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        // Если тест упал (FAILURE), делаем скриншот.
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getName());
        }
        // Проверяем, что драйвер существует и нам есть что закрывать (не null).
        // Так как метод закрытия отдельный как "закрывашка", то он должен опираться на хоть что-то о том, что происходит.
        // Метод как бы не знает что и зачем и почему происходит и происходит ли. Его задача закрыть когда скажут.
        if (driver != null) {
            // То обязательно закрываем браузер в конце теста.
            // quit() полностью закрывает браузер и все его окна, освобождает ресурсы.
            // Если забыть эту строчку, процессы Chrome останутся висеть в фоне и быстро съедят всю память.
            // Аналог в Python: driver.quit()
            driver.quit();
        }
    }

    // Метод для снятия скриншота и записи фактического результата
    public void takeScreenshot(String testName) {
        try {
            // 1. Приводим driver к типу TakesScreenshot (умеет делать скрины)
            TakesScreenshot screenshot = (TakesScreenshot) driver;

            // 2. Забираем скриншот в виде файла
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

            // 3. Создаём папку "screenshot" в конце проекта, если её нет
            File screenshotsDir = new File("screenshots");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdirs();
            }

            // 4. Код даты в читабельном формате
            // Создаем "форматтер", который превращает время в строку нужного вида.
            //  dd - день, MM - месяц, yyyy - год, HH - часы, mm - минуты, ss - секунды.
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH.mm.ss");
            // Получаем текущую дату и время и сразу форматируем её в строку по шаблону
            String timeStamp = LocalDateTime.now().format(formatter);
            // 4. Копируем файл с именем теста и временем (чтобы не пересохранять)
            File destFile = new File(screenshotsDir, testName + "_" + timeStamp + ".png");
            Files.copy(srcFile.toPath(), destFile.toPath());

            // 5. Выводим в консоль сообщения
            System.out.println("❌ ТЕСТ УПАЛ: \" + testName");
            System.out.println("\uD83D\uDCF8 Скриншот сохранён: \" + destFile.getAbsolutePath()");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении скриншота: " + e.getMessage());
        }
    }
}

package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;
import java.time.Duration;

/**
 * Базовый класс для всех страниц. Содержит общие методы и wait.
 */
public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    
    public static final String BASE_URL = PropertyReader.getProperty("saucedemo.url");
    
    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));  // Ожидание загрузки элементов = 15 сек.
    }

    @Step("1. Кликнуть по элементу: {locator}")
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    @Step("2. Ввести текст: {text} в поле: {locator}")
    protected void sendKeys(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    @Step("3. Получить текст элемента: {locator}")
    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    @Step("4. Проверить видимость элемента: {locator}")
    protected boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

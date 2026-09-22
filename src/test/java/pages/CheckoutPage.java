package pages;

import io.qameta.allure.Step;
import locators.CheckoutPageLocators;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** Page Object страниц оформления заказа */
public class CheckoutPage extends BasePage {
    
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    
    @Step("1. Заполнить данные: {firstName} / {lastName} / {postalCode}")
    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        sendKeys(CheckoutPageLocators.FIRST_NAME, firstName);
        sendKeys(CheckoutPageLocators.LAST_NAME, lastName);
        sendKeys(CheckoutPageLocators.POSTAL_CODE, postalCode);
    }
    
    @Step("2. Нажать Continue")
    public void clickContinue() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(CheckoutPageLocators.CONTINUE_BUTTON));
        // JS-клик — как в CartPage.clickCheckout()
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        // Ждём перехода на вторую страницу
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
    }
    
    @Step("3. Нажать Finish (завершить заказ)")
    public void clickFinish() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(CheckoutPageLocators.FINISH_BUTTON));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        // Ждём, пока браузер перейдёт на страницу "Checkout: Complete!"
        wait.until(ExpectedConditions.urlContains("checkout-complete"));
    }
    
    @Step("4. Получить заголовок страницы")
    public String getPageTitle() {
        return getText(CheckoutPageLocators.TITLE);
    }
    
    @Step("5. Получить сообщение об успешном заказе")
    public String getCompleteHeaderText() {
        return getText(CheckoutPageLocators.COMPLETE_HEADER);
    }
}

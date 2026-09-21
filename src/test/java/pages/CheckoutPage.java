package pages;

import io.qameta.allure.Step;
import locators.CheckoutPageLocators;
import org.openqa.selenium.WebDriver;

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
        click(CheckoutPageLocators.CONTINUE_BUTTON);
    }
    
    @Step("3. Нажать Finish (завершить заказ)")
    public void clickFinish() {
        click(CheckoutPageLocators.FINISH_BUTTON);
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

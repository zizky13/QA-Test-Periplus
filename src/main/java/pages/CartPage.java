package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    private By itemQuantity = By.cssSelector("input[id^='qty_']");
    private By emptyCartMessage = By.xpath("//div[@class='content']");
    private By removeBtn = By.xpath("//button[@name='minus']");


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clearCart() {
        waitForPreloader();
        if (!isCartEmpty() ){
            while(!driver.findElements(removeBtn).isEmpty()) {
                click(removeBtn);
                waitForPreloader();
            }
        }
    }

    public boolean isCartEmpty() {
        waitForPreloader();
        return !driver.findElements(emptyCartMessage).isEmpty();
    }

    public int getItemQuantity() {
        waitForPreloader();
        String value = "";

        try {
            value = wait.until(ExpectedConditions.visibilityOfElementLocated(itemQuantity)).getAttribute("value");
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new RuntimeException("CRITICAL DATA ERROR: Could not parse cart quantity. Raw value found: '" + value + "'");
        }
    }
}

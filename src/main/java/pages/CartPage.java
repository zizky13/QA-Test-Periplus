package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {

    private final By itemQuantity = By.cssSelector("input[id^='qty_']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getTotalBasketQuantity() {
        // This function captures total quantity of all item in the cart
        waitForPreloader();

        List<WebElement> quantityFields = driver.findElements(itemQuantity);

        if (quantityFields.isEmpty()) {
            return 0;
        }

        int totalCount = 0;
        for (WebElement field: quantityFields) {
            try {
                String val = field.getAttribute("value");
                totalCount += Integer.parseInt(val);
            } catch (Exception e) {
                throw new RuntimeException("CRITICAL DATA ERROR: Could not parse cart quantity.");
            }
        }

        return totalCount;
    }

    public int getItemQuantity() {
        // get single item quantity for the topmost item in cart
        waitForPreloader();
        String value = "";

        if (driver.findElements(itemQuantity).isEmpty()){
            return 0;
        }

        try {
            value = wait.until(ExpectedConditions.visibilityOfElementLocated(itemQuantity)).getAttribute("value");
            return (value == null || value.isEmpty() ? 0 : Integer.parseInt(value));
        } catch (Exception e) {
            throw new RuntimeException("CRITICAL DATA ERROR: Could not parse cart quantity. Raw value found: '" + value + "'");
        }
    }
}

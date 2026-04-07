package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage extends BasePage {
    private final By addToCartLocation = By.cssSelector(".btn.btn-add-to-cart");


    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart() {
        click(addToCartLocation);
    }
}

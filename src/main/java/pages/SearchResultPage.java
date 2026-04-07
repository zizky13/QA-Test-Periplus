package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage extends BasePage {
    private final By bookToAddLocation = By.xpath("/html/body/section/div/div/div[2]/div[1]/div[1]/div/div[1]/a");

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public void selectFirstResult() {
        click(bookToAddLocation);
    }
}

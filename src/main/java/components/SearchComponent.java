package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchComponent {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By searchInput = By.id("filter_name_desktop");
    private final By searchSubmit = By.cssSelector("div[class='search-bar d-flex w-100'] button[type='submit']");


    public SearchComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void executeSearch(String input) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput)).clear();
        driver.findElement(searchInput).sendKeys(input);
        driver.findElement(searchSubmit).click();
    }
}

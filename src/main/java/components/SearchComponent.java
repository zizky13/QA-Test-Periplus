package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// This component act as the locator for search component (search bar and the search button)
// This class is decoupled from base since Search functionality needs to be accessible from anywhere

public class SearchComponent {
    private final WebDriverWait wait;

    private final By searchInput = By.id("filter_name_desktop");
    private final By searchSubmit = By.cssSelector("div[class='search-bar d-flex w-100'] button[type='submit']");


    public SearchComponent(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void executeSearch(String input) {
        // wait for rendering to stop, in which expect the search input to visible
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        inputField.clear();
        inputField.sendKeys(input);
        wait.until(ExpectedConditions.elementToBeClickable(searchSubmit)).click();
    }
}

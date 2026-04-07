package pages;

import components.SearchComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    // drivers
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions action;
    public SearchComponent searchComponent;


    // locators
    private final By signInBtnToHover = By.cssSelector("#nav-signin-text > a");
    private final By preloader = By.cssSelector("body > div.preloader");
    private final By loginBtnToHover = By.linkText("Login");
    private final By cartButtonLocation = By.xpath("//*[@id=\"show-your-cart\"]/a");

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.action = new Actions(driver);
        this.searchComponent = new SearchComponent(driver);
    }

    // ==== START OF FUNCTIONS SECTION ====
    protected void waitForPreloader() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloader));
    }

    protected void click(By locator) {
        waitForPreloader();
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    private void hoverToSignIn() {
        // - Wait for the sign in to be visible
        // - Perform hover
        // web elements
        WebElement signInBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(signInBtnToHover));
        action.moveToElement(signInBtn).perform();
    }

    public void goToLogin() {
        // wait for any preloader
        waitForPreloader();
        // hover to sign in dropdown
        hoverToSignIn();
        click(loginBtnToHover);
    }

    public void goToCartPage() {
        waitForPreloader();
        wait.until(ExpectedConditions.elementToBeClickable(cartButtonLocation)).click();
    }
}

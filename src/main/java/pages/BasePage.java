package pages;

import components.SearchComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


// Acts as central class for the locators
// Basically hold up all the navbar components except search component

public class BasePage {
    // drivers
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions action;
    public SearchComponent searchComponent;


    // locators
    private final By signInBtnToHover = By.id("nav-signin-text");
    private final By logoutBtnToHover = By.xpath("//div[@class='shopping-item']//a[normalize-space()='Logout']");
    private final By preloader = By.xpath("/html/body/div[2]");
    private final By loginBtnToHover = By.linkText("Login");
    private final By modalCloseLocator = By.cssSelector("#Notification-Modal > div > div > div > div:nth-child(1) > button");
    protected final By cartButtonLocation = By.xpath("//*[@id=\"show-your-cart\"]/a");


    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.action = new Actions(driver);
        this.searchComponent = new SearchComponent(driver);
    }

    // ==== START OF FUNCTIONS SECTION ====
    // The class is designed to be Atomic. So it would be easier to maintain later
    protected void waitForPreloader() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloader));
    }

    public void closeModalAfterAddItem() {
        click(modalCloseLocator);
    }

    protected void click(By locator) {
        waitForPreloader();
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            waitForPreloader();
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        }
    }

    protected void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    private void hoverToSignIn() {
        // - Wait for the sign in to be visible
        // - Perform hover
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

    public void logout() {
        waitForPreloader();
        hoverToSignIn();
        click(logoutBtnToHover);
        waitForPreloader();
    }

    public void goToCartPage() {
        waitForPreloader();
        click(cartButtonLocation);
    }
}

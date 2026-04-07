package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions action;
    private WebElement signInBtn;
    private WebElement loginBtn;

    // locators
    private By searchBookTextArea = By.id("filter_name_desktop");
    private By signInBtnToHover = By.xpath("//*[@id=\"nav-signin-text\"]/a");
    private By searchBookBtn = By.xpath("/html/body/header/div[2]/div/div[1]/div[2]/div/form/div/button");
    private By loginBtnToHover = By.xpath("/html/body/header/div[2]/div/div[1]/div[3]/div/div[3]/div/ul/li[1]/a");
    private By bookToAddLocation = By.xpath("/html/body/section/div/div/div[2]/div[1]/div[1]/div/div[1]/a");
    private By addToCartLocation = By.xpath("/html/body/div[3]/div[1]/div/div/div[3]/div[1]/div[3]/div[1]/button");
    private By preloader = By.className("preloader");



    public HomePage (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.action = new Actions(driver);
    }

    public void waitForPreloader() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloader));
    }

    public void enterSearchTerm(String title) {
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(searchBookTextArea));
        searchBox.click();
        searchBox.sendKeys(title);
    }

    public void clickSearch() {
        waitForPreloader();
        driver.findElement(searchBookBtn).click();
    }

    public void selectFirstResult() {
        waitForPreloader();
        wait.until(ExpectedConditions.elementToBeClickable(bookToAddLocation)).click();
    }

    public void addToCart() {
        waitForPreloader();
        wait.until(ExpectedConditions.elementToBeClickable(addToCartLocation)).click();
        wait.withTimeout(Duration.ofSeconds(5));
    }

    public void searchAndAddFirstResultToCart(String title) {
        enterSearchTerm(title);
        clickSearch();
        selectFirstResult();
        addToCart();
    }

    public void hoverToSignIn() {
        signInBtn = driver.findElement(signInBtnToHover);
        action.moveToElement(signInBtn).perform();
        wait.withTimeout(Duration.ofSeconds(5));
    }

    public void hoverToLogin() {
        loginBtn = driver.findElement(loginBtnToHover);
        action.moveToElement(loginBtn).perform();
        wait.withTimeout(Duration.ofSeconds(5));
    }

    public void goToLogin() {
        waitForPreloader();
        hoverToSignIn();
        hoverToLogin();
        action.click(loginBtn).perform();
    }
}

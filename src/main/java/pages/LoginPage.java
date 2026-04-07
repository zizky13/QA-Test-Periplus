package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    // drivers inherited from BasePage

    // locators
    private By emailTextFormLocation = By.xpath("//*[@id=\"login\"]/div/table/tbody/tr[2]/td/input");
    private By passwordTextFormLocation = By.xpath("//*[@id=\"ps\"]");
    private By submitButtonLocation = By.xpath("//*[@id=\"button-login\"]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginAs(String email, String password) {
        type(emailTextFormLocation, email);
        type(passwordTextFormLocation, password);
        click(submitButtonLocation);
        waitForPreloader();
    }

}

package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class CartTest extends BaseTest {
    @Test
    public void searchBookAndLogin() {
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        CartPage cartPage = new CartPage(driver);
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);

        // Take initial quantity of the comparable item first, make sure there is no product on cart page
        basePage.goToLogin();
        loginPage.loginAs(prop.getProperty("username"), prop.getProperty("password"));
        basePage.goToCartPage();
        int initialQuantity = cartPage.getItemQuantity();
        basePage.logout();

        // Search any product and add it to cart
        basePage.searchComponent.executeSearch("Sapiens");
        searchResultPage.selectFirstResult();
        productDetailPage.addToCart();

        // Login as user, then go to cart page to validate quantity
        basePage.goToLogin();
        // credential provided is a dummy email
        loginPage.loginAs(prop.getProperty("username"), prop.getProperty("password"));
        basePage.goToCartPage();
        int finalQuantity = cartPage.getItemQuantity();

        Assert.assertEquals(finalQuantity, initialQuantity + 1,
                "Cart Merge Failure: Total items do not match Initial (" + initialQuantity + ") + Added (1)");
    }
}

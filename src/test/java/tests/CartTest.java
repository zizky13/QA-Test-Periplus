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

        basePage.searchComponent.executeSearch("Sapiens");
        searchResultPage.selectFirstResult();
        productDetailPage.addToCart();

        basePage.goToLogin();
//        // credential provided is a dummy email
        loginPage.loginAs("insert email", "insert password");
        basePage.goToCartPage();
        int quantity = cartPage.getItemQuantity();
//
        Assert.assertEquals(quantity, 1, "Guess cart is note merged to the main account!");
    }
}

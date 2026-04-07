package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class CartTest extends BaseTest {
    @Test
    public void loginAndAddProductToCart() {
        // Initalize all necessary pages locator
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        CartPage cartPage = new CartPage(driver);
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);

        // Login
        basePage.goToLogin();
        loginPage.loginAs(prop.getProperty("username"), prop.getProperty("password"));

        // Navigate to cart page
        basePage.goToCartPage();

        // Get total current quantity
        int initialQuantity = cartPage.getTotalBasketQuantity();

        // Search "Sapiens"
        basePage.searchComponent.executeSearch("Sapiens");

        // Grab the first result
        searchResultPage.selectFirstResult();

        // Add it to cart
        productDetailPage.addToCart();

        // Go to cart page
        basePage.goToCartPage();

        // Grab final quantity
        int finalQuantity = cartPage.getTotalBasketQuantity();

        Assert.assertEquals(finalQuantity, initialQuantity + 1, "Failed to add item to cart");
    }

    @Test
    public void verifyMergeCart() {
        // Initialize all necessary pages locators
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

        // Search "Sapiens" and add it to cart
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

    @Test
    public void verifyMergeCartForMultipleItems() {
        // Initialize all necessary pages locators
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        CartPage cartPage = new CartPage(driver);
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);

        // Login
        basePage.goToLogin();
        loginPage.loginAs(prop.getProperty("username"), prop.getProperty("password"));

        // Go to cart page
        basePage.goToCartPage();

        // Take initial basket quantity
        int initialTotal = cartPage.getTotalBasketQuantity();

        // Logout
        basePage.logout();

        // Search for "Sapiens" and "Homo Deus"
        String[] booksToAdd = {"Sapiens", "Homo Deus"};
        for (String book: booksToAdd) {
            // Add each item to cart
            basePage.searchComponent.executeSearch(book);
            searchResultPage.selectFirstResult();
            productDetailPage.addToCart();
        }

        // Login again
        basePage.goToLogin();
        loginPage.loginAs(prop.getProperty("username"), prop.getProperty("password"));

        // Go to cart
        basePage.goToCartPage();

        // Get final total and check whether basket quantity change
        int finalTotal = cartPage.getTotalBasketQuantity();

        Assert.assertEquals(finalTotal, initialTotal + booksToAdd.length, "Basket Merge Failure: Expected " + (initialTotal + 2) + " but found" + finalTotal);
    }
}

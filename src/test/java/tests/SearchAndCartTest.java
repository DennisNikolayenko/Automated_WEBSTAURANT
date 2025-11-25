package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.ConfigReader;

public class SearchAndCartTest extends BaseTest {

    @Test(description = "Search for product, verify results, add to cart, and empty cart")
    public void testSearchAndAddToCart() {
        logger.info("=== Starting testSearchAndAddToCart ===");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page should be loaded");
        logger.info("Step 1: Home page loaded successfully");

        String searchTerm = ConfigReader.getProperty("search.term");
        SearchResultsPage searchResultsPage = homePage.search(searchTerm);
        logger.info("Step 2: Search performed for '{}'", searchTerm);

        String expectedKeyword = ConfigReader.getProperty("expected.search.keyword");
        boolean allContainKeyword = searchResultsPage.verifyAllProductsContainKeyword(expectedKeyword);
        Assert.assertTrue(allContainKeyword, 
            "All search results should contain keyword: " + expectedKeyword);
        logger.info("Step 3: Verified all products contain keyword '{}'", expectedKeyword);

        searchResultsPage.clickOnLastProduct()
                .addToCartAndView();
        logger.info("Step 4: Last product added to cart and cart page opened");

        CartPage cartPage = new CartPage(driver);
        cartPage.emptyCart();
        logger.info("Step 5: Cart emptied");

        String expectedMessage = ConfigReader.getProperty("empty.cart.message");
        String actualMessage = cartPage.getCartEmptyMessage();
        Assert.assertEquals(actualMessage, expectedMessage, 
            "Cart empty message should match");
        logger.info("Step 6: Verified cart is empty with message: '{}'", actualMessage);

        logger.info("=== Test testSearchAndAddToCart completed successfully ===");
    }

    @Test(description = "Verify search results count is greater than zero")
    public void testSearchResultsCount() {
        logger.info("=== Starting testSearchResultsCount ===");

        HomePage homePage = new HomePage(driver);
        String searchTerm = ConfigReader.getProperty("search.term");
        SearchResultsPage searchResultsPage = homePage.search(searchTerm);

        int resultsCount = searchResultsPage.getSearchResultsCount();
        Assert.assertTrue(resultsCount > 0, "Search results count should be greater than zero");
        logger.info("Found {} search results", resultsCount);

        logger.info("=== Test testSearchResultsCount completed successfully ===");
    }
}

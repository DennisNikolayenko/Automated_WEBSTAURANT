package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {
    
    private final By addToCartButton = By.xpath("//input[@name='addToCartButton']");
    private final By viewCartButton = By.xpath("//a[@href='/viewcart.cfm']");
    
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        logger.info("ProductDetailsPage initialized");
        // Give page time to fully load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public ProductDetailsPage addToCart() {
        logger.info("Adding product to cart");
        try {
            // Scroll to the button first
            scrollToElement(addToCartButton);
            Thread.sleep(1000);
            click(addToCartButton);
            logger.info("Product added to cart successfully");
            // Wait for cart to update
            Thread.sleep(2000);
        } catch (Exception e) {
            logger.error("Failed to add to cart, trying JavaScript click", e);
            jsClick(addToCartButton);
        }
        return this;
    }

    public CartPage viewCart() {
        logger.info("Navigating to cart page");
        click(viewCartButton);
        waitForPageToLoad();
        return new CartPage(driver);
    }

    public CartPage addToCartAndView() {
        addToCart();
        return viewCart();
    }
}

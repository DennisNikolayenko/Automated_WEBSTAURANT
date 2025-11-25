package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    
    private final By emptyCartButton = By.xpath("//button[@class='emptyCartButton btn btn-mini btn-ui pull-right']");
    private final By confirmEmptyCartButton = By.xpath("//footer[@class='modal__footer']/button[text()='Empty']");
    private final By cartEmptyMessage = By.xpath("//p[@class='header-1']");
    
    public CartPage(WebDriver driver) {
        super(driver);
        logger.info("CartPage initialized");
    }

    public CartPage clickEmptyCart() {
        logger.info("Clicking empty cart button");
        click(emptyCartButton);
        logger.info("Empty cart button clicked");
        return this;
    }

    public CartPage confirmEmptyCart() {
        logger.info("Confirming empty cart action");
        click(confirmEmptyCartButton);
        logger.info("Cart emptied successfully");
        return this;
    }

    public CartPage emptyCart() {
        clickEmptyCart();
        confirmEmptyCart();
        return this;
    }

    public String getCartEmptyMessage() {
        logger.info("Retrieving cart empty message");
        String message = getText(cartEmptyMessage);
        logger.info("Cart message: {}", message);
        return message;
    }

    public boolean isCartEmpty() {
        logger.info("Verifying cart is empty");
        boolean isEmpty = isDisplayed(cartEmptyMessage);
        logger.info("Cart empty status: {}", isEmpty);
        return isEmpty;
    }

    public boolean verifyCartEmptyMessage(String expectedMessage) {
        logger.info("Verifying cart empty message matches: {}", expectedMessage);
        String actualMessage = getCartEmptyMessage();
        boolean matches = actualMessage.equals(expectedMessage);
        logger.info("Message match result: {}", matches);
        return matches;
    }
}

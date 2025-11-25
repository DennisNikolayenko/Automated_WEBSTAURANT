package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {
    private static final Logger logger = LogManager.getLogger(WaitHelper.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final FluentWait<WebDriver> fluentWait;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
        int explicitWait = ConfigReader.getExplicitWait();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
        
        this.fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(explicitWait))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public WebElement waitForElementVisible(By locator) {
        try {
            logger.debug("Waiting for element to be visible: {}", locator);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            logger.error("Element not visible within timeout: {}", locator);
            throw new TimeoutException("Element not visible: " + locator, e);
        }
    }

    public WebElement waitForElementClickable(By locator) {
        try {
            logger.debug("Waiting for element to be clickable: {}", locator);
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            logger.error("Element not clickable within timeout: {}", locator);
            throw new TimeoutException("Element not clickable: " + locator, e);
        }
    }

    public WebElement waitForElementPresent(By locator) {
        try {
            logger.debug("Waiting for element to be present: {}", locator);
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            logger.error("Element not present within timeout: {}", locator);
            throw new TimeoutException("Element not present: " + locator, e);
        }
    }

    public void waitForPageLoad() {
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
        logger.debug("Page loaded completely");
    }
}

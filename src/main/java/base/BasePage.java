package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import utils.WaitHelper;

import java.util.List;

public class BasePage {
    protected static final Logger logger = LogManager.getLogger(BasePage.class);
    protected WebDriver driver;
    protected WaitHelper waitHelper;
    protected Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    protected void click(By locator) {
        try {
            WebElement element = waitHelper.waitForElementClickable(locator);
            element.click();
            logger.info("Clicked on element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to click on element: {}", locator, e);
            throw new RuntimeException("Failed to click element: " + locator, e);
        }
    }

    protected void type(By locator, String text) {
        try {
            WebElement element = waitHelper.waitForElementVisible(locator);
            element.clear();
            element.sendKeys(text);
            logger.info("Typed '{}' into element: {}", text, locator);
        } catch (Exception e) {
            logger.error("Failed to type into element: {}", locator, e);
            throw new RuntimeException("Failed to type into element: " + locator, e);
        }
    }

    protected String getText(By locator) {
        try {
            WebElement element = waitHelper.waitForElementVisible(locator);
            String text = element.getText();
            logger.info("Retrieved text '{}' from element: {}", text, locator);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from element: {}", locator, e);
            throw new RuntimeException("Failed to get text from element: " + locator, e);
        }
    }

    protected boolean isDisplayed(By locator) {
        try {
            WebElement element = waitHelper.waitForElementVisible(locator);
            boolean displayed = element.isDisplayed();
            logger.info("Element {} is displayed: {}", locator, displayed);
            return displayed;
        } catch (Exception e) {
            logger.warn("Element not displayed: {}", locator);
            return false;
        }
    }

    protected List<WebElement> getElements(By locator) {
        try {
            waitHelper.waitForElementPresent(locator);
            List<WebElement> elements = driver.findElements(locator);
            logger.info("Found {} elements matching: {}", elements.size(), locator);
            return elements;
        } catch (Exception e) {
            logger.error("Failed to get elements: {}", locator, e);
            throw new RuntimeException("Failed to get elements: " + locator, e);
        }
    }

    protected void submit(By locator) {
        try {
            WebElement element = waitHelper.waitForElementVisible(locator);
            element.submit();
            logger.info("Submitted form via element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to submit form: {}", locator, e);
            throw new RuntimeException("Failed to submit form: " + locator, e);
        }
    }

    protected void scrollToElement(By locator) {
        try {
            WebElement element = waitHelper.waitForElementPresent(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("Scrolled to element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to scroll to element: {}", locator, e);
            throw new RuntimeException("Failed to scroll to element: " + locator, e);
        }
    }

    protected void jsClick(By locator) {
        try {
            WebElement element = waitHelper.waitForElementPresent(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            logger.info("Clicked element using JavaScript: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to click element using JavaScript: {}", locator, e);
            throw new RuntimeException("Failed to JS click element: " + locator, e);
        }
    }

    protected void waitForPageToLoad() {
        waitHelper.waitForPageLoad();
    }

    protected String getPageTitle() {
        String title = driver.getTitle();
        logger.info("Current page title: {}", title);
        return title;
    }
}

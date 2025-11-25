package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    
    private final By searchInput = By.id("searchval");
    
    public HomePage(WebDriver driver) {
        super(driver);
        logger.info("HomePage initialized");
    }

    public SearchResultsPage search(String searchTerm) {
        logger.info("Searching for: {}", searchTerm);
        type(searchInput, searchTerm);
        submit(searchInput);
        waitForPageToLoad();
        logger.info("Search completed");
        return new SearchResultsPage(driver);
    }

    public boolean isHomePageLoaded() {
        boolean isLoaded = isDisplayed(searchInput);
        logger.info("Home page loaded: {}", isLoaded);
        return isLoaded;
    }
}

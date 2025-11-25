package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage extends BasePage {
    
    private final By productDescriptions = By.xpath("//span[@data-testid='itemDescription']");
    
    public SearchResultsPage(WebDriver driver) {
        super(driver);
        logger.info("SearchResultsPage initialized");
    }

    public List<WebElement> getAllProductTitles() {
        logger.info("Retrieving all product titles");
        List<WebElement> products = getElements(productDescriptions);
        logger.info("Found {} products in search results", products.size());
        return products;
    }

    public boolean verifyAllProductsContainKeyword(String keyword) {
        logger.info("Verifying all products contain keyword: {}", keyword);
        List<WebElement> products = getAllProductTitles();
        
        boolean allContainKeyword = true;
        int count = 0;
        
        for (WebElement product : products) {
            String productText = product.getText();
            if (!productText.contains(keyword)) {
                logger.error("Product does not contain keyword '{}': {}", keyword, productText);
                allContainKeyword = false;
            } else {
                count++;
            }
        }
        
        logger.info("{} out of {} products contain keyword '{}'", count, products.size(), keyword);
        return allContainKeyword;
    }

    public ProductDetailsPage clickOnLastProduct() {
        logger.info("Clicking on last product in search results");
        List<WebElement> products = getAllProductTitles();
        
        if (products.isEmpty()) {
            logger.error("No products found in search results");
            throw new RuntimeException("No products found in search results");
        }
        
        WebElement lastProduct = products.get(products.size() - 1);
        String productName = lastProduct.getText();
        logger.info("Clicking on product: {}", productName);
        lastProduct.click();
        waitForPageToLoad();
        
        return new ProductDetailsPage(driver);
    }

    public int getSearchResultsCount() {
        int count = getAllProductTitles().size();
        logger.info("Total search results: {}", count);
        return count;
    }
}

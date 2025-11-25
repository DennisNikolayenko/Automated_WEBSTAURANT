# Quick Reference Cheat Sheet

## 🚀 Common Commands

### Maven Commands
```bash
# Install dependencies
mvn clean install

# Run all tests
mvn clean test

# Run specific test class
mvn clean test -Dtest=SearchAndCartTest

# Run specific test method
mvn clean test -Dtest=SearchAndCartTest#testSearchAndAddToCart

# Skip tests during build
mvn clean install -DskipTests

# Run with different browser
mvn clean test -Dbrowser=firefox

# Verbose output
mvn clean test -X
```

### Git Commands
```bash
# Clone repository
git clone <url>

# Create new branch
git checkout -b feature-branch

# Check status
git status

# Stage changes
git add .

# Commit
git commit -m "message"

# Push
git push origin branch-name

# Pull latest
git pull origin main

# Merge branch
git checkout main
git merge feature-branch
```

---

## 📁 File Locations

| What | Where |
|------|-------|
| Configuration | `src/main/resources/config.properties` |
| Log Config | `src/main/resources/log4j2.xml` |
| Test Logs | `logs/automation.log` |
| Test Reports | `target/surefire-reports/index.html` |
| Page Objects | `src/main/java/pages/` |
| Test Cases | `src/test/java/tests/` |
| Base Classes | `src/main/java/base/` |
| Utilities | `src/main/java/utils/` |

---

## 🔧 Configuration Properties

```properties
# URLs
base.url=https://webstaurantstore.com/

# Browser
browser=chrome|firefox|edge
headless=true|false
maximize.window=true|false

# Timeouts (seconds)
implicit.wait=10
explicit.wait=20
page.load.timeout=30

# Test Data
search.term=your search term
expected.search.keyword=keyword
```

---

## 📝 Code Templates

### Create New Test
```java
package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;

public class MyNewTest extends BaseTest {
    
    @Test(description = "Test description")
    public void testMyScenario() {
        HomePage homePage = new HomePage(driver);
        // Your test logic
    }
}
```

### Create New Page Object
```java
package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyNewPage extends BasePage {
    
    // Locators
    private final By elementLocator = By.id("element-id");
    
    public MyNewPage(WebDriver driver) {
        super(driver);
        logger.info("MyNewPage initialized");
    }
    
    // Page methods
    public void performAction() {
        click(elementLocator);
    }
    
    public String getText() {
        return getText(elementLocator);
    }
}
```

### Use Wait Helper
```java
// Wait for element visible
WebElement element = waitHelper.waitForElementVisible(locator);

// Wait for element clickable
WebElement element = waitHelper.waitForElementClickable(locator);

// Wait for element present
WebElement element = waitHelper.waitForElementPresent(locator);

// Wait with custom timeout
WebElement element = waitHelper.waitForElementVisible(locator, 30);
```

---

## 🎯 BasePage Methods

### Click & Type
```java
click(By.id("button-id"))
type(By.id("input-id"), "text")
submit(By.id("form-id"))
```

### Get Information
```java
String text = getText(By.id("element-id"))
boolean visible = isDisplayed(By.id("element-id"))
List<WebElement> elements = getElements(By.className("class"))
```

### Advanced Actions
```java
hoverOver(By.id("menu-id"))
scrollToElement(By.id("element-id"))
jsClick(By.id("element-id"))
selectByVisibleText(By.id("dropdown-id"), "Option")
```

---

## 🔍 Locator Strategies

### Priority Order (Best to Worst)
1. **ID** - `By.id("element-id")`
2. **Name** - `By.name("element-name")`
3. **CSS Selector** - `By.cssSelector(".class-name")`
4. **XPath** - `By.xpath("//tag[@attr='value']")`

### XPath Examples
```java
// By ID
By.xpath("//*[@id='element-id']")

// By class
By.xpath("//*[@class='class-name']")

// By text
By.xpath("//button[text()='Click Me']")

// By contains
By.xpath("//div[contains(@class, 'partial')]")

// Parent-child
By.xpath("//parent//child")
```

### CSS Selector Examples
```java
// By ID
By.cssSelector("#element-id")

// By class
By.cssSelector(".class-name")

// By attribute
By.cssSelector("[name='element-name']")

// Combination
By.cssSelector("input.class-name[type='text']")
```

---

## 📊 TestNG Annotations

```java
@BeforeMethod   // Runs before each test
@AfterMethod    // Runs after each test
@BeforeClass    // Runs once before all tests in class
@AfterClass     // Runs once after all tests in class
@Test           // Marks a test method
@Test(priority=1)  // Set execution order
@Test(enabled=false)  // Skip test
@Test(description="Test description")  // Add description
```

---

## 🧪 Assertions

```java
// TestNG Assertions
Assert.assertTrue(condition, "message")
Assert.assertFalse(condition, "message")
Assert.assertEquals(actual, expected, "message")
Assert.assertNotEquals(actual, expected, "message")
Assert.assertNull(object, "message")
Assert.assertNotNull(object, "message")
```

---

## 📈 Logging

```java
// Log levels
logger.info("Information message")
logger.debug("Debug message")
logger.warn("Warning message")
logger.error("Error message")
logger.fatal("Fatal message")

// With parameters
logger.info("User {} logged in", username)
logger.error("Failed to find element: {}", locator)
```

---

## 🔄 Page Navigation Flow

```java
// Typical flow
HomePage homePage = new HomePage(driver);
SearchResultsPage resultsPage = homePage.search("term");
ProductDetailsPage detailsPage = resultsPage.clickOnLastProduct();
CartPage cartPage = detailsPage.addToCartAndView();
cartPage.emptyCart();

// Fluent style
new HomePage(driver)
    .search("term")
    .clickOnLastProduct()
    .addToCartAndView()
    .emptyCart();
```

---

## 🐛 Debugging Tips

### View Element Properties
```java
WebElement element = driver.findElement(locator);
System.out.println("Tag: " + element.getTagName());
System.out.println("Text: " + element.getText());
System.out.println("Enabled: " + element.isEnabled());
System.out.println("Displayed: " + element.isDisplayed());
```

### Take Screenshot (Add to BasePage if needed)
```java
File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(screenshot, new File("screenshot.png"));
```

### Print Page Source
```java
System.out.println(driver.getPageSource());
```

### Breakpoint Debugging
```java
// Add breakpoint in IDE on this line
System.out.println("Debug point");  // Press F8 to step through
```

---

## ⚡ Performance Tips

1. **Use CSS over XPath** when possible (faster)
2. **Explicit waits** over Thread.sleep()
3. **Reuse WebDriver** instance within test
4. **Minimize page loads** - navigate efficiently
5. **Run tests in parallel** - modify testng.xml
6. **Use headless mode** for faster execution

---

## 🎨 Code Style

### Naming Conventions
```java
// Classes: PascalCase
public class HomePage

// Methods: camelCase
public void clickSubmitButton()

// Variables: camelCase
private WebDriver driver;

// Constants: UPPER_SNAKE_CASE
private static final String BASE_URL = "...";

// Locators: camelCase
private final By searchButton = By.id("search");
```

### Best Practices
- One assertion per test (when possible)
- Descriptive test names
- Add comments for complex logic
- Use meaningful variable names
- Keep methods small (<20 lines)
- Follow DRY principle

---

## 📞 Troubleshooting Quick Fixes

| Problem | Solution |
|---------|----------|
| Element not found | Check locator, add wait |
| Stale element | Re-find element, use fresh reference |
| Timeout | Increase wait time in config |
| Test fails randomly | Add explicit waits |
| Driver not found | Check internet, WebDriverManager downloads |
| Can't find config | Check file path in ConfigReader |
| Logs not generating | Check log4j2.xml location |
| Maven errors | Run `mvn clean install -U` |

---

## 🎓 Learning Path

1. ✅ Understand Page Object Model
2. ✅ Learn locator strategies
3. ✅ Master wait conditions
4. ✅ Practice with TestNG
5. ✅ Explore advanced Selenium
6. ⬜ Parallel execution
7. ⬜ Data-driven testing
8. ⬜ CI/CD integration

---

## 🔗 Useful Links

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/doc/)
- [Maven Repository](https://mvnrepository.com/)
- [XPath Cheatsheet](https://devhints.io/xpath)
- [CSS Selector Reference](https://www.w3schools.com/cssref/css_selectors.asp)

---

## 💾 Save This File!

Keep this cheat sheet handy for quick reference while working with the framework.

**Happy Testing! 🎉**
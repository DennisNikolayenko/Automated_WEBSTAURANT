# Selenium Test Automation Framework

A robust and maintainable Selenium WebDriver test automation framework built with Java, TestNG, and Page Object Model (POM) design pattern.

## 🚀 Features

- **Page Object Model (POM)** - Clean separation of test logic and page elements
- **Configurable** - Centralized configuration management via properties file
- **Robust Waits** - Custom wait helpers for reliable element interactions
- **Logging** - Comprehensive logging using Log4j2
- **Reusable Components** - Base classes for common functionality
- **TestNG Integration** - Powerful test execution and reporting
- **WebDriverManager** - Automatic driver management (no manual driver downloads)
- **Multiple Browser Support** - Chrome, Firefox, Edge

## 📁 Project Structure

```
selenium-framework/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/
│   │   │   │   ├── BasePage.java          # Common page object methods
│   │   │   │   └── BaseTest.java          # Test setup and teardown
│   │   │   ├── pages/
│   │   │   │   ├── HomePage.java          # Home page object
│   │   │   │   ├── SearchResultsPage.java # Search results page object
│   │   │   │   ├── ProductDetailsPage.java # Product details page object
│   │   │   │   └── CartPage.java          # Cart page object
│   │   │   └── utils/
│   │   │       ├── ConfigReader.java      # Configuration management
│   │   │       └── WaitHelper.java        # Wait utility methods
│   │   └── resources/
│   │       ├── config.properties          # Configuration file
│   │       └── log4j2.xml                 # Logging configuration
│   └── test/
│       └── java/
│           └── tests/
│               └── SearchAndCartTest.java # Test cases
├── pom.xml                                # Maven dependencies
├── testng.xml                             # TestNG suite configuration
└── README.md                              # This file
```

## 🛠️ Prerequisites

- Java 17 or higher
- Maven 3.6+
- Git

## ⚙️ Setup Instructions

### 1. Clone the Repository

```bash
git clone <your-repository-url>
cd selenium-framework
```

### 2. Install Dependencies

```bash
mvn clean install
```

This will download all required dependencies including Selenium, TestNG, WebDriverManager, and Log4j2.

### 3. Configuration

Edit `src/main/resources/config.properties` to customize:

```properties
# Application URL
base.url=https://webstaurantstore.com/

# Browser settings
browser=chrome
headless=false
maximize.window=true

# Timeout settings (in seconds)
implicit.wait=10
explicit.wait=20
page.load.timeout=30

# Test data
search.term=stainless work table
expected.search.keyword=Table
empty.cart.message=Your cart is empty.
```

## 🧪 Running Tests

### Run all tests via Maven

```bash
mvn clean test
```

### Run specific test class

```bash
mvn clean test -Dtest=SearchAndCartTest
```

### Run specific test method

```bash
mvn clean test -Dtest=SearchAndCartTest#testSearchAndAddToCart
```

### Run via TestNG XML

```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### Run in different browsers

```bash
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge
```

### Run in headless mode

Edit `config.properties` and set:
```properties
headless=true
```

## 📊 Test Reports

After test execution:
- **TestNG Reports**: Located in `target/surefire-reports/index.html`
- **Logs**: Located in `logs/automation.log`

## 🏗️ Framework Architecture

### Base Classes

**BasePage**: Contains common methods used across all page objects
- Element interactions (click, type, getText)
- Wait mechanisms
- Scroll, hover, JavaScript actions

**BaseTest**: Handles test lifecycle
- WebDriver initialization
- Browser configuration
- Setup and teardown

### Page Objects

Each page class represents a specific page or component:
- **HomePage**: Search functionality
- **SearchResultsPage**: Search results verification and product selection
- **ProductDetailsPage**: Add to cart functionality
- **CartPage**: Cart operations and verification

### Utilities

**ConfigReader**: Reads configuration from properties file
**WaitHelper**: Provides various wait strategies for element interactions

## ✅ Test Cases

### testSearchAndAddToCart
1. Navigate to home page
2. Search for "stainless work table"
3. Verify all results contain "Table" in title
4. Click on last product
5. Add to cart
6. Empty cart
7. Verify cart is empty

### testSearchResultsCount
1. Navigate to home page
2. Search for product
3. Verify search results count > 0

## 🔧 Customization

### Adding New Test Cases

1. Create test method in `tests` package
2. Extend `BaseTest` class
3. Use page objects for interactions
4. Add assertions using TestNG Assert

Example:
```java
@Test
public void testNewScenario() {
    HomePage homePage = new HomePage(driver);
    // Your test logic here
}
```

### Adding New Page Objects

1. Create class in `pages` package
2. Extend `BasePage`
3. Define locators as private fields
4. Implement page-specific methods

Example:
```java
public class NewPage extends BasePage {
    private final By elementLocator = By.id("element");
    
    public NewPage(WebDriver driver) {
        super(driver);
    }
    
    public void performAction() {
        click(elementLocator);
    }
}
```

## 🐛 Troubleshooting

### Tests fail with TimeoutException
- Increase wait times in `config.properties`
- Check if element locators are correct
- Verify page loads completely

### WebDriver initialization fails
- Ensure internet connection (WebDriverManager needs to download drivers)
- Check browser is installed
- Try different browser in config

### Elements not found
- Verify locators using browser DevTools
- Check if page structure has changed
- Ensure proper wait conditions are used

## 📈 Best Practices Implemented

1. **Page Object Model** - Separation of concerns
2. **DRY Principle** - Reusable components and methods
3. **Configuration Management** - Externalized test data
4. **Explicit Waits** - Reliable element interactions
5. **Logging** - Comprehensive test execution logs
6. **Fluent API** - Method chaining for readable tests
7. **Single Responsibility** - Each class has one purpose

## 🤝 Contributing

1. Create a feature branch
2. Make your changes
3. Add tests for new functionality
4. Ensure all tests pass
5. Submit pull request

## 📝 License

This project is open source and available under the MIT License.

## 📧 Contact

For questions or issues, please open an issue in the repository.

---

**Happy Testing! 🎉**
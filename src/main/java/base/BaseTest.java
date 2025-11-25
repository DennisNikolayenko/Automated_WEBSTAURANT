package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

import java.time.Duration;

public class BaseTest {
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        logger.info("=== Starting Test Setup ===");
        driver = initializeDriver();
        configureDriver();
        driver.get(ConfigReader.getBaseUrl());
        logger.info("Navigated to: {}", ConfigReader.getBaseUrl());
        logger.info("=== Test Setup Complete ===");
    }

    @AfterMethod
    public void tearDown() {
        logger.info("=== Starting Test Teardown ===");
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed successfully");
        }
        logger.info("=== Test Teardown Complete ===");
    }

    private WebDriver initializeDriver() {
        String browser = ConfigReader.getBrowser().toLowerCase();
        logger.info("Initializing {} browser", browser);

        WebDriver driver;
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (ConfigReader.isHeadless()) {
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");
                }
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                logger.warn("Invalid browser '{}', defaulting to Chrome", browser);
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }

        logger.info("WebDriver initialized successfully");
        return driver;
    }

    private void configureDriver() {
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        logger.info("Implicit wait set to {} seconds", ConfigReader.getImplicitWait());

        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(ConfigReader.getPageLoadTimeout()));
        logger.info("Page load timeout set to {} seconds", ConfigReader.getPageLoadTimeout());

        if (ConfigReader.getBooleanProperty("maximize.window")) {
            driver.manage().window().maximize();
            logger.info("Browser window maximized");
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}

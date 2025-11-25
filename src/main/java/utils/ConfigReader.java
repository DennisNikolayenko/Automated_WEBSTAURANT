package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    static {
        loadProperties();
    }

    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(input);
            logger.info("Configuration properties loaded successfully");
        } catch (IOException e) {
            logger.error("Failed to load configuration properties: {}", e.getMessage());
            throw new RuntimeException("Configuration file not found: " + CONFIG_FILE_PATH, e);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property '{}' not found in configuration", key);
        }
        return value;
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static int getIntProperty(String key) {
        String value = getProperty(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            logger.error("Invalid integer value for property '{}': {}", key, value);
            throw new RuntimeException("Invalid integer value for property: " + key, e);
        }
    }

    public static boolean getBooleanProperty(String key) {
        String value = getProperty(key);
        return Boolean.parseBoolean(value);
    }

    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }

    public static boolean isHeadless() {
        return getBooleanProperty("headless");
    }

    public static int getImplicitWait() {
        return getIntProperty("implicit.wait");
    }

    public static int getExplicitWait() {
        return getIntProperty("explicit.wait");
    }

    public static int getPageLoadTimeout() {
        return getIntProperty("page.load.timeout");
    }
}

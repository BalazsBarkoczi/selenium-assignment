package com.example.selenium.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Centralized WebDriver configuration and initialization.
 */
public class DriverConfig {

    /**
     * Initialize ChromeDriver with custom options.
     * 
     * @return configured WebDriver instance
     */
    public static WebDriver initializeDriver() {
        ChromeOptions options = new ChromeOptions();
        Map<String, String> env = System.getenv();
        String chromeBinary = env.get("CHROME_BINARY");
        boolean headless = Boolean.parseBoolean(env.getOrDefault("CHROME_HEADLESS", "false"));
        String remoteUrl = env.get("SELENIUM_REMOTE_URL");

        if (chromeBinary != null && !chromeBinary.isBlank()) {
            options.setBinary(chromeBinary);
        }

        if (headless) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        if (remoteUrl != null && !remoteUrl.isBlank()) {
            try {
                return new RemoteWebDriver(new URI(remoteUrl).toURL(), options);
            } catch (URISyntaxException | java.net.MalformedURLException exception) {
                throw new IllegalStateException("Invalid SELENIUM_REMOTE_URL: " + remoteUrl, exception);
            }
        }

        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }
    
    /**
     * Quit the WebDriver and clean up resources.
     * 
     * @param driver the WebDriver instance to quit
     */
    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}

package com.example.selenium.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverConfig {

    public static WebDriver initializeDriver() {
        Map<String, String> env = System.getenv();
        boolean headless = Boolean.parseBoolean(env.getOrDefault("CHROME_HEADLESS", "true"));
        String remoteUrl = env.get("SELENIUM_REMOTE_URL");

        String envBrowser = Config.get("BROWSER");
        if (envBrowser == null || envBrowser.isBlank()) {
            envBrowser = "chrome";
        }
        String browser = System.getProperty("browser", envBrowser).toLowerCase();
        // (Docker / Selenium Grid)
        if (remoteUrl != null && !remoteUrl.isBlank()) {
            try {
                if (browser.equals("firefox")) {
                    FirefoxOptions fOptions = new FirefoxOptions();
                    if (headless)
                        fOptions.addArguments("--headless");
                    return new RemoteWebDriver(new URI(remoteUrl).toURL(), fOptions);
                } else {
                    ChromeOptions cOptions = new ChromeOptions();
                    if (headless)
                        cOptions.addArguments("--headless=new");
                    return new RemoteWebDriver(new URI(remoteUrl).toURL(), cOptions);
                }
            } catch (URISyntaxException | java.net.MalformedURLException exception) {
                throw new IllegalStateException("Invalid SELENIUM_REMOTE_URL: " + remoteUrl, exception);
            }
        }

        // Local
        if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            if (headless) {
                firefoxOptions.addArguments("--headless");
            }
            return new FirefoxDriver(firefoxOptions);

        } else { // Default Chrome
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            if (headless) {
                chromeOptions.addArguments("--headless=new");
            }
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--window-size=1920,1080");

            String chromeBinary = env.get("CHROME_BINARY");
            if (chromeBinary != null && !chromeBinary.isBlank()) {
                chromeOptions.setBinary(chromeBinary);
            }

            return new ChromeDriver(chromeOptions);
        }
    }

    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}

package com.example.selenium.tests;

import com.example.selenium.config.DriverConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MultiplePageTest extends BaseTest {

    @BeforeEach
    public void setUp() {
        driver = DriverConfig.initializeDriver();
        login();
    }

    @AfterEach
    public void tearDown() {
        DriverConfig.quitDriver(driver);
    }

    @Test
    public void testTitlesOnMultiplePages() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<String> urls = List.of(
                "clients",
                "products",
                "invoices",
                "payments",
                "quotes"
        );

        for (String url : urls) {
            driver.get(BASE_URL + url);

            wait.until(d -> d.getTitle().toLowerCase().contains(url));

            String title = driver.getTitle();
            String currentUrl = driver.getCurrentUrl();
            Assertions.assertFalse(title == null || title.isBlank(), "Title should not be empty for URL: " + url);
            Assertions.assertTrue(
                    title.toLowerCase().contains(url),
                    "Title should contain route '" + url + "' but was: '" + title + "' (URL: " + currentUrl + ")"
            );
        }
    }
}
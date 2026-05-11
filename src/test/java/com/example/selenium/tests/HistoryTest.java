package com.example.selenium.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HistoryTest extends BaseTest {

    @BeforeEach
    public void setUp() {
        login();
    }


    @Test
    public void testBackAndForwardNavigation() {
        String firstRoute = "clients";
        String secondRoute = "products";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(BASE_URL + firstRoute);
        wait.until(webDriver -> webDriver.getTitle().toLowerCase().contains(firstRoute));
        String firstTitle = driver.getTitle().toLowerCase();
        Assertions.assertTrue(firstTitle.contains(firstRoute), "First page title should contain: " + firstRoute);

        driver.get(BASE_URL + secondRoute);
        wait.until(webDriver -> webDriver.getTitle().toLowerCase().contains(secondRoute));
        String secondTitle = driver.getTitle().toLowerCase();
        Assertions.assertTrue(secondTitle.contains(secondRoute), "Second page title should contain: " + secondRoute);

        driver.navigate().back();
        wait.until(webDriver -> webDriver.getTitle().toLowerCase().contains(firstRoute));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(firstRoute), "Back should return to: " + firstRoute);

        driver.navigate().forward();
        wait.until(webDriver -> webDriver.getTitle().toLowerCase().contains(secondRoute));
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains(secondRoute), "Forward should navigate to: " + secondRoute);
    }
}
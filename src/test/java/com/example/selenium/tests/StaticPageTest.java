package com.example.selenium.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class StaticPageTest extends BaseTest {

    @Test
    public void testStaticHomePageContentAndElements() {
        driver.get("https://invoiceninja.com/");

        String title = driver.getTitle();
        Assertions.assertFalse(title == null || title.isBlank(), "Home page title should not be empty");
        Assertions.assertTrue(title.toLowerCase().contains("invoice"), "Home page title should contain 'invoice'");

        Assertions.assertFalse(driver.findElements(By.tagName("h1")).isEmpty(), "Home page should contain at least one H1 element");

        String pageText = driver.findElement(By.tagName("body")).getText().toLowerCase();
        Assertions.assertTrue(pageText.contains("invoice"), "Home page body should contain invoice-related text");
    }
}
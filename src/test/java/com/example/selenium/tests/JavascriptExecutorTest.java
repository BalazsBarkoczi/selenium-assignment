package com.example.selenium.tests;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;


public class JavascriptExecutorTest extends BaseTest{


@Test
    public void testJavascriptExecutorScrollAndModify() {
        driver.get("https://www.invoiceninja.com/");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        js.executeScript("window.scrollTo({top: document.body.scrollHeight, behavior: 'instant'});");

        boolean hasScrolled = wait.until(d -> {
            Number offset = (Number) js.executeScript("return window.pageYOffset || document.documentElement.scrollTop;");
            return offset.longValue() > 0;
        });

        Assertions.assertTrue(hasScrolled, "Page should be scrolled down (offset > 0)");
    }
}

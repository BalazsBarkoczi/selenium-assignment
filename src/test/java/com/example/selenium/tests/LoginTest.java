package com.example.selenium.tests;

import com.example.selenium.config.DriverConfig;
import com.example.selenium.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginTest {
    
    private WebDriver driver;
    private LoginPage loginPage;
    

    @BeforeEach
    public void setUp() {
        driver = DriverConfig.initializeDriver();
        loginPage = new LoginPage(driver);
    }
    

    @AfterEach
    public void tearDown() {
        DriverConfig.quitDriver(driver);
    }
    

    @Test
    public void testPageTitleIsLoadedOnLoginPage() {
        loginPage.navigateToLoginPage();

        String title = driver.getTitle();
        Assertions.assertFalse(title == null || title.isBlank(), "Page title should not be empty");
        Assertions.assertTrue(title.toLowerCase().contains("invoice"), "Page title should contain 'invoice'");
    }

    @Test
    public void testLoginFormAndSubmit(){
        loginPage.navigateToLoginPage();

        assertTrue(loginPage.isLoginButtonVisible(), "Login button should be visible before login attempt");

        String testEmail = System.getenv("TEST_EMAIL");
        String testPassword = System.getenv("TEST_PASSWORD");

        loginPage.loginWithEmailAndPassword(testEmail, testPassword);
        Assertions.assertTrue(loginPage.waitForSuccessfulLogin(), "Login did not complete successfully");
    }
}

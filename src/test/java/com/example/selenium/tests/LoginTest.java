package com.example.selenium.tests;

import com.example.selenium.config.DriverConfig;
import com.example.selenium.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    public void testFillLoginFormAndSubmit() {
        loginPage.navigateToLoginPage();
        
        assertTrue(loginPage.isLoginButtonVisible(), "Login button should be visible before login attempt");
        
        String testEmail = "test@example.com";
        String testPassword = "testPassword123";
        
        loginPage.loginWithEmailAndPassword(testEmail, testPassword);

        String errorMessage = loginPage.waitForAnyErrorMessage();
        assertFalse(errorMessage.isEmpty(), "An error message should appear for invalid credentials");
    }
}

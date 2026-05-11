package com.example.selenium.tests;

import com.example.selenium.config.DriverConfig;
import com.example.selenium.pages.DashboardPage;
import com.example.selenium.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LogoutTest extends BaseTest {

    @BeforeEach
    public void setup() {
        driver = DriverConfig.initializeDriver();
    }

    @AfterEach
    public void tearDown() {
        DriverConfig.quitDriver(driver);
    }

@Test
    public void testLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();

        String testEmail = System.getenv("TEST_EMAIL");
        String testPassword = System.getenv("TEST_PASSWORD");

        loginPage.loginWithEmailAndPassword(testEmail, testPassword);
        Assertions.assertTrue(loginPage.waitForSuccessfulLogin(), "Login did not complete successfully");

        DashboardPage dashboardPage = new DashboardPage(driver);
        
        Assertions.assertTrue(dashboardPage.isDashboardLoaded(), "Dashboard failed to load after login");

        LoginPage returnedLoginPage = dashboardPage.logout();

        Assertions.assertTrue(
            returnedLoginPage.isLoginButtonVisible(),
            "Login page should be visible after logout"
        );
    }
}
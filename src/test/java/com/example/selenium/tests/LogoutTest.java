package com.example.selenium.tests;

import com.example.selenium.config.DriverConfig;
import com.example.selenium.pages.DashboardPage;
import com.example.selenium.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class LogoutTest {

    private WebDriver localDriver;

    @BeforeEach
    public void setup() {
        localDriver = DriverConfig.initializeDriver();
    }

    @AfterEach
    public void tearDown() {
        DriverConfig.quitDriver(localDriver);
    }

    @Test
    public void testLogout() {
        LoginPage loginPage = new LoginPage(localDriver);
        loginPage.navigateToLoginPage();

        String testEmail = System.getenv("TEST_EMAIL");
        String testPassword = System.getenv("TEST_PASSWORD");

        loginPage.loginWithEmailAndPassword(testEmail, testPassword);
        Assertions.assertTrue(loginPage.waitForSuccessfulLogin(), "Login did not complete successfully");

        DashboardPage dashboardPage = new DashboardPage(localDriver);

        Assertions.assertTrue(dashboardPage.isDashboardLoaded(), "Dashboard failed to load after login");

        LoginPage returnedLoginPage = dashboardPage.logout();

        Assertions.assertTrue(
                returnedLoginPage.isLoginButtonVisible(),
                "Login page should be visible after logout");
    }
}
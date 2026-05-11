package com.example.selenium.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

import com.example.selenium.config.Config;
import com.example.selenium.config.DriverConfig;
import com.example.selenium.pages.LoginPage;

public class BaseTest {
    protected static WebDriver driver;
    protected static final String BASE_URL = Config.get("BASE_URL");
    protected static boolean isLoggedIn = false;

    @BeforeAll
    public static void globalSetup() {
        if (driver == null) {
            driver = DriverConfig.initializeDriver();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if (driver != null) {
                    DriverConfig.quitDriver(driver);
                }
            }));
        }
    }


    protected void login() {
        if (isLoggedIn) {
            return;
        }

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();

        String testEmail = Config.get("TEST_EMAIL");
        String testPassword = Config.get("TEST_PASSWORD");

        loginPage.loginWithEmailAndPassword(testEmail, testPassword);
        Assertions.assertTrue(loginPage.waitForSuccessfulLogin(), "Login did not complete successfully");

        isLoggedIn = true;
    }
}

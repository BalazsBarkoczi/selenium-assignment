package com.example.selenium.tests;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import com.example.selenium.pages.LoginPage;

public class BaseTest {
    protected WebDriver driver;
    protected static final String BASE_URL = "https://app.invoicing.co/#/";

    protected void login(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();

        String testEmail = System.getenv("TEST_EMAIL");
        String testPassword = System.getenv("TEST_PASSWORD");

        loginPage.loginWithEmailAndPassword(testEmail, testPassword);
        Assertions.assertTrue(loginPage.waitForSuccessfulLogin(), "Login did not complete successfully");
    }
}

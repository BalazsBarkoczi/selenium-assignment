package com.example.selenium.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage {
    
    private static final By EMAIL_INPUT = By.cssSelector("input[type='email'], input[name='email'], input[placeholder*='email' i], input[placeholder*='username' i]");
    private static final By PASSWORD_INPUT = By.cssSelector("input[type='password']");
    private static final By LOGIN_BUTTON = By.xpath("//button[contains(text(), 'Login') or contains(text(), 'login') or contains(text(), 'Sign In')]");
    
    private static final String LOGIN_URL = "https://app.invoicing.co/#/login";
    

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    

    public LoginPage navigateToLoginPage() {
        navigateTo(LOGIN_URL);
        return this;
    }
    

    public LoginPage loginWithEmailAndPassword(String email, String password) {
        typeText(EMAIL_INPUT, email);
        typeText(PASSWORD_INPUT, password);
        clickElement(LOGIN_BUTTON);
        return this;
    }

    public boolean waitForSuccessfulLogin() {
        try {
            return wait.until(driver -> !driver.getCurrentUrl().contains("#/login"));
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isLoginButtonVisible() {
        try {
            waitAndReturnElement(LOGIN_BUTTON);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}

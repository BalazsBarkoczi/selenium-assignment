package com.example.selenium.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage extends BasePage {
    
    // Locators for login page elements
    private static final By EMAIL_INPUT = By.cssSelector("input[type='email'], input[name='email'], input[placeholder*='email' i], input[placeholder*='username' i]");
    private static final By PASSWORD_INPUT = By.cssSelector("input[type='password']");
    private static final By LOGIN_BUTTON = By.xpath("//button[contains(text(), 'Login') or contains(text(), 'login') or contains(text(), 'Sign In')]");
    private static final By ERROR_MESSAGE = By.xpath("//div[contains(@class, 'error') or contains(@class, 'alert')]");
    
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
    
    public boolean isLoginButtonVisible() {
        try {
            waitAndReturnElement(LOGIN_BUTTON);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    public String getErrorMessage() {
        try {
            return waitForAnyErrorMessage();
        } catch (Exception e) {
            return "";
        }
    }

    public String waitForAnyErrorMessage() {
        try {
            return wait.until(driver -> {
                List<WebElement> errorElements = driver.findElements(ERROR_MESSAGE);

                for (WebElement errorElement : errorElements) {
                    if (errorElement.isDisplayed()) {
                        String errorText = errorElement.getText().trim();
                        if (!errorText.isEmpty()) {
                            return errorText;
                        }
                    }
                }

                return null;
            });
        } catch (Exception e) {
            return "";
        }
    }
}

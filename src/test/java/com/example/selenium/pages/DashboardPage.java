package com.example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class DashboardPage extends BasePage {
    
    private static final By USER_MENU = By.xpath(
        "//button[@aria-haspopup='menu']"
    );
    private static final By LOGOUT_LINK = By.xpath(
        "//span[normalize-space()='Log Out']/ancestor::div[@role='menuitem']"
    );
    private static final By DASHBOARD_HEADER = By.xpath("//h1 | //h2 | //header | //div[contains(@class, 'header')]");
    
    private static final String DASHBOARD_URL = "https://app.invoicing.co/#/dashboard";


    public DashboardPage(WebDriver driver) {
        super(driver);
    }
    
    public DashboardPage navigateToDashboard() {
        navigateTo(DASHBOARD_URL);
        return this;
    }
    

    public LoginPage logout() {

        clickElement(USER_MENU);
        clickElement(LOGOUT_LINK);
        return new LoginPage(driver);
    }
    
    
    public boolean isDashboardLoaded() {
        try {
            waitAndReturnElement(DASHBOARD_HEADER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

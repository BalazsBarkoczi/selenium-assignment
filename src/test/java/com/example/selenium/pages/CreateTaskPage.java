package com.example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateTaskPage extends BasePage {

    public CreateTaskPage(WebDriver driver) {
        super(driver);
    }

    private static final By SAVE_BUTTON = By.xpath("//button[normalize-space(text())='Save']");
    private static final By SUCCESS_TOAST = By.xpath("//*[contains(text(), 'Successfully created task')]");

    public void fillTaskForm(){
        clickElement(SAVE_BUTTON);
    }

    public boolean isSuccessToastDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_TOAST));
            return true;
            
        } catch (TimeoutException e) {
            return false; 
        }
    }

}

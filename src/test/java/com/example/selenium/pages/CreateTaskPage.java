package com.example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateTaskPage extends BasePage {

    public CreateTaskPage(WebDriver driver) {
        super(driver);
    }

    private static final By RATE_INPUT = By.xpath("//label[normalize-space()='Rate']/following::input[@type='text'][1] | //section[.//label[normalize-space()='Rate']]//input[@type='text'][1]");
    private static final By TASK_NUMBER_INPUT = By.xpath("//label[normalize-space()='Task Number']/following::input[@type='text'][1] | //section[.//label[normalize-space()='Task Number']]//input[@type='text'][1]");
    private static final By DESCRIPTION_INPUT = By.xpath("//label[normalize-space()='Description']/following::textarea[1] | //section[.//label[normalize-space()='Description']]//textarea[1]");
    private static final By SAVE_BUTTON = By.xpath("//button[normalize-space(text())='Save']");
    private static final By SUCCESS_TOAST = By.xpath("//*[contains(text(), 'Successfully created task')]");

    public void fillTaskForm(){
        typeText(RATE_INPUT, "12345");
        //typeText(TASK_NUMBER_INPUT, "98765");
        typeText(DESCRIPTION_INPUT, "Task created by automated test");
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

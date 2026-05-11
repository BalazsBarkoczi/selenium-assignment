package com.example.selenium.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.selenium.config.DriverConfig;
import com.example.selenium.pages.CreateTaskPage;

public class CreateTaskTest extends BaseTest {

    @BeforeEach
    public void setup() {
        driver = DriverConfig.initializeDriver();
        login();
    }

    @AfterEach
    public void tearDown() {
        DriverConfig.quitDriver(driver);
    }

    @Test
    public void testCreateNewTask() {
        driver.get(BASE_URL + "tasks/create");

        CreateTaskPage taskPage = new CreateTaskPage(driver);

        taskPage.fillTaskForm();

        Assertions.assertTrue(taskPage.isSuccessToastDisplayed(),
                "'Successfully created task' message did not appear!");
    }
}

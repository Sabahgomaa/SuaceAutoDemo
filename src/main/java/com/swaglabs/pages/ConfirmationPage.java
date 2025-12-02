package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.Validation;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {
    //Locator
    private final By confirmationMessage = By.cssSelector(".complete-header");

    //variables
    private GUIDriver driver;
    //constructor

    public ConfirmationPage(GUIDriver driver) {
        this.driver = driver;
    }

    //actions

    @Step("get Confirmation Message : {0}")
    public String getConfirmationMessage() {
        return driver.elementActions().getText(confirmationMessage);
    }

    //validation
    public void assertConfirmationMessage(String expectedMessage) {
        String actualMessage = getConfirmationMessage();
        driver.validation().validateEquals(actualMessage, expectedMessage, "Confirmation message mismatch expected");
    }
}

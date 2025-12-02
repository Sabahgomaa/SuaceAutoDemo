package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverViewPage {
    //Locator
    private final By finishButton = By.id("finish");

    //code
    //variables
    private GUIDriver driver;
    //constructor

    public OverViewPage(GUIDriver driver) {
        this.driver = driver;
    }

    //actions

    @Step("click Finish Button")
    public ConfirmationPage clickFinishButton() {
        driver.elementActions().Click( finishButton);
        return new ConfirmationPage(driver);
    }
    //validation

}

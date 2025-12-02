package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Validation {
    private WebDriver driver;
    BrowserActions browserActions;

    public Validation(WebDriver driver) {
        this.driver = driver;
        browserActions = new BrowserActions(driver);
    }

    @Step("validateTrue")
    public void validateTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    @Step("validateFalse")
    public void validateFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
        ;
    }

    @Step("validateEquals")
    public void validateEquals(String actual, String expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    @Step("validateNotEquals")
    public void validateNotEquals(String actual, String expected, String message) {
        Assert.assertNotEquals(actual, expected, message);
    }

    @Step("validatePageUrl: {expected}")
    public void validatePageUrl(String expected) {
        Assert.assertEquals(browserActions.getCurrentUrl(), expected);
    }

    @Step("validatePageTitle: {expected}")
    public void validatePageTitle(String expected) {
        Assert.assertEquals(browserActions.getPageTitle(), expected);
    }

}

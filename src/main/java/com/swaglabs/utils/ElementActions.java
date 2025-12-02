package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementActions {
    private WebDriver driver;
    // Waits Bot
    private Waits waits;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
    }

    //Action-> find - sendKeys
    @Step("sending Data : {data} to the element : {locator}")
    public void Type(By locator, String data) {
        //code
        waits.waitForElementVisible(locator);
       scrollToElement(locator);
        findElement(locator).sendKeys(data);
        LogsUtil.info("Data entered:", data, "in the field :", locator.toString());
    }

    @Step("clicking on the element : {locator}")
    public void Click(By locator) {
        //code
        waits.waitForElementClickable(locator);
        scrollToElement(locator);
        findElement(locator).click();
        LogsUtil.info("Clicked on the element :", locator.toString());
    }

    @Step("Getting Text From the element : {locator}")
    public String getText(By locator) {
        //code
        waits.waitForElementVisible(locator);
        scrollToElement(locator);
        LogsUtil.info("Getting text from the element :", locator.toString(), " Text: ", findElement(locator).getText());

        return findElement(locator).getText();
    }

    //Find Element
    public WebElement findElement(By locator) {
        LogsUtil.info("Finding element :", locator.toString());

        return driver.findElement(locator);
    }

    public String getTextFromInput(By locator) {
        waits.waitForElementVisible(locator);
       scrollToElement(locator);
        LogsUtil.info("Getting text from the input field :", locator.toString(), " Text: ", findElement(locator).getText());
        return findElement(locator).getDomAttribute("value");
    }
    public void scrollToElement(By locator) {
        //Code
        LogsUtil.info("Scrolling to the element :", locator.toString());
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", findElement(locator));
    }
}

package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActions {
    private WebDriver driver;

    public BrowserActions(WebDriver driver) {
        this.driver=driver;
    }
    @Step("Navigating to URL: {url}")
    public  void NavigateToUrl( String url) {
        driver.get(url);
        LogsUtil.info("Navigated to URL: ",url);
    }

    //GetCurrentUrl
    @Step("Getting Current URL")
    public  String getCurrentUrl() {
        LogsUtil.info("Current URL: ", driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }
    //Get Page Title
    @Step("Getting Page Title")
    public  String getPageTitle(){
        LogsUtil.info("Page Title: ", driver.getTitle());
        return driver.getTitle();
    }
    //Refresh page
    @Step("Refreshing Page")
    public  void refreshPage(){
        LogsUtil.info("Refreshing the page: ");
        driver.navigate().refresh();
    }
    //closeBrowser
    @Step("Closing Page")
    public  void closeBrowser(){
        LogsUtil.info("Closing the browser");
        driver.quit();
    }

}

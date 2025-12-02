package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage {
    //Locators
    private final GUIDriver driver;
    private final By userName = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    //Constructors
    public LoginPage(GUIDriver driver) {
        this.driver = driver;
    }

    //Navigate To Login Page
    @Step("Navigate To Login Page")
    public void navigateToLoginPage() {
        driver.browserActions().NavigateToUrl(PropertiesUtils.getPropertyValue("baseURL"));
    }

    //Actions > wait - scroll - find - sendKeys
    @Step("Enter UserName: {0}")
    public LoginPage enterUserName(String userName) {
        driver.elementActions().Type(this.userName, userName);
        return this;
    }

    @Step("Enter Password: {0}")
    public LoginPage enterPassword(String password) {
        driver.elementActions().Type(this.password, password);
        return this;
    }

    @Step("click LoginBtn")
    public LoginPage clickLoginBtn() {
        driver.elementActions().Click(loginButton);
        return this;
    }

    @Step("Get Error Message")
    public String getErrorMessage() {
        return driver.elementActions().getText(errorMessage);
    }

    //Validation
    @Step("Assert Login Page Url")
    public LoginPage assertLoginPageUrl() {
        CustomSoftAssert.softAssert.assertEquals(driver.browserActions().getCurrentUrl(), PropertiesUtils.getPropertyValue("homeURL"), "Url is Not Expected");
        return this;
    }

    @Step("Assert Login Page Title")
    public LoginPage assertLoginPageTitle() {
        CustomSoftAssert.softAssert.assertEquals(driver.browserActions().getPageTitle(), PropertiesUtils.getPropertyValue("appTitle"), "Title is Not Expected");
        return this;
    }

    @Step("Assert Successful Login Soft")
    public HomePage assertSuccessfulLoginSoft() {
        assertLoginPageUrl().assertLoginPageTitle();
        return new HomePage(driver);
    }

    @Step("Assert Successful Login")
    public HomePage assertSuccessfulLogin() {
        driver.validation().validatePageUrl( PropertiesUtils.getPropertyValue("homeURL"));
        return new HomePage(driver);
    }

    @Step("Assert UnSuccessful Login")
    public LoginPage assertUnSuccessfulLogin() {
        driver.validation().validateEquals(getErrorMessage(), PropertiesUtils.getPropertyValue("errorMSG"), "Error Message is not as expected");
        return this;
    }
}

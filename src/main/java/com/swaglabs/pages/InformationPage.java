package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.CustomSoftAssert;
import com.swaglabs.utils.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InformationPage {
    //Locator
    private final By firstName= By.id("first-name");
    private final By lastName=By.id("last-name");
    private final By postalCode=By.id("postal-code");
    private final By continueButton=By.id("continue");

    //code
    //variables
    private GUIDriver driver;
    //constructor
    public InformationPage(GUIDriver driver) {
        this.driver = driver;
    }

    //actions
    @Step("Fill Information Form: FirstName: {0},  LastName: {0},  postalCode: {0}, ")
    public InformationPage fillInformationForm(String firstName,String lastName,String postalCode){
        driver.elementActions().Type(this.firstName,firstName);
        driver.elementActions().Type(this.lastName,lastName);
        driver.elementActions().Type(this.postalCode,postalCode);
        return this;
    }
    @Step("click Continue Button")
    public OverViewPage clickContinueButton(){
        driver.elementActions().Click(continueButton);
        return new OverViewPage(driver);
    }
    //validation
    @Step("assert Information Page")
    public InformationPage assertInformationPage(String firstName,String lastName,String postalCode){
        CustomSoftAssert.softAssert.assertEquals( driver.elementActions().getTextFromInput(this.firstName),firstName);
        CustomSoftAssert.softAssert.assertEquals( driver.elementActions().getTextFromInput(this.lastName),lastName);
        CustomSoftAssert.softAssert.assertEquals( driver.elementActions().getTextFromInput(this.postalCode),postalCode);
        return new InformationPage(driver);
    }
}

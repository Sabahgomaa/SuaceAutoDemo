package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.CustomSoftAssert;
import com.swaglabs.utils.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    //Locator
    private final By productName = By.cssSelector(".inventory_item_name");
    private final By productPrice = By.cssSelector(".inventory_item_price");
    private final By checkoutButton = By.cssSelector(".checkout_button");

    //code
    //variables
    private GUIDriver driver;
    //constructor
    public CartPage(GUIDriver driver) {
        this.driver = driver;
    }
    //actions
    @Step("Get Product Name")
    public String getProductName(){
        return driver.elementActions().getText(productName);
    }
    @Step("Get Product Price")

    public String getProductPrice(){
        return driver.elementActions().getText(productPrice);
    }
    @Step("click Checkout Button")
    public InformationPage clickCheckoutButton(){
        driver.elementActions().Click(checkoutButton);
        return new InformationPage(driver);
    }
    //validation
    @Step("assert Product Details")

    public CartPage assertProductDetails(String productName,String productPrice){
        String actualProductName=getProductName();
        String actualProductPrice=getProductPrice();
        CustomSoftAssert.softAssert.assertEquals(actualProductName,productName,"Product name mismatch");
        CustomSoftAssert.softAssert.assertEquals(actualProductPrice,productPrice,"Product price mismatch");
        return this;
    }
}

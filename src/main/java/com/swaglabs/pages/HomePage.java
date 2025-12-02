package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    //Code
    //Variables
    private GUIDriver driver;
    //Constructor

    public HomePage(GUIDriver driver) {
        this.driver = driver;
    }

    //Locators
    private final By cartIcon = By.cssSelector("[data-test='shopping-cart-link']");

    //Actions
    @Step("Navigate To HomePage")
    public HomePage navigateToHomePage() {
        driver.browserActions().NavigateToUrl(PropertiesUtils.getPropertyValue("homeURL"));
        return this;
    }
    @Step("Add Specific Product To Cart")
    public HomePage addSpecificProductToCart(String productName) {
        LogsUtil.info("Current URL: " + driver.browserActions().getCurrentUrl());

        LogsUtil.info("Adding " + productName + " to cart");
        By addToCartButton = By.xpath("//div[@class='inventory_item' and .//div[text()='" + productName + "']]//button");

        String beforeClick = driver.elementActions().getText(addToCartButton);
        LogsUtil.info("Button before click: " + beforeClick);
        driver.elementActions().Click( addToCartButton);
        String afterClick = driver.elementActions().getText( addToCartButton);
        LogsUtil.info("Button after click: " + afterClick);
        return this;
    }
//    public HomePage addSpecificProductToCart(String productName) {
//        LogsUtil.info("Current URL: " + driver.getCurrentUrl());
//
//        LogsUtil.info("Adding " + productName + " to cart");
//        // إنشاء الـ XPath الخاص بالمنتج
//        String xpath = "//div[@class='inventory_item' and .//div[text()='" + productName + "']]//button";
//
//        // الانتظار حتى يظهر الزر ويصبح قابل للضغط
//        Waits.waitForElementVisible(driver, By.xpath(xpath));
//        Waits.waitForElementPresent(driver, By.xpath(xpath));
//
//        // الحصول على العنصر
//        WebElement addToCartButton = driver.findElement(By.xpath(xpath));
//
//        // تمرير العنصر لـ JS إذا احتجنا
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
//
//        // الضغط على الزر
//        addToCartButton.click();
//
//        // التأكيد بعد الضغط (اختياري حسب نظامك)
//        String buttonTextAfterClick = addToCartButton.getText();
//        System.out.println("Button after click: " + buttonTextAfterClick);
//        return this;
//    }

    @Step("Click Cart Icon")
    public CartPage clickCartIcon() {
        driver.elementActions().Click( cartIcon);
        return new CartPage(driver);
    }

    //Validations
    @Step("Assert Product Added to Cart ")
    public HomePage assertProductAddedToCart(String productName) {
        By addToCartButton = By.xpath("//div[@class='inventory_item' and .//div[text()='" + productName + "']]//button");
//        Waits.waitForElementPresent(driver, addToCartButton);
        String actualValue = driver.elementActions().getText(addToCartButton);
        LogsUtil.info("Actual Value: " + actualValue);
        driver.validation().validateEquals(actualValue, "Remove", "Product not added to cart");
        LogsUtil.info(productName + " added to cart successfully");
        return this;
    }
}
//        By addToCartButton = RelativeLocator
//                .with(By.tagName("button"))
//                .below(By.xpath("//div[.='" + productName + "']"));

//        By addToCartButton= RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='" + productName + "']"));

//By addToCartButton = By.xpath(
//        "//div[@class='inventory_item'][.//div[@class='inventory_item_name' and text()='"
//                + productName + "']]//button"
//);

//By addToCartButton = By.xpath(
//        "//div[@class='inventory_item'][.//div[text()='" + productName + "']]//button"
//);

//        By addToCartButton = By.xpath(
//                "//div[@class='inventory_item'][.//div[text()='" + productName + "']]//button");
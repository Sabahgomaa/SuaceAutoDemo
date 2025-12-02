package com.swaglabs.drivers;

import com.swaglabs.utils.BrowserActions;
import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.LogsUtil;
import com.swaglabs.utils.Validation;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.testng.Assert.fail;

public class GUIDriver {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public GUIDriver(String browserName) {
        WebDriver driver = getDriver(browserName).startDriver();
        setDriver(driver);
    }

    private AbstractDriver getDriver(String browserName) {
        return switch (browserName.toLowerCase()) {
            case "chrome" -> new ChromeFactory();
            case "firefox" -> new FirefoxFactory();
            case "edge" -> new EdgeFactory();
            default -> throw new IllegalArgumentException();

        };
    }
    public static WebDriver createInstance(){
        return driverThreadLocal.get();
    }

    private void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public WebDriver get() {
        if (driverThreadLocal.get() == null) {
            LogsUtil.error("Driver is null");
            fail("Driver is null");
            return null;
        } else
            return driverThreadLocal.get();
    }
    public BrowserActions browserActions(){
        return new BrowserActions(get());
    }
    public ElementActions elementActions(){
        return new ElementActions(get());
    }
    public Validation validation(){
        return new Validation(get());
    }
}

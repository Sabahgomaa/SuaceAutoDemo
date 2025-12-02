package com.swaglabs.drivers;

import com.swaglabs.utils.PropertiesUtils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxFactory extends AbstractDriver implements WebDriverOptionsAbstract<FirefoxOptions>{
    @Override
    public FirefoxOptions getOption() {
        FirefoxOptions firefoxoptions=new FirefoxOptions();
        firefoxoptions.addArguments("--start-maximize");
        firefoxoptions.addArguments("" +
                "--disable-extensions");
        firefoxoptions.addArguments("--disable-infobars");
        firefoxoptions.addArguments("--disable-notifications");
        firefoxoptions.addArguments("--remote-allow-origins=*");
        firefoxoptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        firefoxoptions.setAcceptInsecureCerts(true);
        if (PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local")){
            firefoxoptions.addArguments("--headless");
        }
        return firefoxoptions;
    }

    @Override
    public WebDriver startDriver() {
        return new FirefoxDriver(getOption());
    }
}

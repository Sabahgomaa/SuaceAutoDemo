package com.swaglabs.drivers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.Map;

public class EdgeFactory extends AbstractDriver implements  WebDriverOptionsAbstract<EdgeOptions>{
    @Override
    public EdgeOptions getOption() {
        EdgeOptions edgeoptions=new EdgeOptions();
        edgeoptions.addArguments("--start-maximize");
        edgeoptions.addArguments("--disable-extensions");
        edgeoptions.addArguments("--disable-infobars");
        edgeoptions.addArguments("--disable-notifications");
        edgeoptions.addArguments("--remote-allow-origins=*");
        Map<String, Object> edgePrefs = Map.of(
                "profile.default_content_setting_values.notifications", 2, // تعطيل الإشعارات
                "credentials_enable_service", false,                      // تعطيل حفظ كلمات المرور
                "profile.password_manager_enabled", false,
                "autofill.profile_enabled", false
        );
        edgeoptions.setExperimentalOption("prefs",edgePrefs);
        edgeoptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//        if (PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local")){
//            edgeoptions.addArguments("--headless");
//        }
        return edgeoptions;
    }

    @Override
    public WebDriver startDriver() {
        return new EdgeDriver(getOption());
    }
}

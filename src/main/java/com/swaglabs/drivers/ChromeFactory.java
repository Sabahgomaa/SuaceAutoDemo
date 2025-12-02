package com.swaglabs.drivers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

public class ChromeFactory extends AbstractDriver implements WebDriverOptionsAbstract<ChromeOptions>{
    @Override
    public ChromeOptions getOption() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximize");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-cache");
        options.addArguments("--disable-application-cache");
        options.addArguments("--disable-save-password-bubble");
        Map<String, Object> prefs = Map.of(
                "profile.default_content_setting_values.notifications", 2, // تعطيل الإشعارات
                "credentials_enable_service", false,                      // تعطيل حفظ كلمات المرور
                "profile.password_manager_enabled", false,
                "autofill.profile_enabled", false
        );
        options.setExperimentalOption("prefs", prefs);
        options.setExperimentalOption("prefs",prefs);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//        if (PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local")){
//            options.addArguments("--headless");
//        }
        return options;
    }

    @Override
    public WebDriver startDriver() {
        return new ChromeDriver(getOption());
    }
}

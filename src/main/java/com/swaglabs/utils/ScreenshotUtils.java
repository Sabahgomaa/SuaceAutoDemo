package com.swaglabs.utils;

import com.swaglabs.drivers.GUIDriver;
import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

import static com.swaglabs.utils.TimeStampUtils.getTimeStamp;

public class ScreenshotUtils {
    public static final String SCREENSHOTS_PATH = "test-outputs/screenshots/";

    private ScreenshotUtils() {
        super();
    }

    public static void takeScreenShot(WebDriver driver, String screenshotName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName +"_"+ getTimeStamp()+".png");
            FileUtil.copyFile(screenshot, screenshotFile);
            AllureUtils.attacheScreenshotToAllure(screenshotName,screenshotFile.getPath());
        } catch (Exception e) {
            LogsUtil.error("Failed to take screenshot:"+e.getMessage());
        }
    }

}
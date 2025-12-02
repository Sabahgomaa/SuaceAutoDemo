package com.swaglabs.Listeners;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.*;
import org.apache.commons.io.FileUtils;
import org.testng.*;

import java.io.File;

import static com.swaglabs.utils.PropertiesUtils.loadProperties;

public class TestNGListeners implements IExecutionListener, ITestListener, IInvokedMethodListener {
    File allure_result = new File("test-outputs/allure-results/");
    File logs= new File("test-outputs/Logs");
    File ScreenShots= new File("test-outputs/screenshots");
    @Override
    public void onExecutionStart() {
        LogsUtil.info("Test Execution Started");
        loadProperties();
        FilesUtils.cleanDirectory( allure_result);
        FilesUtils.cleanDirectory(logs);
        FilesUtils.cleanDirectory(ScreenShots);
    }
    @Override
    public void onExecutionFinish(){
        LogsUtil.info("Test Execution finished");
        loadProperties();
        AllureUtils.generateAllureReport();
        String reportName=AllureUtils.renameReport();
        AllureUtils.openReport(reportName);
    }
    @Override
    public void  afterInvocation(IInvokedMethod method, ITestResult testResult){
        if(method.isTestMethod()){
            CustomSoftAssert.customAssertAll(testResult);
            switch (testResult.getStatus()){
                case ITestResult.SUCCESS -> ScreenshotUtils.takeScreenShot(GUIDriver.createInstance(),"Passed-"+testResult.getName());
                case ITestResult.FAILURE -> ScreenshotUtils.takeScreenShot(GUIDriver.createInstance(),"Failed-"+testResult.getName());
                case ITestResult.SKIP -> ScreenshotUtils.takeScreenShot(GUIDriver.createInstance(),"Skipped-"+testResult.getName());
            }
        }
            AllureUtils.attacheLogsToAllureReport();
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        LogsUtil.info("Test Case" + result.getName()+"Passed");
//        ScreenshotUtils.takeScreenShot("Passed-"+result.getName());
    }
    @Override
    public void onTestFailure(ITestResult result) {
        LogsUtil.info("Test Case" + result.getName()+"Failed");
//        ScreenshotUtils.takeScreenShot("Failed-"+result.getName());
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        LogsUtil.info("Test Case" + result.getName()+"Skipped");
//        ScreenshotUtils.takeScreenShot("Skipped-"+result.getName());
    }
}

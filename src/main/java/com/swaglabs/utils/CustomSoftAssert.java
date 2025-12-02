package com.swaglabs.utils;

import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

public class CustomSoftAssert extends SoftAssert {
    public static CustomSoftAssert softAssert=new CustomSoftAssert();
    public static void customAssertAll(ITestResult result){
        try{
            softAssert.assertAll("Custom Soft Assert");
        }catch (AssertionError e){
            LogsUtil.error("Custom Soft Assert Failed :" + e.getMessage());
            result.setStatus(ITestResult.FAILURE);
            result.setThrowable(e);
        }finally {
            reInitializeSoftAssert();
        }
    }
    private static void reInitializeSoftAssert(){
        softAssert=new CustomSoftAssert();
    }
}

package com.swaglabs.utils;

import io.qameta.allure.Allure;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtils {
    public static final String ALLURE_RESULTS_PATH = "test-outputs/allure-results";
    public static String REPORT_PATH = "test-outputs/allure-report";

    static String USER_Home = System.getProperty("user.home");
    static String ALLURE_PATH = USER_Home + File.separator + "scoop" + File.separator + "apps" + File.separator + "allure" + File.separator + "2.24.1"
            + File.separator + "bin" + File.separator + "allure.bat";

    private AllureUtils() {
        super();
    }

    public static void generateAllureReport() {
        if (PropertiesUtils.getPropertyValue("os.name").toLowerCase().contains("min")) {
            String WIN = ALLURE_PATH ;
            TerminalUtils.executeCommand(WIN, " generate", ALLURE_RESULTS_PATH, " -o", REPORT_PATH, " clean", " --single-file");
            LogsUtil.info("Allure report generate successfully on windows");
            //C:\Users\star\scoop\apps\allure\2.24.1\bin
        } else {
            TerminalUtils.executeCommand(ALLURE_PATH, "generate", ALLURE_RESULTS_PATH, "-o", REPORT_PATH, "clean", "--single-file");
            LogsUtil.info("Allure report generate successfully on " + PropertiesUtils.getPropertyValue("os.name"));
        }
    }

    public static void openReport(String fileName) {
        //allure serve //path
        String reportPath = REPORT_PATH +"/" + fileName;
        if (PropertiesUtils.getPropertyValue("openAllureAutomatically").equalsIgnoreCase("true")) {
            if (PropertiesUtils.getPropertyValue("os.name").toLowerCase().contains("win"))//windows
            {
                TerminalUtils.executeCommand("cmd", "/c", "start", reportPath);
            } else //Linux
            {
                TerminalUtils.executeCommand("open", reportPath);
            }
        }
    }
    public static String renameReport(){
        File newName=new File("Report_"+TimeStampUtils.getTimeStamp()+".html");
        File oldName=new File(REPORT_PATH+File.separator+"index.html");
        FilesUtils.renameFile(oldName,newName);
        return newName.getName();
    }


    public static void attacheLogsToAllureReport() {
        try {
            File logFile = FilesUtils.getLatestFile(LogsUtil.LOGS_PATH);
            assert logFile != null;
            if (!logFile.exists()) {
                LogsUtil.warn("Log file does not exist :" + LogsUtil.LOGS_PATH);
                return;
            }
            Allure.addAttachment("Logs.log", Files.readString(Path.of(logFile.getPath())));
            LogsUtil.info("Logs attached to Allure report");
        } catch (Exception e) {
            LogsUtil.error("Failed attach logs to Allure Report" + e.getLocalizedMessage());
        }
    }

    public static void attacheScreenshotToAllure(String screenshotName, String screenshotPath) {
        try {
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotPath)));
        } catch (Exception e) {
            LogsUtil.error("Failed to attach screenshot to Allure report :" + e.getMessage());
        }
    }

}
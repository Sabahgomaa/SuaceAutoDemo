package com.swaglabs.utils;

public class TerminalUtils {
    //allure , generate , //path, -o , //path , --single-file
    //allure
    public static void executeCommand(String... command){
        try {
            ProcessBuilder processBuilder=new ProcessBuilder(command);
            processBuilder.inheritIO();
            Process process=processBuilder.start();
            process.waitFor();
            LogsUtil.info("Command Executed Successfully :" +String.join("",command));
        }catch (Exception e){
            LogsUtil.error("Failed to execute command : " + e.getMessage());
        }
    }
}

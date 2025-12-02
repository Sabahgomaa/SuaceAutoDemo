package com.swaglabs.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class TimeStampUtils {
    // test_" timestamp "@gmail.com   test_2025-11-28-17-20@gmail.com
    public static void main(String[] args){
        System.out.println(getTimeStamp());
    }
    public static String getTimeStamp(){
        Date date=new Date();
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return formatter.format(date);
    }
}

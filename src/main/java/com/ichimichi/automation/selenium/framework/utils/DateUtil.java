package com.ichimichi.automation.selenium.framework.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");

    public static String getDateTimeStamp() {
        return LocalDateTime.now().format(formatter);
    }

}

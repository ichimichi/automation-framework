package com.ichimichi.automation.selenium.framework.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    public static ExtentReports report;

    public static ExtentReports getReportInstance(){

        if(report == null){
            String reportName = DateUtil.getDateTimeStamp() + ".html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/src/test/java/com/ichimichi/automation/selenium/framework/reports/" + reportName);
            report =  new ExtentReports();
            report.attachReporter(sparkReporter);

            report.setSystemInfo("OS", "Linux");

            sparkReporter.config().setDocumentTitle("Automation Report");
            sparkReporter.config().setReportName("Automation Report");
            sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
            sparkReporter.config().setTheme(Theme.STANDARD);
        }

        return report;
    }
}

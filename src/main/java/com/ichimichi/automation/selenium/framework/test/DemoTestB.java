package com.ichimichi.automation.selenium.framework.test;

import com.ichimichi.automation.selenium.framework.core.Core;
import com.ichimichi.automation.selenium.framework.utils.TestDataProvider;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class DemoTestB extends Core {

    @DataProvider
    public Object[][] getTestData() {
        return TestDataProvider.getTestData("TestData.xlsx", "Feature 1", "Demo Test B");
    }

    @Test(dataProvider = "getTestData")
    public void test(Hashtable<String,String> testData) {
        initializeBrowser(testData.get("browser"));
        createTest(testData.get("test_name"));
        openURL(testData.get("url"));

        elementEnterText("//div/input[@id='email' and @name='email']", testData.get("email"));
        elementEnterText("//div/input[@id='pass' and @name='pass']", testData.get("password"));
        logScreenShot();
    }

}

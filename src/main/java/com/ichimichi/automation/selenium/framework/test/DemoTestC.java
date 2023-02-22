package com.ichimichi.automation.selenium.framework.test;

import com.ichimichi.automation.selenium.framework.core.Core;
import com.ichimichi.automation.selenium.framework.utils.TestDataProvider;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class DemoTestB extends Core {

    @Test(enabled=false)
    public void test1(Hashtable<String,String> testData) {
    }

    @Test(dependsOnMethods = { "test1" })
    public void test2(Hashtable<String,String> testData) {
    }
        

}

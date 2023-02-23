package com.ichimichi.automation.selenium.framework.test;

import org.testng.annotations.Test;

public class DemoCTests {

    @Test(enabled=true)
    public void test1() {
    }

    @Test(dependsOnMethods = { "test1" }, enabled = true)
    public void test2() {
    }
        

}

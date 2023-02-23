package com.ichimichi.automation.selenium.framework.test;

import org.testng.annotations.Test;

import com.ichimichi.automation.selenium.framework.constants.BrowserType;
import com.ichimichi.automation.selenium.framework.core.Core;

/**
 * Hello world!
 */
public class DemoATests extends Core {

    @Test(enabled = false)
    public void test1() {
        initializeBrowser(BrowserType.CHROME);
        createTest("Test 1");
        openURL("https://www.facebook.com");

        elementEnterText("//div/input[@id='email' and @name='email']", "test@gmail.com");
        elementEnterText("//div/input[@id='pass' and @name='pass']", "passsword12345");
        logScreenShot();
    }

    @Test(enabled = false)
    public void test2() {
        initializeBrowser(BrowserType.CHROME);
        createTest("Test 2");
        openURL("https://www.facebook.com");

        elementEnterText("//div/input[@id='email' and @name='email']", "test@test.com");
        elementEnterText("//div/input[@id='pass' and @name='pass']", "passIs123456");
        logScreenShot();
    }
}

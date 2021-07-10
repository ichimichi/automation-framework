package com.ichimichi.automation.selenium.framework.test;

import com.ichimichi.automation.selenium.framework.constants.BrowserType;
import com.ichimichi.automation.selenium.framework.core.Core;
import org.testng.annotations.Test;

/**
 * Hello world!
 */
public class DemoTest extends Core {
    @Test
    public void main() {
        initializeBrowser(BrowserType.CHROME);
        openURL("https://www.facebook.com");
        elementEnterText("//div/input[@id='email' and @name='email']", "test@gmail.com");
        elementEnterText("//div/input[@id='pass' and @name='pass']", "passsword12345");
        logScreenShot();
    }
}

package com.ichimichi.automation.selenium.framework.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.ichimichi.automation.selenium.framework.constants.BrowserType;
import com.ichimichi.automation.selenium.framework.constants.Constant;
import com.ichimichi.automation.selenium.framework.constants.LocatorType;
import com.ichimichi.automation.selenium.framework.constants.ScreenshotType;
import com.ichimichi.automation.selenium.framework.utils.DateUtil;
import com.ichimichi.automation.selenium.framework.utils.ExtentReportManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Core {

    public WebDriver driver;
    public Properties prop;
    public ExtentReports report = ExtentReportManager.getReportInstance();
    public ExtentTest logger;

    SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    public void beforeTest() {
    }

    @BeforeMethod
    public void beforeMethod() {
    }

    @AfterMethod
    public void afterMethod() {
        softAssert.assertAll();
        driver.quit();
    }

    @AfterTest
    public void afterTest() {
        report.flush();

    }

    public void initializeBrowser() {
        initializeBrowser(BrowserType.CHROME);
    }

    public void logScreenShot(String details) {
        logScreenShot(ScreenshotType.BASE64, details);
    }

    public void logScreenShot() {
        logScreenShot(ScreenshotType.BASE64, Constant.EMPTY_STRING);
    }

    public void logFailScreenShot(String details) {
        logFailScreenShot(ScreenshotType.BASE64, details);
    }

    public void logFailScreenShot() {
        logFailScreenShot(ScreenshotType.BASE64, Constant.EMPTY_STRING);
    }

    public WebElement getElement(String locator) {
        return getElement(locator, LocatorType.XPATH);
    }

    public List<WebElement> getElements(String locator) {
        return getElements(locator, LocatorType.XPATH);
    }

    public void elementEnterText(String locator, String data) {
        elementEnterText(locator, LocatorType.XPATH, data);
    }

    public void elementClick(String locator) {
        elementClick(locator, LocatorType.XPATH);
    }

    public boolean isElementPresent(String locator) {
        return isElementPresent(locator, LocatorType.XPATH);
    }

    public boolean isElementSelected(String locator) {
        return isElementSelected(locator, LocatorType.XPATH);
    }

    public boolean isElementEnabled(String locator) {
        return isElementEnabled(locator, LocatorType.XPATH);
    }

    public void assertTrue(boolean flag) {
        try {
            softAssert.assertTrue(flag);
        } catch (Exception e) {
            reportFail(e.getMessage());
        }
    }

    public void assertFalse(boolean flag) {
        try {
            softAssert.assertFalse(flag);
        } catch (Exception e) {
            reportFail(e.getMessage());
        }
    }

    public void assertEquals(String actual, String expected) {
        try {
            softAssert.assertEquals(actual, expected);
        } catch (Exception e) {
            reportFail(e.getMessage());
        }
    }

    public void createTest(String testName) {
        logger = report.createTest(testName);
    }

    public void reportPass(String reportString) {
        logger.pass(reportString);
    }

    public void reportFail(String reportString) {
        logger.fail(reportString);
        logFailScreenShot();
        Assert.fail(reportString);
    }

    public void initializeBrowser(String browserName) {
        switch (browserName.toLowerCase(Locale.ROOT)) {
            case "chrome":
                initializeBrowser(BrowserType.CHROME);
                break;
            case "mozilla":
                initializeBrowser(BrowserType.MOZILLA);
                break;
            case "ie":
                initializeBrowser(BrowserType.IE);
                break;
        }
    }

    public void initializeBrowser(BrowserType type) {

        try {
            switch (type) {
                case CHROME:
                    System.setProperty("webdriver.chrome.driver",
                            System.getProperty("user.dir") + "/src/test/java/com/ichimichi/automation/selenium/framework/resources/drivers/chromedriver");
                    driver = new ChromeDriver();
                    break;
                case MOZILLA:
                    System.setProperty("webdriver.gecko.driver",
                            System.getProperty("user.dir") + "/src/test/java/com/ichimichi/automation/selenium/framework/resources/drivers/geckodriver");
                    driver = new FirefoxDriver();
                    break;
                case IE:
                    System.setProperty("webdriver.ie.driver",
                            System.getProperty("user.dir") + "/src/test/java/com/ichimichi/automation/selenium/framework/resources/drivers/IEDriverServer.exe");
                    driver = new InternetExplorerDriver();
                    break;
            }
        } catch (Exception e) {
            reportFail(e.getMessage());
        }

        driver.manage().timeouts().implicitlyWait(Constant.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Constant.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();


        if (prop == null) {
            prop = new Properties();

            try {
                FileInputStream file = new FileInputStream(System.getProperty("user.dir")
                        + "/src/test/java/com/ichimichi/automation/selenium/framework/config/projectConfig.properties");
                prop.load(file);
            } catch (Exception e) {
                reportFail(e.getMessage());
                e.printStackTrace();
            }

        }

    }

    public void openURL(String url) {
        try {
            driver.get(url);
            reportPass(url + " loaded Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            reportFail(e.getMessage());
        }

    }

    public void tearDown() {
        driver.close();

    }

    public void quitBrowser() {
        driver.quit();

    }

    public void elementEnterText(String locator, LocatorType type, String data) {
        try {
            getElement(locator, type).sendKeys(data);
        } catch (Exception e) {
            reportFail(e.getMessage());
        }
    }

    public void elementClick(String locator, LocatorType type) {
        try {
            getElement(locator, type).click();
        } catch (Exception e) {
            reportFail(e.getMessage());
        }
    }

    public void elementClickDropdownValue(String locator, LocatorType type, String Value) {
        try {
            List<WebElement> listElement = getElements(locator, type);
            for (WebElement listItem : listElement) {
                String prefix = listItem.getText();
                if (prefix.contains(Value)) {
                    waitForPageLoad();
                    listItem.click();
                }
            }
        } catch (Exception e) {
            reportFail(e.getMessage());
        }
    }

    public WebElement getElement(String locator, LocatorType type) {
        WebElement element = null;
        try {
            switch (type) {
                case ID:
                    element = driver.findElement(By.id(locator));
                    break;
                case XPATH:
                    element = driver.findElement(By.xpath(locator));
                    break;
                case CLASS_NAME:
                    element = driver.findElement(By.className(locator));
                    break;
                case CSS:
                    element = driver.findElement(By.cssSelector(locator));
                    break;
                case LINK_TEXT:
                    element = driver.findElement(By.linkText(locator));
                    break;
                case PARTIAL_LINK_TEXT:
                    element = driver.findElement(By.partialLinkText(locator));
                    break;
                case NAME:
                    element = driver.findElement(By.name(locator));
                    break;
                default:
                    reportFail("Failing the Testcase, Invalid Locator " + locator);
            }
        } catch (Exception e) {
            reportFail(e.getMessage());
            e.printStackTrace();
        }

        return element;
    }


    public List<WebElement> getElements(String locator, LocatorType type) {
        List<WebElement> elements = null;
        try {
            switch (type) {
                case ID:
                    elements = driver.findElements(By.id(locator));
                    break;
                case XPATH:
                    elements = driver.findElements(By.xpath(locator));
                    break;
                case CLASS_NAME:
                    elements = driver.findElements(By.className(locator));
                    break;
                case CSS:
                    elements = driver.findElements(By.cssSelector(locator));
                    break;
                case LINK_TEXT:
                    elements = driver.findElements(By.linkText(locator));
                    break;
                case PARTIAL_LINK_TEXT:
                    elements = driver.findElements(By.partialLinkText(locator));
                    break;
                case NAME:
                    elements = driver.findElements(By.name(locator));
                    break;
                default:
                    reportFail("Failing the Testcase, Invalid Locator " + locator);
            }
        } catch (Exception e) {
            reportFail(e.getMessage());
            e.printStackTrace();
        }

        return elements;
    }


    public boolean isElementPresent(String locator, LocatorType type) {
        try {
            if (getElement(locator, type).isDisplayed()) {
                return true;
            }
        } catch (Exception e) {
            reportFail(e.getMessage());
        }
        return false;
    }

    public boolean isElementSelected(String locator, LocatorType type) {
        try {
            if (getElement(locator, type).isSelected()) {
                return true;
            }
        } catch (Exception e) {
            reportFail(e.getMessage());
        }
        return false;
    }

    public boolean isElementEnabled(String locator, LocatorType type) {
        try {
            if (getElement(locator, type).isEnabled()) {
                return true;
            }
        } catch (Exception e) {
            reportFail(e.getMessage());
        }
        return false;
    }

    private String getScreenShotAsPath() {
        TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
        File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
        String timestamp = DateUtil.getDateTimeStamp();
        String screenShotFileName = System.getProperty("user.dir") + "/src/test/java/com/ichimichi/automation/selenium/framework/reports/screenshots/" + timestamp + ".png";
        String imgSrcAttribute = "screenshots/" + timestamp + ".png";
        File targetFile = new File(screenShotFileName);
        try {
            FileUtils.copyFile(sourceFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgSrcAttribute;
    }

    private String getScreenShotAsBase64() {
        TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
        return takeScreenShot.getScreenshotAs(OutputType.BASE64);

    }

    public void logScreenShot(ScreenshotType type, String details) {
        try {
            switch (type) {
                case FILE:
                    logger.pass(details,
                            MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotAsPath()).build());
                    break;
                case BASE64:

                    logger.pass(details,
                            MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenShotAsBase64()).build());
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logFailScreenShot(ScreenshotType type, String details) {
        try {
            switch (type) {
                case FILE:
                    logger.fail(details,
                            MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotAsPath()).build());
                    break;
                case BASE64:

                    logger.fail(details,
                            MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenShotAsBase64()).build());
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waitForPageLoad() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int i = 0;
        while (i != 180) {
            String pageState = (String) js.executeScript("return document.readyState;");
            if (pageState.equals("complete")) {
                break;
            } else {
                wait(1);
            }
            i++;
        }

        wait(2);

        i = 0;
        while (i != 180) {
            Boolean jsState = (Boolean) js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
            if (jsState) {
                break;
            } else {
                wait(1);
            }
            i++;
        }
    }

    public void wait(int i) {
        try {
            Thread.sleep(i * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void switchToDefaultFrame() {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            reportFail(e.getMessage());
        }
    }

    public void switchToFrame(String locator) {
        try {
            driver.switchTo().frame(locator);
        } catch (Exception e) {
            reportFail(e.getMessage());
        }
    }

    public void switchToFrameByIndex(int index) {
        try {
            driver.switchTo().frame(index);
        } catch (Exception e) {
            reportFail(e.getMessage());
        }
    }


}
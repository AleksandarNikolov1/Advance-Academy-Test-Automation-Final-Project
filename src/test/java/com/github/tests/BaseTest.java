package com.github.tests;

import com.github.drivers.DriverFactory;
import configurations.ConfigurationManager;
import configurations.ScreenshotManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {

    @Step("Precondition - load https://github.com/ in browser")
    @BeforeClass
    public void setup() {
        setupBrowserDriver();
        loadUrl();
    }

    private void setupBrowserDriver() {
        int implicitWait = ConfigurationManager.getImplicitWait();
        String browser = ConfigurationManager.getBrowser();
        DriverFactory.setTlDriver(browser, implicitWait);
    }

    private void loadUrl() {
        String url = ConfigurationManager.getUrl();
        WebDriver driver = DriverFactory.getDriver();
        driver.get(url);
    }

    @AfterMethod
    public void makeScreenshotWhenFail(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();

        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotManager.takeScreenshot(driver, result.getName());
        }
    }


    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
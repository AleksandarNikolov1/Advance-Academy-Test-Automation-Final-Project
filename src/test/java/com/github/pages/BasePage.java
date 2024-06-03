package com.github.pages;

import com.github.drivers.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public abstract class BasePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public BasePage() {
        driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForTextToBePresentInElement(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void scrollIntoViewWithJS(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public String getTabTitle(){
        return driver.getTitle();
    }

    public void refresh(){
        driver.navigate().refresh();
    }

    public void switchToFrame(WebElement iframe){
        driver.switchTo().frame(iframe);
        PageFactory.initElements(driver, iframe);
    }

    public void switchToDefaultContent(){
        driver.switchTo().defaultContent();
    }
}

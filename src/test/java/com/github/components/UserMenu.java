package com.github.components;

import com.github.pages.BasePage;
import com.github.pages.RepositoriesPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserMenu extends BasePage {

    @FindBy(xpath = "//a[contains(@data-analytics-event, 'YOUR_REPOSITORIES')]")
    WebElement yourRepositoriesOption;

    @Step("From user menu select \"Your repositories\"")
    public void clickYourRepositoriesOption() throws InterruptedException {
        // waitForElementToBeClickable(yourRepositoriesOption);
        Thread.sleep(3000);
        yourRepositoriesOption.click();
    }

    @Step("Open your repositories page")
    public RepositoriesPage loadRepositoriesPage() throws InterruptedException {
        clickYourRepositoriesOption();
        return new RepositoriesPage();
    }
}

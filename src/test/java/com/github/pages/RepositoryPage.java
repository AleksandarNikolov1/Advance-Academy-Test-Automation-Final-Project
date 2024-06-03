package com.github.pages;

import com.github.components.Header;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RepositoryPage extends BasePage {

    @FindBy(xpath = "//a[@class='color-fg-default']")
    WebElement repositoryName;

    public String getRepositoryName(){
        waitForElementToBeVisible(repositoryName);
        return repositoryName.getText();
    }

    @Step("Open repository settings clicking \"Settings\"")
    public RepositorySettingsPage loadRepositorySettingsPage(){
        Header header = new Header();
        header.clickSettingButton();
        return new RepositorySettingsPage();
    }

    @Step("From user menu select \"Your repositories\"")
    public RepositoriesPage loadRepositoriesPage() throws InterruptedException {
        Header header = new Header();
        return header.loadUserMenu().loadRepositoriesPage();
    }
}

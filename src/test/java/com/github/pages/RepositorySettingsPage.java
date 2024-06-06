package com.github.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RepositorySettingsPage extends BasePage {

    @FindBy(id = "rename-field")
    WebElement renameTextArea;

    @FindBy(xpath = "//button[@data-disable-with='Renaming...']")
    WebElement renameButton;

    @FindBy(id = "dialog-show-repo-delete-menu-dialog")
    WebElement deleteRepositoryButton;

    @FindBy(id = "repo-delete-proceed-button")
    WebElement confirmDeleteRepositoryButton;

    @FindBy(id = "verification_field")
    WebElement verificationFieldTextArea;

    @Step("Set new repository name: {newRepoName}")
    public void setRepositoryName(String newRepoName){
        waitForElementToBeVisible(renameTextArea);
        renameTextArea.clear();
        renameTextArea.sendKeys(newRepoName);
    }

    @Step("Click \"Rename\" button")
    public void clickRenameButton(){
        waitForElementToBeClickable(renameButton);
        renameButton.click();
    }

    @Step("Edit repository")
    public void editRepositoryName(String name){
        setRepositoryName(name);
        clickRenameButton();
    }

    @Step("Click \"Delete this repository\" button")
    public void clickDeleteRepositoryButton(){
        waitForElementToBeClickable(deleteRepositoryButton);
        deleteRepositoryButton.click();
    }

    @Step("Confirm deletion - click confirmation button")
    public void clickConfirmDeleteRepositoryButton(){
        waitForElementToBeClickable(confirmDeleteRepositoryButton);
        confirmDeleteRepositoryButton.click();
    }

    @Step("Confirm deletion - fill verification field by: {username}, {repo}")
    public void setVerificationFieldTextArea(String username, String repo){
        String confirmText = username.concat("/").concat(repo);
        verificationFieldTextArea.sendKeys(confirmText);
    }

    @Step("Delete repository")
    public void deleteRepository(String username, String repo){
        clickDeleteRepositoryButton();
        clickConfirmDeleteRepositoryButton();
        clickConfirmDeleteRepositoryButton();
        setVerificationFieldTextArea(username, repo);
        clickConfirmDeleteRepositoryButton();
    }
}

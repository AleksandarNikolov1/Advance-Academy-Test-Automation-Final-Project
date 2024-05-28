package com.github.pages;

import com.github.components.Header;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RepositoryPage extends BasePage {

    @FindBy(xpath = "//h1[contains(@class, 'Heading')]")
    WebElement pageTitle;

    @FindBy(xpath = "//input[contains(@aria-describedby, 'RepoNameInput')]")
    WebElement repositoryNameTextArea;

    @FindBy(xpath = "/html/body/div[1]/div[6]/main/react-app/div/form/div[5]/button")
    WebElement createRepositoryButton;

    @FindBy(xpath = "//a[@class='color-fg-default']")
    WebElement repositoryName;

    @FindBy(id = "rename-field")
    WebElement renameTextArea;

    @FindBy(xpath = "//button[@data-disable-with='Renaming...']")
    WebElement confirmRenameButton;

    @FindBy(id = "dialog-show-repo-delete-menu-dialog")
    WebElement deleteRepositoryButton;

    @FindBy(id = "repo-delete-proceed-button")
    WebElement confirmDeleteRepositoryButton;

    @FindBy(id = "verification_field")
    WebElement verificationFieldTextArea;

    public String getPageTitle(){
        return pageTitle.getText();
    }

    public void setRepositoryName(String repositoryName){
        waitForElementToBeVisible(repositoryNameTextArea);
        repositoryNameTextArea.sendKeys(repositoryName);
    }

    public void clickCreateRepositoryButton() throws InterruptedException {
        scrollIntoViewWithJS(createRepositoryButton);
        Thread.sleep(3000);
        createRepositoryButton.click();
    }

    public void createRepository(String repoName) throws InterruptedException {
        setRepositoryName(repoName);
        clickCreateRepositoryButton();
    }


    public String getRepositoryName(){
        waitForElementToBeVisible(repositoryName);
        return repositoryName.getText();
    }

    private Header getHeader(){
        return new Header();
    }

    public void switchToSettings(){
        Header header = new Header();
        header.clickSettingButton();
    }

    public void editRepositoryName(String newRepoName){
        renameTextArea.clear();
        renameTextArea.sendKeys(newRepoName);
    }

    public void clickConfirmRenameButton(){
        waitForElementToBeClickable(confirmRenameButton);
        confirmRenameButton.click();
    }

    public void clickDeleteRepositoryButton(){
        waitForElementToBeClickable(deleteRepositoryButton);
        deleteRepositoryButton.click();
    }

    public void clickConfirmDeleteRepositoryButton(){
        waitForElementToBeClickable(confirmDeleteRepositoryButton);
        confirmDeleteRepositoryButton.click();
    }

    public void setVerificationFieldTextArea(String username, String repo){
        String confirmText = username.concat("/").concat(repo);
        verificationFieldTextArea.sendKeys(confirmText);
    }

    public void deleteRepository(String username, String repo){
        clickDeleteRepositoryButton();
        clickConfirmDeleteRepositoryButton();
        clickConfirmDeleteRepositoryButton();
        setVerificationFieldTextArea(username, repo);
        clickConfirmDeleteRepositoryButton();
    }
}

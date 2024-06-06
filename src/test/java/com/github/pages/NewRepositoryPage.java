package com.github.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewRepositoryPage extends BasePage{

    @FindBy(xpath = "//h1[contains(@class, 'Heading')]")
    WebElement pageTitle;

    @FindBy(xpath = "//input[contains(@aria-describedby, 'RepoNameInput')]")
    WebElement repositoryNameTextArea;

    @FindBy(css = "input[name='Description']")
    WebElement repositoryDescriptionTextArea;

    @FindBy(xpath = "//span[contains(text(), 'Create repository')]")
    WebElement createRepositoryButton;

    public String getPageTitle(){
        waitForElementToBeVisible(pageTitle);
        return pageTitle.getText();
    }

    @Step("Set repository name")
    public void setRepositoryName(String name){
        waitForElementToBeVisible(repositoryNameTextArea);
        repositoryNameTextArea.sendKeys(name);
    }

    @Step("Set repository description")
    public void setRepositoryDescription(String description){
        waitForElementToBeVisible(repositoryDescriptionTextArea);
        repositoryDescriptionTextArea.sendKeys(description);
    }

    @Step("Click \"Create\" repository button")
    public void clickCreateRepositoryButton() throws InterruptedException {
        scrollIntoViewWithJS(createRepositoryButton);
        Thread.sleep(3000);
        createRepositoryButton.click();
    }

    @Step("Create repository")
    public RepositoryPage createRepository(String name) throws InterruptedException {
        setRepositoryName(name);
        clickCreateRepositoryButton();
        return new RepositoryPage();
    }

    @Step("Create repository")
    public RepositoryPage createRepository(String name, String description) throws InterruptedException {
        setRepositoryName(name);
        setRepositoryDescription(description);
        clickCreateRepositoryButton();
        return new RepositoryPage();
    }
}

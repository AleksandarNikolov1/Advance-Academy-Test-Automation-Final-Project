package com.github.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class RepositoriesPage extends BasePage{

    @FindBy(xpath = "//a[@itemprop='name codeRepository']")
    List<WebElement> repositories;

    @FindBy(xpath = "//a[contains(@class, 'btn-primary ml-2')]")
    WebElement createRepositoryButton;

    private WebElement findRepositoryByName(String repositoryName){
        WebElement repo = null;
        for (WebElement repository : repositories) {
            if (repository.getText().equals(repositoryName))
                repo = repository;
        }

        return repo;
    }

    @Step("Open your repository: {name}")
    public RepositoryPage loadRepositoryPage (String name){
        WebElement repo = findRepositoryByName(name);
        waitForElementToBeClickable(repo);
        repo.click();
        return new RepositoryPage();
    }

    @Step("Open new repository page")
    public NewRepositoryPage loadNewRepositoryPage(){
        waitForElementToBeClickable(createRepositoryButton);
        createRepositoryButton.click();
        return new NewRepositoryPage();
    }

    public boolean containsRepository(String name){
        List<String> repos = repositories
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        return repos.contains(name);
    }
}

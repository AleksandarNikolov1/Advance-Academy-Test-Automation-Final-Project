package com.github.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RepositoriesPage extends BasePage{

    @FindBy(xpath = "//a[@itemprop='name codeRepository']")
    List<WebElement> repositories;

    public List<WebElement> getRepositories(){
        return repositories;
    }

    public WebElement findMyRepository(String repositoryName){
        WebElement myRepo = null;
        for (WebElement repository : repositories) {
            if (repository.getText().equals(repositoryName))
                myRepo = repository;
        }

        return myRepo;
    }

    public RepositoryPage switchToMyRepository(String repoName){
        findMyRepository(repoName).click();
        return new RepositoryPage();
    }
}

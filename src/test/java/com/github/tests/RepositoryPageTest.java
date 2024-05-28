package com.github.tests;

import com.github.pages.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static org.testng.AssertJUnit.*;

public class RepositoryPageTest extends BaseTest{

    private static final String VALID_EMAIL = "alfo0140@gmail.com";
    private static final String VALID_PASSWORD = "Thedarklord99!";
    private static final String REPO_NAME = "Repository-test-name";
    private static final String REPO_NAME_CHANGED = "Repository-test-name-Changed";

    InitialPage initialPage;
    SignInPage signInPage;
    HomePage homePage;
    RepositoryPage repositoryPage;

    @BeforeClass
    public void setUpHomePage(){
          initialPage = new InitialPage();
          signInPage = initialPage.getSignInPage();
          homePage = signInPage.loginAs(VALID_EMAIL, VALID_PASSWORD);
    }

    @Test
    public void testPageTitle(){
        repositoryPage = homePage.getNewRepositoryPage();
        String expectedTabTitle = "New repository";
        String actualTabTitle = repositoryPage.getTabTitle();

        String expectedTitle = "Create a new repository";
        String actualTitle = repositoryPage.getPageTitle();

        assertEquals(expectedTabTitle, actualTabTitle);
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testCreateNewRepository() throws InterruptedException {
        repositoryPage = homePage.getNewRepositoryPage();
        repositoryPage.createRepository(REPO_NAME);

        assertEquals(REPO_NAME, repositoryPage.getRepositoryName());
        assertTrue(repositoryPage.getCurrentUrl().endsWith(REPO_NAME));

        //post-condition
        GitHubApiManager.deleteRepository(REPO_NAME);
    }

    @Test
    public void testEditRepository(){

        //precondition
        GitHubApiManager.createRepository(REPO_NAME);

        //test
        RepositoriesPage repositoriesPage = homePage.switchToRepositoriesPage();
        RepositoryPage repositoryPage = repositoriesPage.switchToMyRepository(REPO_NAME);

        repositoryPage.switchToSettings();
        repositoryPage.editRepositoryName(REPO_NAME_CHANGED);
        repositoryPage.clickConfirmRenameButton();

        assertEquals(REPO_NAME_CHANGED, repositoryPage.getRepositoryName());
        assertTrue(repositoryPage.getCurrentUrl().endsWith(REPO_NAME_CHANGED));

        //post-condition
        GitHubApiManager.deleteRepository(REPO_NAME_CHANGED);
    }

    @Test
    public void testDeleteRepository(){

        //precondition
        GitHubApiManager.createRepository(REPO_NAME);

        //test
        RepositoriesPage repositoriesPage = homePage.switchToRepositoriesPage();
        RepositoryPage repositoryPage = repositoriesPage.switchToMyRepository(REPO_NAME);

        repositoryPage.switchToSettings();
        repositoryPage.deleteRepository("AleksandarNikolov1", REPO_NAME);
    }

}

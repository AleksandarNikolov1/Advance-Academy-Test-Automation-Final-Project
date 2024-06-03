package com.github.tests;

import com.github.pages.*;
import com.github.utils.GitHubRepositoryService;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static org.testng.AssertJUnit.*;

@Feature("CRUD Repository")
public class RepositoryTest extends BaseTest {

    private static final String VALID_EMAIL = "stu2001321058@uni-plovdiv.bg";
    private static final String VALID_PASSWORD = "secret_sauce34";
    private static final String USERNAME = "Test-username22";
    private static final String REPO_NAME = "REPO_NAME";
    private static final String REPO_NAME_UPDATE = "REPO_NAME_AFTER_UPDATE";


    InitialPage initialPage;
    SignInPage signInPage;
    HomePage homePage;
    RepositoriesPage repositoriesPage;
    RepositoryPage repositoryPage;
    NewRepositoryPage newRepositoryPage;

    @BeforeClass
    public void setUpRepositoriesPage() throws InterruptedException {
        initialPage = new InitialPage();
        signInPage = initialPage.loadSignInPage();
        homePage = signInPage.loginAs(VALID_EMAIL, VALID_PASSWORD);
        repositoriesPage = homePage.loadUserMenu().loadRepositoriesPage();
    }


    @Story("Create Repository")
    @Test
    public void testCreateNewRepository() throws InterruptedException {

        if (repositoriesPage.containsRepository(REPO_NAME)) {
            GitHubRepositoryService.deleteRepository(REPO_NAME);
        }

        newRepositoryPage = repositoriesPage.loadNewRepositoryPage();
        repositoryPage = newRepositoryPage.createRepository(REPO_NAME);

        assertEquals(REPO_NAME, repositoryPage.getRepositoryName());
        assertTrue(repositoryPage.getCurrentUrl().endsWith(REPO_NAME));

        repositoriesPage = repositoryPage.loadRepositoriesPage();

        assertTrue(repositoriesPage.containsRepository(REPO_NAME));

        //post-condition
        GitHubRepositoryService.deleteRepository(REPO_NAME);
        repositoriesPage.refresh();
    }

    @Story("Update Repository")
    @Test
    public void testUpdateRepository() throws InterruptedException {

        //precondition
        if (!repositoriesPage.containsRepository(REPO_NAME)) {
            GitHubRepositoryService.createRepository(REPO_NAME);
            repositoriesPage.refresh();
        }

        if (repositoriesPage.containsRepository(REPO_NAME_UPDATE)) {
            GitHubRepositoryService.deleteRepository(REPO_NAME_UPDATE);
            repositoriesPage.refresh();
        }

        //test
        repositoryPage = repositoriesPage.loadRepositoryPage(REPO_NAME);

        RepositorySettingsPage repositorySettingsPage = repositoryPage.loadRepositorySettingsPage();
        repositorySettingsPage.editRepositoryName(REPO_NAME_UPDATE);

        assertEquals(REPO_NAME_UPDATE, repositoryPage.getRepositoryName());
        assertTrue(repositoryPage.getCurrentUrl().endsWith(REPO_NAME_UPDATE));

        repositoriesPage = repositoryPage.loadRepositoriesPage();

        assertFalse(repositoriesPage.containsRepository(REPO_NAME));
        assertTrue(repositoriesPage.containsRepository(REPO_NAME_UPDATE));

        //post-condition
        GitHubRepositoryService.deleteRepository(REPO_NAME_UPDATE);
        repositoriesPage.refresh();
    }

    @Story("Delete Repository")
    @Test
    public void testDeleteRepository() {

        //precondition
        if (!repositoriesPage.containsRepository(REPO_NAME)) {
            GitHubRepositoryService.createRepository(REPO_NAME);
            repositoriesPage.refresh();
        }

        //test
        RepositoryPage repositoryPage = repositoriesPage.loadRepositoryPage(REPO_NAME);

        RepositorySettingsPage repositorySettingsPage = repositoryPage.loadRepositorySettingsPage();
        repositorySettingsPage.deleteRepository(USERNAME, REPO_NAME);

        assertFalse(repositoriesPage.containsRepository(REPO_NAME));
    }
}

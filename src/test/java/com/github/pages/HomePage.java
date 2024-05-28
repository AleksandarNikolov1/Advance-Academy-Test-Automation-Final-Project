package com.github.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(xpath = "//a[contains(@data-hydro-click, 'NEW_REPOSITORY_BUTTON')]")
    WebElement newRepositoryButton;

    @FindBy(xpath = "//img[@class='avatar circle']")
    WebElement avatarIcon;

    @FindBy(xpath = "//a[contains(@data-analytics-event, 'YOUR_REPOSITORIES')]")
    WebElement yourRepositoriesOption;


    public RepositoryPage getNewRepositoryPage(){
        waitForElementToBeClickable(newRepositoryButton);
        newRepositoryButton.click();
        return new RepositoryPage();


    }

    public void clickAvatarIcon(){
        waitForElementToBeClickable(avatarIcon);
        avatarIcon.click();
    }

    public void clickYourRepositoriesOption(){
        waitForElementToBeClickable(yourRepositoriesOption);
        yourRepositoriesOption.click();
    }

    public RepositoriesPage switchToRepositoriesPage(){
        clickAvatarIcon();
        clickYourRepositoriesOption();
        return new RepositoriesPage();
    }


}

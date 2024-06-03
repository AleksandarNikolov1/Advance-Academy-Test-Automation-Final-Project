package com.github.components;

import com.github.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends BasePage {

    @FindBy(id = "settings-tab")
    WebElement settingsButton;

    @FindBy(xpath = "//img[@class='avatar circle']")
    WebElement avatarIcon;

    @Step("Click \"Settings\" button")
    public void clickSettingButton() {
        waitForElementToBeClickable(settingsButton);
        settingsButton.click();
    }

    @Step("Click avatar icon")
    public void clickAvatarIcon() {
        waitForElementToBeClickable(avatarIcon);
        avatarIcon.click();
    }

    @Step("Open user menu")
    public UserMenu loadUserMenu() {
        clickAvatarIcon();
        return new UserMenu();
    }
}

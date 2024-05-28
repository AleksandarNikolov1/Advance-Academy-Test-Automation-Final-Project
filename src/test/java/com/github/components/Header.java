package com.github.components;

import com.github.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends BasePage {

    @FindBy(id = "settings-tab")
    WebElement settingsButton;

    public void clickSettingButton(){
        waitForElementToBeClickable(settingsButton);
        settingsButton.click();
    }
}

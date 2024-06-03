package com.github.pages;

import com.github.components.Header;
import com.github.components.UserMenu;
import io.qameta.allure.Step;

public class HomePage extends BasePage{

    Header header = new Header();

    @Step("Open user menu clicking avatar icon")
    public UserMenu loadUserMenu(){
        header.clickAvatarIcon();
        return new UserMenu();
    }
}

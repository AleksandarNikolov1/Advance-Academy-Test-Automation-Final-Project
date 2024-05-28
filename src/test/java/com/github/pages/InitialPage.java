package com.github.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InitialPage extends BasePage {

    @FindBy(xpath = "//a[contains(@class, 'sign-up')]")
    WebElement signUpButton;

    @FindBy(xpath = "//a[contains(@class, 'sign-in')]")
    WebElement signInButton;

    public SignUpPage getSignUpPage(){
        waitForElementToBeClickable(signUpButton);
        signUpButton.click();
        return new SignUpPage();
    }

    public SignInPage getSignInPage(){
        waitForElementToBeClickable(signInButton);
        signInButton.click();
        return new SignInPage();
    }
}

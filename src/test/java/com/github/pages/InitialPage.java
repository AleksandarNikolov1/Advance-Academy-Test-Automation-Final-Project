package com.github.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InitialPage extends BasePage {

    @FindBy(xpath = "//a[contains(@class, 'sign-up')]")
    WebElement signUpButton;

    @FindBy(xpath = "//a[contains(@class, 'sign-in')]")
    WebElement signInButton;

    @Step("Open sign up page clicking \"Sign up\" button")
    public SignUpPage loadSignUpPage(){
        waitForElementToBeClickable(signUpButton);
        signUpButton.click();
        return new SignUpPage();
    }

    @Step("Open sign in page clicking \"Sign in\" button")
    public SignInPage loadSignInPage(){
        waitForElementToBeClickable(signInButton);
        signInButton.click();
        return new SignInPage();
    }
}

package com.github.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {

    @FindBy(id = "login_field")
    WebElement loginTextArea;

    @FindBy(id = "password")
    WebElement passwordTextArea;

    @FindBy(xpath = "//input[@value='Sign in']")
    WebElement submitButton;

    @Step("Set email address: {email}")
    public void setEmailAddress(String email){
        loginTextArea.clear();
        loginTextArea.sendKeys(email);
    }

    @Step("Set username: {username}")
    public void setUsername(String username){
        loginTextArea.clear();
        loginTextArea.sendKeys(username);
    }

    @Step("Set password: {password}")
    public void setPassword(String password){
        passwordTextArea.clear();
        passwordTextArea.sendKeys(password);
    }

    @Step("Click \"Sign in\" button")
    public void clickSubmitButton(){
        submitButton.click();
    }

    @Step("Login as: {email}, {password}")
    public HomePage loginAs(String email, String password){
        setEmailAddress(email);
        setPassword(password);
        clickSubmitButton();

        return new HomePage();
    }
}

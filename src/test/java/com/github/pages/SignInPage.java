package com.github.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {

    @FindBy(id = "login_field")
    WebElement loginTextArea;

    @FindBy(id = "password")
    WebElement passwordTextArea;

    @FindBy(xpath = "//input[@value='Sign in']")
    WebElement submitButton;

    public void setEmailAddress(String email){
        loginTextArea.clear();
        loginTextArea.sendKeys(email);
    }

    public void setUsername(String username){
        loginTextArea.clear();
        loginTextArea.sendKeys(username);
    }

    public void setPassword(String password){
        passwordTextArea.clear();
        passwordTextArea.sendKeys(password);
    }

    public void clickSubmitButton(){
        submitButton.click();
    }

    public HomePage loginAs(String email, String password){
        setEmailAddress(email);
        setPassword(password);
        clickSubmitButton();

        return new HomePage();
    }
}

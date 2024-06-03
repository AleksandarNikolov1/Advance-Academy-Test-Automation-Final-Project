package com.github.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {

    @FindBy(xpath = "//span[@class='text-mono text-gray-light-mktg']")
    WebElement welcomeMessage;

    @FindBy(id = "email")
    WebElement emailTextArea;

    @FindBy(xpath = "//button[@type='button' and @data-continue-to='password-container']")
    WebElement confirmEmailButton;

    @FindBy(id = "password")
    WebElement passwordTextArea;

    @FindBy(xpath = "//button[@type='button' and @data-continue-to='username-container']")
    WebElement confirmPasswordButton;

    @FindBy(id = "login")
    WebElement usernameTextArea;

    @FindBy(xpath = "//button[@type='button' and @data-continue-to='opt-in-container']")
    WebElement confirmUsernameButton;

    @FindBy(id = "opt_in")
    WebElement emailPreferencesCheckBox;

    @FindBy(xpath = "//button[@type='button' and @data-continue-to='captcha-and-submit-container']")
    WebElement confirmRegistrationButton;

    @FindBy(id = "email-err")
    WebElement emailErrorMessage;

    @FindBy(id = "password-err")
    WebElement passwordErrorMessage;

    @FindBy(id = "login-err")
    WebElement usernameErrorMessage;

    @FindBy(xpath = "//*[@id='captcha-and-submit-container']/div[1]")
    WebElement verifyAccountMessage;

    @FindBy(xpath = "//*[@id='root']/div/div[1]/h2")
    WebElement protectAccountMessage;

    @FindBy(xpath = "//*[@id='root']/div/div[1]/p")
    WebElement taskConditionMessage;

    @FindBy(xpath = "//*[@id='root']/div/div[1]/button")
    WebElement verifyButton;

    @FindBy(xpath = "//*[@id='root']/div/div[2]/button")
    WebElement audioButton;

//    @FindBy(xpath = "//iframe[@title='Please verify by completing this captcha.']")
//    WebElement verificationAccountFrame;

    @FindBy(id = "game-core-frame")
    WebElement verificationAccountFrame;

    public String getWelcomeMessage() {
        waitForTextToBePresentInElement(welcomeMessage, "adventure");
        return welcomeMessage.getText();
    }

    @Step("Set email")
    public void setEmail(String email){
        emailTextArea.clear();
        emailTextArea.sendKeys(email);
    }


    @Step("Click \"Continue\" button")
    public void clickConfirmEmailButton(){
        waitForElementToBeClickable(confirmEmailButton);
        confirmEmailButton.click();
    }

    @Step("Set password: {password}")
    public void setPassword(String password){
        passwordTextArea.clear();
        passwordTextArea.sendKeys(password);
    }

    @Step("Click \"Continue\" button")
    public void clickConfirmPasswordButton(){
        waitForElementToBeClickable(confirmPasswordButton);
        confirmPasswordButton.click();
    }

    @Step("Set username: {username}")
    public void setUsername(String username){
        usernameTextArea.clear();
        usernameTextArea.sendKeys(username);
    }

    @Step("Click \"Continue\" button")
    public void clickConfirmUsernameButton(){
        waitForElementToBeClickable(confirmUsernameButton);
        confirmUsernameButton.click();
    }

    @Step("Click email preferences check box")
    public void clickEmailPreferencesCheckBox(){
        waitForElementToBeClickable(emailPreferencesCheckBox);
        emailPreferencesCheckBox.click();
    }

    @Step("Click \"Continue\" button")
    public void clickConfirmRegistrationButton(){
        waitForElementToBeClickable(confirmRegistrationButton);
        confirmRegistrationButton.click();
    }

    public String getErrorMessageForInvalidEmail(){
        waitForElementToBeVisible(emailErrorMessage);
        return emailErrorMessage.getText();
    }

    public String getErrorMessageForInvalidPassword(){
        waitForElementToBeVisible(passwordErrorMessage);
        return passwordErrorMessage.getText();
    }

    public String getErrorMessageForInvalidUsername(){
        waitForElementToBeVisible(usernameErrorMessage);
        return usernameErrorMessage.getText();
    }

    public boolean isPasswordTextAreaPresent(){
        return passwordTextArea.isDisplayed();
    }

    public boolean isUsernameTextAreaPresent(){
        return usernameTextArea.isDisplayed();
    }

    public boolean isEmailPreferencesCheckBoxSelected(){
        return emailPreferencesCheckBox.isSelected();
    }

    @Step("Sign Up")
    public void signUp(String email, String password, String username){
        setEmail(email);
        clickConfirmEmailButton();
        setPassword(password);
        clickConfirmPasswordButton();
        setUsername(username);
        clickConfirmUsernameButton();
        clickConfirmRegistrationButton();
    }

    public String getVerifyAccountMessage(){
        return verifyAccountMessage.getText();
    }

    public void switchToVerificationAccountFrame(){
      //  scrollIntoViewWithJS(verificationAccountFrame);
        waitForElementToBeVisible(verificationAccountFrame);
        switchToFrame(verificationAccountFrame);
    }

    public String getProtectAccountMessage(){
        waitForElementToBeVisible(protectAccountMessage);
        return protectAccountMessage.getText();
    }

    public String getTaskConditionMessage(){
        waitForElementToBeVisible(taskConditionMessage);
        return taskConditionMessage.getText();
    }

    public boolean isVerifyButtonClickable(){
        waitForElementToBeVisible(verifyButton);
        return verifyButton.isEnabled();
    }

    public boolean isAudioButtonClickable(){
        scrollIntoViewWithJS(audioButton);
        waitForElementToBeVisible(audioButton);
        return audioButton.isEnabled();
    }
}

package com.github.tests;

import com.github.pages.InitialPage;
import com.github.pages.SignUpPage;
import data.TestDataProvider;
import io.qameta.allure.*;
import net.bytebuddy.build.Plugin;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.AssertJUnit.*;

public class SignUpPageTest extends BaseTest {

    InitialPage initialPage;
    SignUpPage signUpPage;
    SoftAssert softAssert;

    @BeforeMethod
    public void setupSignUpPage() {
        initialPage = new InitialPage();
        signUpPage = initialPage.getSignUpPage();
        softAssert = new SoftAssert();
    }


    @Test(priority = 1)
    public void testWelcomeMessage() {
        String expectedWelcomeMessage = "Welcome to GitHub! Let’s begin the adventure";
        String actualWelcomeMessage = signUpPage.getWelcomeMessage().replaceAll("\n", " ");

        assertEquals(expectedWelcomeMessage, actualWelcomeMessage);
    }

    @Epic("Authentication")
    @Feature("Login")
    @Story("Negative login tests with invalid emails")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2, dataProvider = "invalid-emails", dataProviderClass = TestDataProvider.class)
    public void testInvalidEmails(String email, String expectedErrorMessage) {


        signUpPage.setEmail(email);

        String actualErrorMessage = signUpPage.getErrorMessageForInvalidEmail();
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test(priority = 3, dataProvider = "invalid-passwords", dataProviderClass = TestDataProvider.class)
    public void testInvalidPasswords(String password, String expectedErrorMessage) {
        if (!signUpPage.isPasswordTextAreaPresent()) {
            enterValidEmail();
        }

        signUpPage.setPassword(password);
        String actualErrorMessage = signUpPage.getErrorMessageForInvalidPassword().replaceAll("\n", " ");
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }


    @Test(priority = 4, dataProvider = "invalid-usernames", dataProviderClass = TestDataProvider.class)
    public void testInvalidUsernames(String username, String expectedErrorMessage) {
        if (!signUpPage.isUsernameTextAreaPresent()) {
            enterValidEmail();
            enterValidPassword();
        }

        signUpPage.setUsername(username);
        String actualErrorMessage = signUpPage.getErrorMessageForInvalidUsername().replaceAll("\n", " ");
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test(priority = 5)
    public void testEmailPreferencesAreEnabled(){
        enterValidEmail();
        enterValidPassword();
        enterValidUsername();

        signUpPage.clickEmailPreferencesCheckBox();

        assertTrue(signUpPage.isEmailPreferencesCheckBoxSelected());
 //       assertEquals(true, signUpPage.getEmailPreferencesCssValueIsChecked());
    }

    @Test(priority = 6)
    public void testValidRegistration() {
        enterValidEmail();
        enterValidPassword();
        enterValidUsername();

        signUpPage.clickEmailPreferencesCheckBox();

        softAssert.assertTrue(signUpPage.isEmailPreferencesCheckBoxSelected());

        signUpPage.clickConfirmRegistrationButton();

        testWelcomeMessage();

        softAssert.assertEquals("Verify your account", signUpPage.getVerifyAccountMessage());
//        softAssert.assertEquals("Protecting your account", signUpPage.getProtectAccountMessage());
//        softAssert.assertTrue(signUpPage.isVerifyButtonClickable());
//        softAssert.assertTrue(signUpPage.isAudioButtonClickable());
        softAssert.assertAll();
    }


    private void enterValidEmail() {
        signUpPage.setEmail("correctemail@gmail.com");
        signUpPage.clickConfirmEmailButton();
    }

    private void enterValidPassword() {
        signUpPage.setPassword("CorrectPassword123");
        signUpPage.clickConfirmPasswordButton();
    }

    private void enterValidUsername() {
        signUpPage.setUsername("CorrectUsername");
        signUpPage.clickConfirmUsernameButton();
    }
}




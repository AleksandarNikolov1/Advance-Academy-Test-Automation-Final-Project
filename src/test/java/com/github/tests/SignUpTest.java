package com.github.tests;

import com.github.pages.InitialPage;
import com.github.pages.SignUpPage;
import data.TestDataProvider;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

@Feature("Registration")
public class SignUpTest extends BaseTest {

    private static final String VALID_EMAIL = "correctemail@gmail.com";
    private static final String VALID_PASSWORD = "CorrectPassword123";
    private static final String VALID_USERNAME = "CorrectUsername";

    InitialPage initialPage;
    SignUpPage signUpPage;

    @BeforeClass
    public void setupSignUpPage() {
        initialPage = new InitialPage();
        signUpPage = initialPage.loadSignUpPage();
    }


    @Test(priority = 1)
    public void testWelcomeMessage() {
        String expectedWelcomeMessage = "Welcome to GitHub! Let’s begin the adventure";
        String actualWelcomeMessage = signUpPage.getWelcomeMessage()
                .replaceAll("\n", " ");

        assertEquals(expectedWelcomeMessage, actualWelcomeMessage);
    }

    @Story("Test invalid registration emails")
    @Test(priority = 2, dataProvider = "invalid-emails", dataProviderClass = TestDataProvider.class)
    public void testInvalidEmails(String email, String expectedErrorMessage) {

        signUpPage.setEmail(email);

        String actualErrorMessage = signUpPage.getErrorMessageForInvalidEmail();
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Story("Test invalid registration passwords")
    @Test(priority = 3, dataProvider = "invalid-passwords", dataProviderClass = TestDataProvider.class)
    public void testInvalidPasswords(String password, String expectedErrorMessage) {
        if (!signUpPage.isPasswordTextAreaPresent()) {
            signUpPage.setEmail(VALID_EMAIL);
            signUpPage.clickConfirmEmailButton();
        }

        signUpPage.setPassword(password);
        String actualErrorMessage = signUpPage.getErrorMessageForInvalidPassword().replaceAll("\n", " ");
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }


    @Story("Test invalid registration usernames")
    @Test(priority = 4, dataProvider = "invalid-usernames", dataProviderClass = TestDataProvider.class)
    public void testInvalidUsernames(String username, String expectedErrorMessage) {
        if (!signUpPage.isUsernameTextAreaPresent()) {
            signUpPage.setEmail(VALID_EMAIL);
            signUpPage.clickConfirmEmailButton();
            signUpPage.setPassword(VALID_PASSWORD);
            signUpPage.clickConfirmPasswordButton();
        }

        signUpPage.setUsername(username);
        String actualErrorMessage = signUpPage.getErrorMessageForInvalidUsername().replaceAll("\n", " ");
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test(priority = 5)
    public void testEmailPreferencesAreEnabled() {
        signUpPage.setEmail(VALID_EMAIL);
        signUpPage.clickConfirmEmailButton();
        signUpPage.setPassword(VALID_PASSWORD);
        signUpPage.clickConfirmPasswordButton();
        signUpPage.setUsername(VALID_USERNAME);
        signUpPage.clickConfirmUsernameButton();

        signUpPage.clickEmailPreferencesCheckBox();

        assertTrue(signUpPage.isEmailPreferencesCheckBoxSelected());
    }

    @Story("Test valid registration")
    @Test(priority = 6)
    public void testValidRegistration() {
        signUpPage.signUp(VALID_EMAIL, VALID_PASSWORD, VALID_USERNAME);

        String expectedMessage = "Verify your account";
        String actualMessage = signUpPage.getVerifyAccountMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}




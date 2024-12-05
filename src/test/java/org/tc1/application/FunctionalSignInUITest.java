package org.tc1.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tc1.pages.SignInPage;
import org.tc1.setup.SetupTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FunctionalSignInUITest extends SetupTest {
    private SignInPage signInPage;

    @BeforeEach
    public void openLoginModal() {
        signInPage = new SignInPage(driver);
        signInPage.clickLogInModalButton();
    }

    @Test
    @DisplayName("Validate H1 Heading Text")
    public void testPageTitle() {
        String expectedTitle = "Hospital digital";
        String actualTitle = signInPage.getPageTitle();
        assertEquals(expectedTitle, actualTitle,
                "The page title does not match what was expected.");
    }

    @Test
    @DisplayName("Verify Login Button Visibility")
    public void testLoginButtonVisibility() {
        assertTrue(signInPage.checkIfLoginButtonIsDisplayed(),
                "The login button is not visible.");
    }

    @Test
    @DisplayName("Verify Login Modal Button Visibility")
    public void testLoginModalButtonVisibility() {
        assertTrue(signInPage.checkIfLoginModalButtonIsDisplayed(),
                "The login modal button is not visible.");
    }

    @Test
    @DisplayName("Verify Email Input Visibility")
    public void testEmailInputVisibility() {
        assertTrue(signInPage.checkIfEmailInputIsDisplayed(),
                "The email field is not visible.");
    }

    @Test
    @DisplayName("Verify Password Input Visibility")
    public void testPasswordInputVisibility() {
        assertTrue(signInPage.checkIfPasswordInputIsDisplayed(),
                "The password field is not visible.");
    }

    @Test
    @DisplayName("Verify Cancel Button Visibility")
    public void testCancelButtonVisibility() {
        assertTrue(signInPage.checkIfCancelButtonIsDisplayed(),
                "The cancel button is not visible.");
    }
}

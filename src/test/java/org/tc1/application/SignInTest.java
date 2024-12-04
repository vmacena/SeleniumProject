package org.tc1.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tc1.pages.SignInPage;
import org.tc1.setup.SetupTest;


import static org.junit.jupiter.api.Assertions.*;

public class SignInTest extends SetupTest {
    
    @Test
    @DisplayName("Should not allow an unregistered user to login and then allow closing the modal")
    public void testLoginFailure(){
        SignInPage signInPage = new SignInPage(driver);
        
        signInPage.clickLogInModalButton();
        signInPage.fillForm(faker.internet().emailAddress(), faker.rockBand().name() + faker.number().digits(3));
        signInPage.submitForm();
        signInPage.cancelForm();
        
        assertFalse(signInPage.verifyModalIsDisplayed());
    }

    @Test
    @DisplayName("Should work if you fail to login more than once")
    public void testMultipleLoginFailures() throws InterruptedException {
        SignInPage signInPage = new SignInPage(driver);

        signInPage.clickLogInModalButton();
        signInPage.fillForm(faker.internet().emailAddress(), faker.rockBand().name() + faker.number().digits(3));
        signInPage.submitForm();

        signInPage.fillForm(faker.internet().emailAddress(), faker.rockBand().name() + faker.number().digits(3));
        signInPage.submitForm();
        
        Thread.sleep(5000);

        assertTrue(signInPage.verifyModalIsDisplayed());
    }
}

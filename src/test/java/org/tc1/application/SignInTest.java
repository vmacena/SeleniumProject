package org.tc1.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.tc1.pages.SignInPage;
import org.tc1.pages.SignUpPage;
import org.tc1.setup.SetupTest;


import java.text.SimpleDateFormat;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SignInTest extends SetupTest {
    private SignInPage signInPage;
    
    @BeforeEach
    public void createPage(){
        signInPage = new SignInPage(driver);
    }
    
    @Test
    @DisplayName("Should not allow an unregistered user to login and then allow closing the modal")
    public void testLoginFailure(){
        signInPage.clickLogInModalButton();
        signInPage.fillForm(faker.internet().emailAddress(), faker.rockBand().name() + faker.number().digits(3));
        signInPage.submitForm();
        signInPage.cancelForm();
        
        assertFalse(signInPage.verifyModalIsDisplayed());
    }

    @Test
    @DisplayName("Should work if you fail to login more than once")
    public void testMultipleLoginFailures() {
        signInPage.clickLogInModalButton();
        signInPage.fillForm(faker.internet().emailAddress(), faker.rockBand().name() + faker.number().digits(3));
        signInPage.submitForm();

        signInPage.fillForm(faker.internet().emailAddress(), faker.rockBand().name() + faker.number().digits(3));
        signInPage.submitForm();
        
        assertTrue(signInPage.verifyModalIsDisplayed());
    }

    @ParameterizedTest
    @MethodSource("provideSignUpData")
    @DisplayName("Should let you login if you register beforehand")
    public void testSuccessfulLogin(
            String name,
            String email,
            int sexoIndex,
            String birthDate,
            int typeIndex,
            String cri,
            int specialityIndex,
            String password,
            String confirmPassword,
            String loginEmail,
            String loginPassword,
            boolean expected
    ) {
        SignUpPage signUpPage = new SignUpPage(driver);
        
        signUpPage.clickSignUpButton();
        signUpPage.fillForm(name, email, sexoIndex, birthDate, typeIndex, cri, specialityIndex, password, confirmPassword);
        signUpPage.submitForm();
        
        signInPage.clickLogInModalButton();
        signInPage.fillForm(loginEmail,loginPassword);
        signInPage.submitForm();
        
        signInPage.waitForUrlChange();

        var result = driver.getCurrentUrl();
        
        assertEquals(expected, result.equals( BASE_URL + "user"));
    }

    private static Stream<Arguments> provideSignUpData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        var email1 = faker.internet().emailAddress();
        var password1 = "123";

        return Stream.of(
                Arguments.of(
                        "@#$%^",
                        email1,
                        faker.number().numberBetween(1, 2),
                        dateFormat.format(faker.date().birthday(18, 60)),
                        1,
                        String.valueOf(faker.number().numberBetween(10000, 999999)),
                        faker.number().numberBetween(1, 3),
                        password1,
                        password1,
                        email1,
                        password1,
                        true
                ),
                Arguments.of(
                        faker.name().firstName(),
                        faker.internet().emailAddress(),
                        faker.number().numberBetween(1, 2),
                        dateFormat.format(faker.date().birthday(18, 60)),
                        1,
                        String.valueOf(faker.number().numberBetween(10000, 999999)),
                        faker.number().numberBetween(1, 3),
                        password1,
                        password1,
                        faker.internet().emailAddress(),
                        password1,
                        false
                )
        );
    }
}

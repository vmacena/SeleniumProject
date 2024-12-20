package org.tc1.application;

import junit.framework.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.tc1.pages.ProfilePage;
import org.tc1.pages.SignInPage;
import org.tc1.pages.SignUpPage;
import org.tc1.setup.SetupTest;
import org.tc1.utils.Utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class ProfileTest extends SetupTest {
    private static Stream<Arguments> provideProfileData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return Stream.of(
                Arguments.of(
                        faker.name().firstName(),
                        faker.number().numberBetween(1, 2),
                        LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        false
                ),
                Arguments.of(
                        faker.name().firstName(),
                        faker.number().numberBetween(1, 2),
                        dateFormat.format(faker.date().birthday(18, 60)),
                        true
                ),
                Arguments.of(
                        "",
                        null,
                        null,
                        false
                ),
                Arguments.of(
                        " ",
                        null,
                        null,
                        false
                ),
                Arguments.of(
                        "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss",
                        null,
                        null,
                        false
                ),
                Arguments.of(
                        "nome",
                        null,
                        "222222-11-12",
                        false
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideProfileData")
    @DisplayName("Validate Profile")
    public void testSignUp(String name, Integer sexoIndex, String birthDate, boolean expected) {
        ProfilePage profilePage = new ProfilePage(driver);
        SignInPage signInPage = new SignInPage(driver);

        signInPage.clickLogInModalButton();
        signInPage.fillForm("emaildeteste@gmail.com", "123");
        signInPage.submitForm();

        profilePage.clickUserButton();
        profilePage.fillForm(name, sexoIndex, birthDate);
        profilePage.submitForm();

        Utils.refreshScreen(driver);
        profilePage.clickUserButton();

        Assert.assertEquals(expected, profilePage.compareFields(name, sexoIndex, birthDate));
    }

    private static Stream<Arguments> provideConsistentProfileData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return Stream.of(
                Arguments.of(
                        faker.name().firstName(),
                        faker.internet().emailAddress(),
                        faker.number().numberBetween(1, 2),
                        dateFormat.format(faker.date().birthday(18, 60)),
                        1,
                        String.valueOf(faker.number().numberBetween(10000, 999999)),
                        faker.number().numberBetween(1, 3),
                        "123",
                        "123",
                        true
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideConsistentProfileData")
    @DisplayName("Validate Profile")
    public void testConsistenceProfile(String name, String email, int sexoIndex, String birthDate, int typeIndex, String cri, int specialityIndex, String password, String confirmPassword, boolean expected) throws InterruptedException {
        SignUpPage signUpPage = new SignUpPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        SignInPage signInPage = new SignInPage(driver);

        signUpPage.clickSignUpButton();
        signUpPage.fillForm(name, email, sexoIndex, birthDate, typeIndex, cri, specialityIndex, password, confirmPassword);
        signUpPage.submitForm();

        signInPage.clickLogInModalButton();
        signInPage.fillForm(email, password);
        signInPage.submitForm();

        profilePage.clickUserButton();
        Assert.assertEquals(expected, profilePage.compareFields(name, sexoIndex, birthDate));
    }
}

package org.tc1.application;

import junit.framework.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.tc1.pages.ProfilePage;
import org.tc1.pages.SignInPage;
import org.tc1.setup.SetupTest;
import org.tc1.utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class ProfileTest extends SetupTest {
    private static Stream<Arguments> provideProfileData() {

        return Stream.of(
                Arguments.of(
                        faker.name().firstName(),
                        faker.number().numberBetween(1, 2),
                        LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        false
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideProfileData")
    @DisplayName("Validate Profile")
    public void testSignUp(String name, int sexoIndex, String birthDate, boolean expected) {
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
}

package org.tc1.application;

import junit.framework.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.tc1.pages.SignUpPage;
import org.tc1.setup.SetupTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class SignUpTest extends SetupTest {

    private static Stream<Arguments> provideSignUpData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return Stream.of(
                Arguments.of(
                        faker.name().firstName(),
                        faker.name().firstName(),
                        faker.number().numberBetween(1, 2),
                        dateFormat.format(faker.date().birthday(18, 60)),
                        1,
                        String.valueOf(faker.number().numberBetween(10000, 999999)),
                        faker.number().numberBetween(1, 3),
                        "123",
                        "123",
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
                        "123",
                        "123",
                        false
                ),
                Arguments.of(
                        "",
                        faker.internet().emailAddress(),
                        faker.number().numberBetween(1, 2),
                        dateFormat.format(faker.date().birthday(18, 60)),
                        1,
                        String.valueOf(faker.number().numberBetween(10000, 999999)),
                        faker.number().numberBetween(1, 3),
                        "123",
                        "123",
                        true
                ),
                Arguments.of(
                        "@#$%^",
                        faker.internet().emailAddress(),
                        faker.number().numberBetween(1, 2),
                        dateFormat.format(faker.date().birthday(18, 60)),
                        1,
                        String.valueOf(faker.number().numberBetween(10000, 999999)),
                        faker.number().numberBetween(1, 3),
                        "123",
                        "123",
                        true
                ),
                Arguments.of(
                        " ",
                        faker.internet().emailAddress(),
                        faker.number().numberBetween(1, 2),
                        dateFormat.format(faker.date().birthday(18, 60)),
                        1,
                        String.valueOf(faker.number().numberBetween(10000, 999999)),
                        faker.number().numberBetween(1, 3),
                        "123",
                        "123",
                        true
                ),
                Arguments.of(
                        faker.name().firstName(),
                        faker.internet().emailAddress(),
                        faker.number().numberBetween(1, 2),
                        LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
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
    @MethodSource("provideSignUpData")
    @DisplayName("Validate SignUp")
    public void testSignUp(String name, String email, int sexoIndex, String birthDate, int typeIndex, String cri, int specialityIndex, String password, String confirmPassword, boolean expected) {
        SignUpPage signUpPage = new SignUpPage(driver);

        signUpPage.clickSignUpButton();
        signUpPage.fillForm(name, email, sexoIndex, birthDate, typeIndex, cri, specialityIndex, password, confirmPassword);
        signUpPage.submitForm();

        Assert.assertEquals(expected, signUpPage.verifyModalIsDisplayed());
    }
}
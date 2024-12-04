package org.tc1.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.tc1.setup.SetupTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FunctionalSignInUITests extends SetupTest {
    private By loginModalButton = By.xpath("//*[@id=\"root\"]/div/nav/div[2]/button[1]");
    private By emailInput = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/label[1]/input");
    private By passwordInput = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/label[2]/input");
    private By loginButton = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/div/button[2]");
    private By cancelButton = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/div/button[1]");

    @BeforeEach
    public void openLoginModal() {
        driver.findElement(loginModalButton).click();
    }

    @Test
    @DisplayName("Validate H1 Heading Text")
    public void testPageTitle() {
        String expectedTitle = "Hospital digital";
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle,
                "The page title does not match what was expected.");
    }
}

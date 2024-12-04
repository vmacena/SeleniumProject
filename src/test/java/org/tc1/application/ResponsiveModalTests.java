package org.tc1.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.tc1.setup.MobileSetupTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResponsiveModalTests extends MobileSetupTest {
    private By loginModalButton = By.xpath("//*[@id=\"root\"]/div/nav/div[2]/button[1]");
    private By emailInput = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/label[1]/input");
    private By loginModal = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form");

    @BeforeEach
    public void openLoginModal() {
        driver.findElement(loginModalButton).click();
    }

    @Test
    @DisplayName("Check if the login input leaves the modal on the Mobile Device")
    public void testInputPositionOnIPhone12Pro() {
        WebElement modal = driver.findElement(loginModal);
        WebElement input = driver.findElement(emailInput);

        boolean isInputInsideModal = isElementInsideAnother(modal, input);
        assertTrue(isInputInsideModal, "The email input is outside the modal.");
    }

    private boolean isElementInsideAnother(WebElement outer, WebElement inner) {
        org.openqa.selenium.Rectangle outerRect = outer.getRect();
        org.openqa.selenium.Rectangle innerRect = inner.getRect();
        return innerRect.x >= outerRect.x &&
                innerRect.y >= outerRect.y &&
                innerRect.x + innerRect.width <= outerRect.x + outerRect.width &&
                innerRect.y + innerRect.height <= outerRect.y + outerRect.height;
    }
}
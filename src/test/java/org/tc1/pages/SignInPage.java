package org.tc1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tc1.utils.Utils;

import java.time.Duration;

public class SignInPage {
    private final WebDriver driver;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By loginModalButton = By.xpath("//*[@id=\"root\"]/div/nav/div[2]/button[1]");
    private final By loginModal = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div");
    private final By emailInput = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/label[1]/input");
    private final By passwordInput = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/label[2]/input");
    private final By loginButton = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/div/button[2]");
    private final By cancelButton = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/div/button[1]");

    public void clickLogInModalButton() {
        Utils.clickElement(driver, loginModalButton);
    }

    public void fillForm(String email, String password) {
        Utils.sendKeys(driver, emailInput, email);
        Utils.sendKeys(driver, passwordInput, password);
    }

    public void submitForm() {
        Utils.clickElement(driver, loginButton);
        Utils.waitForFieldToBeEditable(driver, loginButton, 3);
    }

    public void cancelForm() {
        Utils.clickElement(driver, cancelButton);
    }

    public boolean verifyModalIsDisplayed() {
        Utils.waitForFieldToBeEditable(driver, loginButton, 3);
        
        return Utils.verifyIsDisplayed(driver, loginModal, 2);
    }

    public boolean isUrlChanged() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("https://site-test-selenium.vercel.app/")));
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
}

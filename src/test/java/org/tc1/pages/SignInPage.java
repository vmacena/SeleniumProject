package org.tc1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.tc1.utils.Utils;

public class SignInPage {
    private WebDriver driver;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By loginModalButton = By.xpath("//*[@id=\"root\"]/div/nav/div[2]/button[1]");
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
    }

    public void cancelForm() {
        Utils.clickElement(driver, cancelButton);
    }
}

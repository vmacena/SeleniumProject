package org.tc1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.tc1.utils.Utils;

public class SignUpPage {
    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    private By formModal = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div");
    private By signupButton = By.xpath("//*[@id=\"root\"]/div/nav/div[2]/button[2]");
    private By nameInput = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/label[1]/input");
    private By emailInput = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/label[2]/input");
    private By sexoSelect = By.xpath("//*[@id=\"sexo\"]");
    private By birthdateInput = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/label[4]/input");
    private By typeSelect = By.xpath("//*[@id=\"user_type\"]");
    private By criInput = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/label[6]/input");
    private By specialitySelect = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/label[7]/select");
    private By passwordInput = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/label[8]/input");
    private By confirmPasswordInput = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/label[9]/input");
    private By submitButton = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/div/button[2]");
    private By cancelButton = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/div/button[1]");

    public void clickSignUpButton() {
        Utils.clickElement(driver, signupButton);
    }

    public void fillForm(String name, String email, int sexoIndex, String birthdate, int typeIndex, String cri, int specialityIndex, String password, String confirmPassword) {
        Utils.fillInput(driver, nameInput, name);
        Utils.fillInput(driver, emailInput, email);

        Utils.selectByIndex(driver, sexoSelect, sexoIndex);
        Utils.fillInput(driver, birthdateInput, birthdate);

        Utils.selectByIndex(driver, typeSelect, typeIndex);
        Utils.fillInput(driver, criInput, cri);

        Utils.selectByIndex(driver, specialitySelect, specialityIndex);
        Utils.fillInput(driver, passwordInput, password);
        Utils.fillInput(driver, confirmPasswordInput, confirmPassword);
    }

    public void submitForm() {
        Utils.clickElement(driver, submitButton);
    }

    public void cancelForm() {
        Utils.clickElement(driver, cancelButton);
    }

    public boolean verifyModalIsDisplayed() {
        Utils.waitForFieldToBeEditable(driver, submitButton, 2);
        boolean formModalIsVisible = Utils.verifyIsDisplayed(driver, formModal, 2);

        return formModalIsVisible;
    }
}

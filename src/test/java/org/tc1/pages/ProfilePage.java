package org.tc1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.tc1.utils.Utils;

public class ProfilePage {
    private WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By buttonUser = By.xpath("//*[@id=\"root\"]/div/nav/button");
    private final By nameUser = By.xpath("//*[@id=\"root\"]/div/nav/button/p");
    private final By nameInput = By.xpath("//*[@id=\"root\"]/div/main/div/form/label[1]/input");
    private final By sexoSelect = By.xpath("//*[@id=\"sexo\"]");
    private final By birthdateInput = By.xpath("//*[@id=\"root\"]/div/main/div/form/label[4]/input");
    private final By submitButton = By.xpath("//*[@id=\"root\"]/div/main/div/form/div/button[2]");
    private final By restoreButton = By.xpath("//*[@id=\"root\"]/div/main/div/form/div/button[1]");

    public void clickUserButton() {
        Utils.clickElement(driver, buttonUser);
    }

    public String getNameUser() {
        return Utils.getText(driver, nameUser);
    }

    public void fillForm(String name, int sexoIndex, String birthdate) {
        Utils.fillInput(driver, nameInput, name);
        Utils.selectByIndex(driver, sexoSelect, sexoIndex);
        Utils.fillInput(driver, birthdateInput, birthdate);
    }

    public void submitForm() {
        Utils.clickElement(driver, submitButton);
    }

    public void restoreForm() {
        Utils.clickElement(driver, restoreButton);
    }
}

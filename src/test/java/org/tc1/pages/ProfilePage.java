package org.tc1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import static org.tc1.utils.Utils.*;

public class ProfilePage {
    private WebDriver driver;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


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
        clickElement(driver, buttonUser);
    }

    public String getNameUser() {
        return getText(driver, nameUser);
    }

    public void fillForm(String name, Integer sexoIndex, String birthdate) {
        if(name != null) fillInput(driver, nameInput, name);
        if(sexoIndex != null) selectByIndex(driver, sexoSelect, sexoIndex);
        if(birthdate != null) fillInput(driver, birthdateInput, birthdate);
    }

    public void submitForm() {
        clickElement(driver, submitButton);
    }

    public void restoreForm() {
        clickElement(driver, restoreButton);
    }

    public boolean compareFields(String name, Integer sexoIndex, String birthdate) {
        String nameField = getInputValue(driver, nameInput);

        List<String> sexoOptions;
        String sexoValue = null;
        if(sexoIndex != null) {
            sexoOptions = getSelecteOptions(driver, sexoSelect);
            sexoValue = sexoOptions.get(sexoIndex);
        }
        String sexoField = getSelectValue(driver, sexoSelect);
        String birthdateField = getInputValue(driver, birthdateInput);
        String birthdateFormatted;
        try{
            birthdateFormatted = convertDateFormat(birthdateField, "yyyy-MM-dd", "dd/MM/yyyy");
        } catch (Exception e) {
            birthdateFormatted = "";
        }

        return (Objects.equals(name, nameField) || name == null) &&
                (Objects.equals(sexoValue, sexoField) || sexoIndex == null) &&
                (Objects.equals(birthdate, birthdateFormatted) || birthdate == null);
    }
}

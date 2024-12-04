package org.tc1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {
    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    private By name = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/label[1]/input");
    private By email = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/label[2]/input");
    private By sexo = By.xpath("//*[@id=\"sexo\"]");
    private By birthdate = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/label[4]/input");
    private By type = By.xpath("//*[@id=\"user_type\"]");
    private By password = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/label[6]/input");
    private By confirm_password = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/label[7]/input");
    private By submit_button = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/div/button[2]");
    private By cancel_button = By.xpath("//*[@id=\"root\"]/div/nav/div[3]/div/form/div/button[1]");

    public void fillForm(String name, String email, String sexo, String birthdate, String type, String password, String confirm_password) {
        driver.findElement(this.name).sendKeys(name);
        driver.findElement(this.email).sendKeys(email);
        driver.findElement(this.sexo).sendKeys(sexo);
        driver.findElement(this.birthdate).sendKeys(birthdate);
        driver.findElement(this.type).sendKeys(type);
        driver.findElement(this.password).sendKeys(password);
        driver.findElement(this.confirm_password).sendKeys(confirm_password);
    }

    public void submitForm() {
        driver.findElement(this.submit_button).click();
    }

    public void cancelForm() {
        driver.findElement(this.cancel_button).click();
    }
}

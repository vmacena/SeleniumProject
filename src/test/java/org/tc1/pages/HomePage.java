package org.tc1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.tc1.utils.Utils;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By buttonUser = By.xpath("//*[@id=\"root\"]/div/nav/button");
    private By nameUser = By.xpath("//*[@id=\"root\"]/div/nav/button/p");

    public void clickUserButton() {
        Utils.clickElement(driver, buttonUser);
    }

    public String getNameUser() {
        return Utils.getText(driver, nameUser);
    }
}

package org.tc1.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utils {
    public static WebElement waitForVisibility(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickability(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void fillInput(WebDriver driver, By locator, String text) {
        WebElement inputField = waitForVisibility(driver, locator, 10);
        inputField.clear();
        inputField.sendKeys(text);
    }

    public static void clickElement(WebDriver driver, By locator) {
        WebElement element = waitForClickability(driver, locator, 10);
        element.click();
    }

    public static void selectByIndex(WebDriver driver, By locator, int index) {
        WebElement dropdown = waitForVisibility(driver, locator, 10);
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }

    public static String getText(WebDriver driver, By locator) {
        WebElement element = waitForVisibility(driver, locator, 10);
        return element.getText();
    }

    public static void sendKeys(WebDriver driver, By locator, String keys) {
        WebElement element = waitForVisibility(driver, locator, 10);
        element.clear();
        element.sendKeys(keys);
    }
}

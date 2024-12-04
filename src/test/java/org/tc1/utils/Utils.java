package org.tc1.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    public static boolean waitForFieldToBeEditable(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(locator, "readonly", "true")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static boolean verifyIsDisplayed(WebDriver driver, By locator, int timeoutInSeconds) {
        try {
            WebElement modal = waitForVisibility(driver, locator, timeoutInSeconds);
            return modal.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static void refreshScreen(WebDriver driver) {
        driver.navigate().refresh();
    }

    public static List<String> getSelecteOptions(WebDriver driver, By locator) {
        WebElement selectElement = driver.findElement(locator);
        Select select = new Select(selectElement);
        return select.getOptions().stream().map(WebElement::getText).toList();
    }

    public static String getInputValue(WebDriver driver, By locator) {
        WebElement inputField = waitForVisibility(driver, locator, 2);
        return inputField.getAttribute("value");
    }

    public static String getSelectValue(WebDriver driver, By locator) {
        WebElement selectElement = driver.findElement(locator);
        Select select = new Select(selectElement);
        return select.getFirstSelectedOption().getText();
    }

    public static String convertDateFormat(String date, String fromFormat, String toFormat) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(fromFormat);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(toFormat);
        LocalDate localDate = LocalDate.parse(date, inputFormatter);
        return localDate.format(outputFormatter);
    }
}

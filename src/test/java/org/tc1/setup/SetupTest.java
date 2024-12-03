package org.tc1.setup;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SetupTest {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected String BASE_URL = "https://site-test-selenium.vercel.app/";

    @BeforeAll
    public static void setUp() {
        System.out.println("Starting WebDriver initialization...");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-web-security");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Validate WebDriver Initialization")
    public void testDriverInitialization() {
        assertNotNull(
                driver, "WebDriver was not initialized correctly.");
    }
}
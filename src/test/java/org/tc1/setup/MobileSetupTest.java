package org.tc1.setup;

import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MobileSetupTest extends SetupTest {

    @BeforeAll
    public static void setUp() {
        System.out.println("Starting WebDriver initialization for mobile...");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-web-security");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=390,844"); // iPhone 12 Pro

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get(BASE_URL);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
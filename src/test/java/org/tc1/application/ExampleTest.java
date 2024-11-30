package org.tc1.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.tc1.setup.SetupTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleTest extends SetupTest {

    @Test
    @DisplayName("Validate H1 Heading Text")
    public void testHeaderText() {
        driver.get(BASE_URL);

        // take a xpath in browser
        WebElement header = driver.findElement(By.xpath("//*[@id=\"root\"]/div/nav/div[1]/h1"));

        // verifying content :)
        assertEquals("Hospital Digital", header.getText(), "The H1 heading text is incorrect.");
    }

}




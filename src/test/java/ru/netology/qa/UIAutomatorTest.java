package ru.netology.qa;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.qa.screens.ButtonScreen;

import java.net.MalformedURLException;
import java.net.URL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UIAutomatorTest {
    private AppiumDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AppiumDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() {
        ButtonScreen button = new ButtonScreen(driver);

        button.userInput.sendKeys("Netology");
        button.buttonChange.click();
        button.textToBeChanged.isDisplayed();
        Assertions.assertEquals(button.textToBeChanged.getText(), "Netology");
        button.userInput.sendKeys("           ");
        button.buttonChange.click();
        button.textToBeChanged.isDisplayed();
        Assertions.assertEquals(button.textToBeChanged.getText(), "Netology");
    }


    @Test
    public void sampleTestNew() {
        ButtonScreen button = new ButtonScreen(driver);
        button.userInput.sendKeys("NEW TEXT");
        button.buttonActivity.click();
        button.activityText.isDisplayed();
        Assertions.assertEquals(button.activityText.getText(), "NEW TEXT");
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}


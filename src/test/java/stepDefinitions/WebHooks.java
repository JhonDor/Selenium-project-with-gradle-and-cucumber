package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import configuration.DriverWeb;
//import org.finalExamTae.utils.webtestdata.WebData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class for Web automation hooks.
 */
public class WebHooks {

    private static DriverWeb driver;

    /**
     * Before hook for initializing the driver and going to the 'Home' page.
     */
    @Before
    public void environmentSetUp(Scenario scenario) {
        scenario.getSourceTagNames().forEach(tag -> {
            if (tag.equals("@webAutomation")) {
                driver = new DriverWeb();
                driver.getDriver().get("https://practicesoftwaretesting.com/");
                // Skip maximize() call which causes CDP errors with Chrome 148
                // Window size is already set via ChromeOptions
            }
        });

    }

    /**
     * After hook for taking screenshots on failure and closing the browser.
     */
    @After
    public void tearDown(Scenario scenario) {
        scenario.getSourceTagNames().forEach(tag -> {
            if (tag.equals("@webAutomation")) {
                // Take screenshot on failure before closing the browser
                if (scenario.isFailed() && driver != null) {
                    takeScreenshot(scenario);
                }
                // Close the browser
                if (driver != null) {
                    try {
                        driver.getDriver().quit();
                    } catch (Exception e) {
                        System.err.println("Error closing driver: " + e.getMessage());
                    }
                }
            }

        });
    }

    /**
     * Takes a screenshot and attaches it to the Cucumber report on test failure.
     *
     * @param scenario The current Cucumber scenario
     */
    private void takeScreenshot(Scenario scenario) {
        try {
            if (driver != null && driver.getDriver() != null) {
                byte[] screenshot = ((TakesScreenshot) driver.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName() + "_failure_" + System.currentTimeMillis());
                System.out.println("Screenshot attached for scenario: " + scenario.getName());
            } else {
                System.err.println("Driver is null, cannot take screenshot for scenario: " + scenario.getName());
            }
        } catch (Exception e) {
            System.err.println("Failed to take screenshot for scenario '" + scenario.getName() + "': " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Allow to get the current driver instance.
     *
     * @return Current WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver.getDriver();
    }
}

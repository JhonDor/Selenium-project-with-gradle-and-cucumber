package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import configuration.DriverWeb;
//import org.finalExamTae.utils.webtestdata.WebData;
import org.openqa.selenium.WebDriver;

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
                driver.getDriver().get("https://magento.softwaretestingboard.com/");
                driver.getDriver().manage().window().maximize();
            }
        });

    }

    /**
     * After hook for closing the browser.
     */
    @After
    public void tearDown(Scenario scenario) {
        scenario.getSourceTagNames().forEach(tag -> {
            if (tag.equals("@webAutomation")) {
                driver.getDriver().quit();
            }

        });
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

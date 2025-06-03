package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;

public class ExampleSteps {
    WebDriver driver;

    @Given("I open the Google homepage")
    public void i_open_the_google_homepage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
    }

    @Then("the title should be {string}")
    public void the_title_should_be(String expectedTitle) {
        assertEquals(expectedTitle, driver.getTitle());
        driver.quit();
    }
}
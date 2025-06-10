package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import pageObjects.HomePage;
import pageObjects.LoginPage;


public class MagentoStepsDefinitions {
  //  WebDriver driver;
    private HomePage homePage;
    LoginPage loginPage = new LoginPage(WebHooks.getDriver());



    @Given("I am in the home page")
    public void iAmInTheESPNWebsiteHomePage() {
        homePage = new HomePage(WebHooks.getDriver());
    }

    @When("I click in sing in")
    public void clickSingIn () {
        homePage.clickSignIn();
    }

    @When("I send the login info")
    public void sigIn () {
        loginPage.sendLoginInfo();
    }

    @Then("The welcome user text should be visible")
    public void verifyLogIn () {
        Assert.assertTrue("The Welcome text was not visible",homePage.verifyWelcomeText());
    }
}
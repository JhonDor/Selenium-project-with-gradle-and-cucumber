package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


import pageObjects.HomePage;
import pageObjects.LoginPage;

import java.util.List;


public class MagentoStepsDefinitions {
    private HomePage homePage;
    LoginPage loginPage = new LoginPage(WebHooks.getDriver());



    @Given("I am in the home page")
    public void iAmInTheHomePage() {
        homePage = new HomePage(WebHooks.getDriver());
    }

    @When("I click in sing in")
    public void clickSingIn () {
        homePage.clickSignIn();
    }

    @When("I send the login information")
    public void sigIn () {
        loginPage.sendLoginInfo();
    }

    @Then("The welcome user text should be visible")
    public void verifyLogIn () {
        Assert.assertTrue("The Welcome text was not visible",homePage.verifyWelcomeText());
    }

    @When("I scroll to the hot sellers section")
    public void scrollToHotSellers () {
        homePage.scrollToHotSellers();
    }

    @Then("the hot sellers section should have 6 products")
    public void countHotSellersItems () {
        Assert.assertEquals("The amount of hot sellers products is incorrect", 6, homePage.countHotSellersItems());
    }

    @Then("the hot sellers items should have all their information displayed")
    public void verifyHotSellersItemsInformation () {
        List<String> notDisplayedElements = homePage.checkElementsVisibility();
        Assert.assertTrue("The following elements are not displayed: " + notDisplayedElements, notDisplayedElements.isEmpty());
    }
}
package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


import pageObjects.HomePage;
import pageObjects.JacketsPage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;

import java.util.List;


public class MagentoStepsDefinitions {
    private HomePage homePage;
    LoginPage loginPage = new LoginPage(WebHooks.getDriver());
    private JacketsPage jacketsPage = new JacketsPage(WebHooks.getDriver());
    private ProductPage productPage = new ProductPage(WebHooks.getDriver());



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

    @Given("I am logged in the website")
    public void loggedUser () {
        homePage = new HomePage(WebHooks.getDriver());
        homePage.clickSignIn();
        loginPage.sendLoginInfo();
        homePage.verifyWelcomeText();
    }

    @When("I click on the woman dropdown")
    public void clickOnWomanDropDown () {
        homePage.clickWomenDropDown();
    }

    @When("I click on the tops dropdown")
    public void clickOnTopsDropDown () {
        homePage.clickTopsDropDown();
    }

    @When("I click on the jackets option")
    public void clickOnWomanJackets () {
        homePage.clickWomenJackets();
    }

    @When("I add the first jacket to the shopping cart")
    public void addJacketToShoppingCart () {
        jacketsPage.clickItemByIndex(0);
    }

    @When("I click Add to Cart")
    public void clickAddToCart() {
        productPage.clickAddToCart();
    }

    @When("I choose a color")
    public void chooseColor() {
        productPage.chooseColor();
    }

    @Then("The errors about size and color being required should be visible")
    public void areErrorsVisible() {
        boolean areErrorsVisible = productPage.areErrorsVisible();
        Assert.assertTrue("One or both error messages are not visible.", areErrorsVisible);
    }


}
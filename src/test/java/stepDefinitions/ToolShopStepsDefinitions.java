package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


import pageObjects.*;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ToolShopStepsDefinitions {
    private HomePage homePage;
    LoginPage loginPage = new LoginPage(WebHooks.getDriver());
    private JacketsPage jacketsPage = new JacketsPage(WebHooks.getDriver());
    private ProductPage productPage = new ProductPage(WebHooks.getDriver());
    private MyAccount myAccount = new MyAccount(WebHooks.getDriver());;



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

    @Then("The My account should be visible")
    public void verifyLogIn () {
        Assert.assertEquals("My account",myAccount.getTitle());
    }

    @When("I scroll to the hot sellers section")
    public void scrollToHotSellers () {
        homePage.scrollToHotSellers();
    }

    @Then("9 products should be displayed")
    public void countHotSellersItems () {
        Assert.assertEquals("The amount of products is incorrect", 9, homePage.countProducts());
    }

    @And("The home page should have a banner")
    public void isBannerDisplayed () {
        assertTrue("The banner was not displayed",homePage.isBannerVisible());
    }

    @And("The sort option should be displayed")
    public void isSortOptionDisplayed () {
        assertTrue("The banner was not displayed",homePage.isSortVisible());
    }

    @And("Price Range should be displayed")
    public void isPriceRangeDisplayed () {
        assertTrue("The banner was not displayed",homePage.isPriceRangeVisible());
    }

    @And("Filters should be displayed")
    public void areFiltersDisplayed () {
        assertTrue("The banner was not displayed",homePage.areFiltersVisible());
    }

    @When("I search for {string} in the search bar")
    public void searchProduct (String product) {
        homePage.searchForTool(product);
    }

    @Then("{int} products should be displayed")
    public void productsFound (int amount) {
        assertEquals(amount, homePage.countProductsAfterSearch(), "The product count does not match the expected value.");
    }

    @Then("All the products should be {string}")
    public void verifyProducts (String product) {
        assertTrue("Not all product titles contain the expected substring.",
                homePage.doAllProductTitlesContain(product));
    }

    @Then("I click in the sort dropdown")
    public void clickSortDropDOwn () {
        homePage.clickSortDropDown();
    }

    @And("I sort the products by alphabetical order")
    public void iSelectTheOptionNameAZ() {
        homePage.selectOrder();
    }

    @Then("All products should be in alphabetical order")
    public void allProductsShouldBeInAlphabeticalOrder() {
        assertTrue("Not all products are in alphabetical order", homePage.areProductsSortedAlphabetically());
    }

























    @Then("the hot sellers items should have all their information displayed")
    public void verifyHotSellersItemsInformation () {
        List<String> notDisplayedElements = homePage.checkElementsVisibility();
        assertTrue("The following elements are not displayed: " + notDisplayedElements, notDisplayedElements.isEmpty());
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
        assertTrue("One or both error messages are not visible.", areErrorsVisible);
    }



}
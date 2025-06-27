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
    private ProductPage productPage = new ProductPage(WebHooks.getDriver());
    private MyAccount myAccount = new MyAccount(WebHooks.getDriver());
    private RegisterAccount registerAccount = new RegisterAccount(WebHooks.getDriver());
    private ShoppingCart shoppingCart = new ShoppingCart(WebHooks.getDriver());

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



    @Given("I am logged in the website")
    public void loggedUser () {
        homePage = new HomePage(WebHooks.getDriver());
        loginPage.sendLoginInfo();

    }


    @When("I click Add to Cart")
    public void clickAddToCart() {
        productPage.clickAddToCart();
    }


    @When("I click on the product")
    public void iClickOnTheProduct(){
        //Thread.sleep(5000);
        homePage.clickProductByTitle();
    }

    @Then("The title, product image, description, price, input quantity and add to favourites should be visible")
    public void theTitleProductImageDescriptionPriceInputQuantityAndAddToFavouritesShouldBeVisible() {
        List<String> notDisplayedElements = productPage.checkElementsVisibility();
        assertTrue("The following elements are not displayed: " + notDisplayedElements, notDisplayedElements.isEmpty());
    }

    @Then("The pop-up showing the product was added to the shopping cart should be visible")
    public void thePopUpShouldBeVisible() {
        productPage.isPopUpVisible();

    }

    @And("I click on the shopping cart icon")
    public void iClickOnTheShoppingCartIcon() {
        homePage.clickShoppingCartIcon();
    }

    @And("The item, quantity, price and total should be visible")
    public void theItemQuantityPriceAndTotalAreVisible() {
        List<String> notDisplayedElements = shoppingCart.checkElementsVisibility();
        assertTrue("The following elements are not displayed: " + notDisplayedElements, notDisplayedElements.isEmpty());


    }

    @When("I click the register link")
    public void iClickTheRegisterLink() {
        loginPage.clickRegisterLink();

    }

    @And("I fill the registration form")
    public void iFillTheRegistrationForm() {
        registerAccount.IFillTheRegisterInformation();
    }

    @Then("I am already logged in")
    public void iAmAlreadyLoggedIn() {
        loginPage.loginProcess();
    }

    @And("I click proceed to checkout")
    public void iClickProceedToCheckout() {
        shoppingCart.clickProceedToCheckout();
    }

    @And("In the sing in step I click in the second proceed to checkout")
    public void inTheSingInStepIClickInTheSecondProceedToCheckout() {
        shoppingCart.clickProceedToCheckoutTwo();
    }

    @And("in the payment select I choose credit card")
    public void inThePaymentSelectIChooseCreditCard() {
        shoppingCart.selectPayment();

    }

    @And("in the billing address step I click in the third proceed to checkout")
    public void inTheBillingAddressStepIClickInTheThirdProceedToCheckout() {
        shoppingCart.clickProceedToCheckoutThree();
    }

    @And("I fill the credit card information")
    public void iFillTheCreditCardInformation() {
        shoppingCart.fillCreditCardInformation();
    }

    @Then("the successful payment message should be displayed")
    public void theSuccessfulPaymentMessageShouldBeDisplayed() {
        assertTrue("The successful payment message wast not displayed", shoppingCart.isPaymentSuccessDisplayed());
    }

    @And("I click in the button to delete a product")
    public void iClickInTheButtonToDeleteAProduct() {
        shoppingCart.clickDeleteProduct();
    }

    @Then("The item, quantity, price and total should not be visible")
    public void areItemsNotVisible() {
        List<String> DisplayedElements = shoppingCart.checkElementsNotVisibility();
        assertTrue("The following elements are displayed: " + DisplayedElements, DisplayedElements.isEmpty());
    }
}
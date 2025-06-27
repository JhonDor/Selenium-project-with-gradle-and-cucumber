package pageObjects;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.*;



public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[data-test=\"nav-sign-in\"]")
    private WebElement sigIn;

    @FindBy(className = "card")
    private List<WebElement> productsInHomepage;

    @FindBy(xpath = "//h4[@class='grid-title' and contains(text(),'Sort')]")
    private WebElement sortTitle;

    @FindBy(xpath = "//h4[@class='grid-title' and contains(text(),'Filters')]")
    private WebElement filters;

    @FindBy(className = "img-fluid")
    private WebElement banner;

    @FindBy(css = ".ngx-slider.animate")
    private WebElement priceRange;

    @FindBy(id = "search-query")
    private WebElement searchBar;

    @FindBy(className = "card-title")
    private List<WebElement> productsTitle;


    @FindBy(css = "select[data-test='sort']")
    private WebElement sortDropDown;

    @FindBy(css = "a[data-test=\"nav-cart\"]")
    private WebElement shoppingCarIcon;

    @FindBy(css = "a[data-test=\"nav-home\"]")
    private WebElement homePageIcon;

    /**
     * this method verifies if all the elements are sorted in alphabetical order
     * @return true if al the elements are sorted correctly, false otherwise
     */
    public boolean areProductsSortedAlphabetically() {
        if (productsTitle == null || productsTitle.size() < 2) {
            return true;
        }

        String previousTitle = productsTitle.get(0).getText().toLowerCase();

        for (int i = 1; i < productsTitle.size(); i++) {
            String currentTitle = productsTitle.get(i).getText().toLowerCase();
            if (previousTitle.compareTo(currentTitle) > 0) {
                return false;
            }
            previousTitle = currentTitle;
        }

        return true;
    }

    /**
     * this method clicks in the title of the first element on the homepage
     */
    public void clickProductByTitle() {
        super.wait.until(ExpectedConditions.visibilityOfAllElements(productsTitle));
        super.clickElement(this.productsTitle.getFirst());

    }

    /**
     * clicks in the sig in button
     */
    public void clickSignIn (){
        super.clickElement(this.sigIn);
        new LoginPage(getDriver());
    }
    /**
     * Counts the amount of products in the homepage
     * @return the amount of products
     */
    public int countProducts () {
        return this.productsInHomepage.size();
    }
    /**
     * Checks if the banner element is visible on the page.
     * Waits for the banner to be visible before checking its display status.
     *
     * @return {@code true} if the banner is displayed; {@code false} otherwise.
     */
    public boolean isBannerVisible () {
        super.waitForVisibility(banner);
        return this.banner.isDisplayed();
    }

    /**
     * Checks if the sort title element is visible on the page.
     * Waits for the sort title to be visible before checking its display status.
     *
     * @return {@code true} if the sort title is displayed; {@code false} otherwise.
     */
    public boolean isSortVisible () {
        super.waitForVisibility(sortTitle);
        return this.sortTitle.isDisplayed();
    }
    /**
     * Checks if the price range element is visible on the page.
     * Waits for the price range to be visible before checking its display status.
     *
     * @return {@code true} if the price range is displayed; {@code false} otherwise.
     */
    public boolean isPriceRangeVisible () {
        super.waitForVisibility(priceRange);
        return this.priceRange.isDisplayed();
    }
    /**
     * Checks if the filters element is visible on the page.
     * Waits for the filters to be visible before checking their display status.
     *
     * @return {@code true} if the filters are displayed; {@code false} otherwise.
     */
    public boolean areFiltersVisible () {
        super.waitForVisibility(filters);
        return this.filters.isDisplayed();
    }

    /**
     * Searches for a tool by entering its name into the search bar and submitting the search form.
     * Clears any existing text in the search bar before entering the new tool name.
     *
     * @param toolName The name of the tool to search for.
     */
    public void searchForTool(String toolName) {
        searchBar.clear();
        searchBar.sendKeys(toolName);
        searchBar.submit();
    }

    /**
     * Counts the number of products displayed on the homepage after performing a search.
     * Waits until the number of products changes from the initial count before returning the new count.
     *
     * @return The number of products displayed on the homepage after the search.
     */
    public int countProductsAfterSearch () {
        int initialCount = countProducts();

        super.wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return countProducts() != initialCount;
            }
        });
        return this.productsInHomepage.size();
    }

    /**
     * Checks if each product title in the list contains the specified substring.
     *
     * @param searchString The substring to search for within each product title.
     * @return {@code true} if all product titles contain the substring; {@code false} otherwise.
     */
    public boolean doAllProductTitlesContain(String searchString) {
        int initialCount = countProducts();


        super.wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return countProducts() != initialCount;
            }
        });

        for (WebElement productTitle : productsTitle) {
            if (!productTitle.getText().contains(searchString)) {
                return false;
            }
        }
        return true;
    }

    /**
     * this method clicks on the sort dropdown
     */
    public void clickSortDropDown () {
        super.waitForVisibility(this.sortDropDown);
        super.clickElement(this.sortDropDown);
    }

    /**
     * this method selects the option to sort the products in alphabetical order
     */
    public void selectOrder () {
        Select select = new Select(this.sortDropDown);
        select.selectByVisibleText("Name (A - Z)");
    }

    /**
     * click on the shopping cart icon
     */
    public void clickShoppingCartIcon () {
        super.waitForVisibility(shoppingCarIcon);
        this.clickElement(shoppingCarIcon);
    }

    /**
     * clicks on the home page icon
     */
    public void clickHomePageIcon () {
        super.clickElement(homePageIcon);
    }

}

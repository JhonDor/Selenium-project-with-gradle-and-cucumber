package pageObjects;

import configuration.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;


public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[data-test=\"nav-sign-in\"]")
    private WebElement sigIn;

    @FindBy(className = "logged-in")
    private WebElement loggedInIcon;

    @FindBy(xpath = "//div[h2[text()='Hot Sellers']]")
    private WebElement hotSellersTitle;

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

    @FindBy(className = "product-price")
    private List<WebElement> productPrice;


    public boolean areProductsSortedAlphabetically() {
        super.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
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

    public boolean verifyProductTitlesSorted() {
        super.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return this.areProductsSortedAlphabetically();
    }










@FindBy(className = "product-item-name")
    private WebElement productItemName;

    @FindBy(className = "rating-summary")
    private WebElement ratingSummary;

    @FindBy(className = "reviews-actions")
    private WebElement reviewsActions;

    @FindBy(className = "normal-price")
    private WebElement itemPrice;

    @FindBy(css = ".swatch-attribute.size")
    private WebElement itemSize;

    @FindBy(css = ".swatch-attribute.color")
    private WebElement itemColor;

    @FindBy(id = "ui-id-4")
    private WebElement womenDropDown;

    @FindBy(id = "ui-id-9")
    private WebElement topsDropDown;

    @FindBy(id = "ui-id-11")
    private WebElement womenJacketsOption;


    ConfigLoader configLoader = new ConfigLoader();

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


    public void clickSortDropDown () {
        super.waitForVisibility(this.sortDropDown);
        super.clickElement(this.sortDropDown);
    }

    public void selectOrder () {
        Select select = new Select(this.sortDropDown);
        select.selectByVisibleText("Name (A - Z)");
    }












































































    /**
     * verifies if the welcome text is properly displayed
     * @return true if the welcome text is displayed, false otherwise
     */
    public boolean verifyWelcomeText () {
        String userName = configLoader.getUserName();
        super.waitForVisibility(this.loggedInIcon);
        String actualText = loggedInIcon.getText();
        return actualText.equals("Welcome, User "+ userName+"!");
    }

    /**
     * scrolls to the title of hot sellers
     */
    public void scrollToHotSellers () {
        super.scrollToElement(hotSellersTitle);
    }


    /**
     * Checks the visibility of all specified elements on the product page.
     * @return A list of element names that are not displayed.
     */

    public List<String> checkElementsVisibility() {
        Map<String, WebElement> elementsMap = new HashMap<>();
        elementsMap.put("productItemName", productItemName);
        elementsMap.put("ratingSummary", ratingSummary);
        elementsMap.put("reviewsActions", reviewsActions);
        elementsMap.put("itemPrice", itemPrice);
        elementsMap.put("itemSize", itemSize);
        elementsMap.put("itemColor", itemColor);

        List<String> notDisplayedElements = new ArrayList<>();

        for (Map.Entry<String, WebElement> entry : elementsMap.entrySet()) {
            if (!isElementDisplayed(entry.getValue())) {
                notDisplayedElements.add(entry.getKey());
            }
        }

        return notDisplayedElements;
    }
    /**
     * Checks if a given web element is displayed.
     *
     * @param element The web element to check.
     * @return True if the element is displayed, False otherwise.
     */

    private boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * clicks on the woman dropdown
     */
    public void clickWomenDropDown () {
        super.mouseOver(this.womenDropDown);
    }

    public void clickTopsDropDown () {
        super.mouseOver(this.topsDropDown);
    }

    public void clickWomenJackets () {
        super.mouseOver(this.womenJacketsOption);
        super.clickElement(this.womenJacketsOption);
    }
}

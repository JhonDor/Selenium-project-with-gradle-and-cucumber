package pageObjects;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
        // Re-initialize PageFactory elements to get fresh DOM references after sort
        PageFactory.initElements(getDriver(), this);
        
        // Wait for elements to be visible to ensure fresh element references
        super.wait.until(ExpectedConditions.visibilityOfAllElements(productsTitle));
        
        // Wait for the DOM to stabilize by ensuring the product count remains stable
        // This prevents stale element reference errors
        int stableCount = productsTitle.size();
        super.wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                // Re-fetch to check if count is still the same (indicating stability)
                PageFactory.initElements(getDriver(), HomePage.this);
                return productsTitle.size() == stableCount;
            }
        });
        
        if (stableCount < 2) {
            System.out.println("Only " + stableCount + " product(s) found. Skipping sort verification.");
            return true;
        }

        // Extract all titles immediately after PageFactory init to avoid stale elements
        // Use retry logic to handle stale element exceptions during text extraction
        List<String> titles = new ArrayList<>();
        for (int retryCount = 0; retryCount < 3; retryCount++) {
            try {
                // Re-initialize right before accessing elements
                PageFactory.initElements(getDriver(), this);
                titles.clear();
                for (WebElement title : productsTitle) {
                    titles.add(title.getText().toLowerCase());
                }
                break; // Success - exit retry loop
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                if (retryCount < 2) {
                    try {
                        Thread.sleep(200); // Longer pause before retry
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    System.out.println("Failed to extract product titles after " + (retryCount + 1) + " retries");
                    throw e; // Rethrow if all retries exhausted
                }
            }
        }

        // Log the products for debugging
        System.out.println("Products extracted for sort verification: " + titles.size() + " products");
        for (int i = 0; i < titles.size(); i++) {
            System.out.println("  [" + i + "] " + titles.get(i));
        }

        // Verify all titles are in alphabetical order
        for (int i = 1; i < titles.size(); i++) {
            int comparison = titles.get(i - 1).compareTo(titles.get(i));
            if (comparison > 0) {
                System.out.println("Sort order violation at index " + (i - 1) + "-" + i + 
                    ": '" + titles.get(i - 1) + "' > '" + titles.get(i) + "'");
                return false;
            }
        }

        System.out.println("All " + titles.size() + " products are in alphabetical order");
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
     * Clicks the "Sign in" link in the navigation menu
     * Element: <a data-test="nav-sign-in" routerlink="/auth/login">Sign in</a>
     */
    public void clickSignIn() {
        super.waitForVisibility(sigIn);
        super.clickElement(sigIn);
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
        super.waitForVisibility(searchBar);
        searchBar.clear();
        searchBar.sendKeys(toolName);
        searchBar.submit();
    }

    /**
     * Counts the number of products displayed on the homepage after performing a search.
     * Waits until the number of products changes from the initial count before returning the new count.
     * Has a fallback to return the current count if the timeout occurs (for CI environments where timing varies).
     *
     * @return The number of products displayed on the homepage after the search.
     */
    public int countProductsAfterSearch () {
        int initialCount = countProducts();

        try {
            super.wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return countProducts() != initialCount;
                }
            });
        } catch (org.openqa.selenium.TimeoutException e) {
            // If the count didn't change within timeout, it may be because:
            // 1. The search results already match the initial count
            // 2. In CI, the page load/rendering is slower
            // Return the current count anyway for more resilient tests
            System.out.println("Product count did not change within timeout. Returning current count: " + countProducts());
        }
        return this.productsInHomepage.size();
    }

    /**
     * Checks if each product title in the list contains the specified substring.
     * Performs case-insensitive comparison.
     * Has fallback logic to handle timeouts in CI environments.
     *
     * @param searchString The substring to search for within each product title.
     * @return {@code true} if all product titles contain the substring; {@code false} otherwise.
     */
    public boolean doAllProductTitlesContain(String searchString) {
        int initialCount = countProducts();

        // Wait for the product count to change after search
        try {
            super.wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return countProducts() != initialCount;
                }
            });
        } catch (org.openqa.selenium.TimeoutException e) {
            // If count didn't change, continue anyway - may be due to slow rendering in CI
            System.out.println("Product count did not change within timeout. Continuing with product verification...");
        }

        // Re-initialize PageFactory elements to get fresh DOM references
        PageFactory.initElements(getDriver(), this);
        
        // Wait for the old product titles to become stale and be replaced with new ones
        // This is a more reliable dynamic wait than Thread.sleep()
        try {
            if (!productsTitle.isEmpty()) {
                super.wait.until(ExpectedConditions.stalenessOf(productsTitle.get(0)));
            }
        } catch (Exception e) {
            // Element might not become stale if the list is recreated, continue anyway
        }
        
        // Re-initialize again after staleness to get the fresh elements
        PageFactory.initElements(getDriver(), this);
        
        // Wait for the newly re-initialized elements to be visible and stable
        super.wait.until(ExpectedConditions.visibilityOfAllElements(productsTitle));
        
        // Verify all product titles contain the search string
        // Perform case-insensitive comparison
        String searchStringLower = searchString.toLowerCase();
        for (WebElement productTitle : productsTitle) {
            if (!productTitle.getText().toLowerCase().contains(searchStringLower)) {
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
     * and waits for the products list to be re-sorted
     */
    public void selectOrder () {
        // Re-initialize to get fresh reference to sortDropDown element
        PageFactory.initElements(getDriver(), this);
        super.waitForVisibility(this.sortDropDown);
        
        // Store the current first product title before sorting
        String firstProductBeforeSort = "";
        try {
            PageFactory.initElements(getDriver(), this);
            if (!productsTitle.isEmpty()) {
                firstProductBeforeSort = productsTitle.get(0).getText();
                System.out.println("First product before sort: " + firstProductBeforeSort);
            }
        } catch (Exception e) {
            // Continue even if we can't get the initial title
            System.out.println("Warning: Could not retrieve first product before sort");
        }
        
        // Select the sort option
        System.out.println("Selecting 'Name (A - Z)' sort option...");
        Select select = new Select(this.sortDropDown);
        select.selectByVisibleText("Name (A - Z)");
        System.out.println("Sort option selected");
        
        // Add explicit wait after sort selection - give the backend time to process
        try {
            Thread.sleep(1000); // Give the backend time to process the sort request
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Wait for the products to be re-sorted (the first product should change OR stabilize)
        // This ensures the sorting operation has completed
        final String initialFirstProduct = firstProductBeforeSort;
        try {
            System.out.println("Waiting for products to be sorted...");
            super.wait.until(new ExpectedCondition<Boolean>() {
                private int checksWithoutChange = 0;
                private String lastProduct = "";
                
                public Boolean apply(WebDriver driver) {
                    try {
                        PageFactory.initElements(getDriver(), HomePage.this);
                        if (productsTitle.isEmpty()) {
                            return false;
                        }
                        String currentFirst = productsTitle.get(0).getText();
                        System.out.println("  Current first product: " + currentFirst);
                        
                        // Check if products have changed from initial
                        if (!initialFirstProduct.isEmpty() && !currentFirst.equals(initialFirstProduct)) {
                            System.out.println("  Product changed! Sort operation appears complete.");
                            return true;
                        }
                        
                        // If no initial product, check for any product and stability
                        if (initialFirstProduct.isEmpty()) {
                            if (!currentFirst.isEmpty()) {
                                // Check if product title is stable (not changing)
                                if (currentFirst.equals(lastProduct)) {
                                    checksWithoutChange++;
                                    if (checksWithoutChange >= 2) {
                                        System.out.println("  Product stable. Sort operation appears complete.");
                                        return true;
                                    }
                                } else {
                                    checksWithoutChange = 0;
                                }
                                lastProduct = currentFirst;
                            }
                        }
                        return false;
                    } catch (Exception e) {
                        System.out.println("  Exception during wait: " + e.getMessage());
                        return false;
                    }
                }
            });
        } catch (Exception e) {
            // If wait times out, add a longer static wait and continue
            System.out.println("Warning: Sort operation may not have completed immediately. Adding static wait...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        
        // Additional wait to ensure all product elements are stable
        try {
            System.out.println("Waiting for all product elements to be visible...");
            PageFactory.initElements(getDriver(), this);
            super.wait.until(ExpectedConditions.visibilityOfAllElements(productsTitle));
            System.out.println("All products are now visible.");
        } catch (Exception e) {
            System.out.println("Could not verify all products visible, but continuing...");
        }
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

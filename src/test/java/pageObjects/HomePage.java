package pageObjects;

import configuration.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "authorization-link")
    private WebElement sigIn;

    @FindBy(className = "logged-in")
    private WebElement loggedInIcon;

    @FindBy(xpath = "//div[h2[text()='Hot Sellers']]")
    private WebElement hotSellersTitle;

    @FindBy(className = "product-item")
    private List<WebElement> hotSellersItems;

    @FindBy(className = "product-image-photo")
    private WebElement productImagePhoto;

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
     * Counts the amount of hot sellers items
     * @return the amount of hot sellers items
     */
    public int countHotSellersItems () {
            return hotSellersItems.size();
    }

    /**
     * Checks the visibility of all specified elements on the product page.
     * @return A list of element names that are not displayed.
     */

    public List<String> checkElementsVisibility() {
        Map<String, WebElement> elementsMap = new HashMap<>();
        elementsMap.put("productImagePhoto", productImagePhoto);
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

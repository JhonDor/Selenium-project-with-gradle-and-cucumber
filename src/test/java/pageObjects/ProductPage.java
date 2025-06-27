package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "btn-add-to-cart")
    private WebElement addToCartButton;

    @FindBy(css = "[data-test=\"product-name\"]")
    private WebElement productTitle;

    @FindBy(css = ".figure-img.img-fluid")
    private WebElement productImage;

    @FindBy(css = ".input-group.quantity")
    private WebElement inputQuantity;

    @FindBy(id = "btn-add-to-favorites")
    private WebElement addToFavourites;

    @FindBy(css = "span[data-test=\"unit-price\"]")
    private WebElement unitPrice;

    @FindBy(id = "description")
    private WebElement productDescription;

    @FindBy(id = "toast-container")
    private WebElement addedToCartPopUp;

    /**
     * clicks on the add to cart button
     */
    public void clickAddToCart() {
        super.clickElement(this.addToCartButton);

    }

    /**
     * this method verifies the visibility of all the elements related to the product
     * @return the list of not displayed elements
     */
    public List<String> checkElementsVisibility() {
        super.waitForVisibility(addToCartButton);
        Map<String, WebElement> elementsMap = new HashMap<>();
        elementsMap.put("productTitle", productTitle);
        elementsMap.put("productImage", productImage);
        elementsMap.put("unitPrice", unitPrice);
        elementsMap.put("productDescription", productDescription);
        elementsMap.put("inputQuantity", inputQuantity);
        elementsMap.put("addToCartButton", addToCartButton);
        elementsMap.put("addToFavourites", addToFavourites);

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
     * this method verifies if the pop up of product added to the shopping cart is visible
     */
    public void isPopUpVisible () {
        super.waitForVisibility(addedToCartPopUp);
        this.addedToCartPopUp.isDisplayed();
    }
}


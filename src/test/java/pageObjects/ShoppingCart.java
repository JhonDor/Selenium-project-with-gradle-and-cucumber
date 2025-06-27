package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart extends BasePage{
    public ShoppingCart(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "col-md-4")
    private WebElement item;

    @FindBy(css = ".form-control.quantity")
    private WebElement quantity;

    @FindBy(css = "[data-test=\"product-price\"]")
    private WebElement price;

    @FindBy(css = "[data-test=\"line-price\"]")
    private WebElement linePrice;

    @FindBy(css = "[data-test=\"cart-total\"]")
    private WebElement cartTotal;

    @FindBy(css = "[data-test=\"proceed-1\"]")
    private WebElement proceedToCheckout;

    @FindBy(css = "[data-test=\"proceed-2\"]")
    private WebElement proceedToCheckoutTwo;

    @FindBy(css = "[data-test=\"proceed-3\"]")
    private WebElement proceedToCheckoutThree;

    @FindBy(id = "payment-method")
    private WebElement selectPayment;

    @FindBy(id = "credit_card_number")
    private WebElement creditCardNumber;

    @FindBy(id = "expiration_date")
    private WebElement expirationDate;

    @FindBy(id = "cvv")
    private WebElement cvv;

    @FindBy(id = "card_holder_name")
    private WebElement cardHolderName;

    @FindBy(css = "[data-test=\"finish\"]")
    private WebElement confirmPaymentButton;

    @FindBy(css = "[data-test=\"payment-success-message\"]")
    private WebElement successPayment;

    @FindBy(css = ".btn.btn-danger")
    private WebElement deleteProduct;

    @FindBy(id = "toast-container")
    private WebElement deletedProductPopUp;

    /**
     * clicks on the proceed to checkout button
     */
    public void clickProceedToCheckout () {
        super.clickElement(proceedToCheckout);
    }

    /**
     * clicks on the second proceed to checkout button
     */
    public void clickProceedToCheckoutTwo () {
        super.clickElement(proceedToCheckoutTwo);
    }

    /**
     * clicks on the third proceed to checkout button
     */
    public void clickProceedToCheckoutThree () {
        super.clickElement(proceedToCheckoutThree);
    }

    /**
     * selects credit card as payment method
     */
    public void selectPayment () {
        Select select = new Select(this.selectPayment);
        select.selectByVisibleText("Credit Card");
    }

    /**
     * fills all the credit card information
     */
    public void fillCreditCardInformation () {
        super.typeOnInput(this.creditCardNumber, "1111-2222-3333-4444");
        super.typeOnInput(this.expirationDate, "05/2030");
        super.typeOnInput(this.cvv, "123");
        super.typeOnInput(this.cardHolderName, "Testing");
        super.clickElement(this.confirmPaymentButton);
    }

    /**
     * verifies if the pop up of payment successful is displayed
     * @return true if the pop up is displayed, false otherwise
     */
    public boolean isPaymentSuccessDisplayed () {
        super.waitForVisibility(successPayment);
        return this.successPayment.isDisplayed();
    }

    /**
     * verifies if all the element of the shopping cart are visible
     * @return the list of not displayed elements if there are any
     */
    public List<String> checkElementsVisibility() {
        super.waitForVisibility(cartTotal);
        Map<String, WebElement> elementsMap = new HashMap<>();
        elementsMap.put("item", item);
        elementsMap.put("quantity", quantity);
        elementsMap.put("price", price);
        elementsMap.put("linePrice", linePrice);
        elementsMap.put("cartTotal", cartTotal);


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
     * clicks the delete product button
     */
    public void clickDeleteProduct () {
        super.waitForInvisibility(deletedProductPopUp);
        super.clickElement(deleteProduct);
    }

    /**
     * verifies if all the elements of the shopping cart are not visible
     * @return the list of visible elements if there are any
     */
    public List<String> checkElementsNotVisibility() {
        super.waitForInvisibility(cartTotal);
        Map<String, WebElement> elementsMap = new HashMap<>();
        elementsMap.put("item", item);
        elementsMap.put("quantity", quantity);
        elementsMap.put("price", price);
        elementsMap.put("linePrice", linePrice);
        elementsMap.put("cartTotal", cartTotal);


        List<String> DisplayedElements = new ArrayList<>();

        for (Map.Entry<String, WebElement> entry : elementsMap.entrySet()) {
            if (isElementDisplayed(entry.getValue())) {
                DisplayedElements.add(entry.getKey());
            }
        }
        return DisplayedElements;
    }
}

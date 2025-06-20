package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductPage extends BasePage{
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".action.primary.tocart")
    private WebElement addToCartButton;

    @FindBy(id = "super_attribute[143]-error")
    private WebElement missingSizeError;

    @FindBy(id = "super_attribute[93]-error")
    private WebElement missingColorError;

    @FindBy(id = "option-label-color-93-item-49")
    private WebElement color;


    public void clickAddToCart () {
        super.clickElement(this.addToCartButton);
    }

    /**
     * Verifies the visibility of both {@code missingSizeError} and {@code missingColorError} elements.
     * This method checks if both elements are displayed on the webpage. If both are visible, it returns true.
     * If either element is not visible, it logs a message indicating which element is not visible and returns false.
     *
     * @return true if both elements are visible, false otherwise.
     */
    public boolean areErrorsVisible() {
        boolean isSizeErrorVisible = missingSizeError.isDisplayed();
        boolean isColorErrorVisible = missingColorError.isDisplayed();

        if (isSizeErrorVisible && isColorErrorVisible) {
            return true;
        } else {
            if (!isSizeErrorVisible) {
                System.out.println("missingSizeError element is not visible.");
            }
            if (!isColorErrorVisible) {
                System.out.println("missingColorError element is not visible.");
            }
            return false;
        }
    }

    public void chooseColor () {
        super.clickElement(this.color);
    }
}

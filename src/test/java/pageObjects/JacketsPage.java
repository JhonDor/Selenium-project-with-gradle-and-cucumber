package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;

public class JacketsPage extends BasePage{

    public JacketsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".item.product.product-item")
    private List<WebElement> jacketsList;

    @FindBy(css = ".action.tocart.primary")
    private WebElement addToCartButton;



    public void clickItemByIndex(int index) {
        if (index >= 0 && index < jacketsList.size()) {
            WebElement itemToClick = jacketsList.get(index);

            super.clickElement(itemToClick);
        } else {
            System.out.println("Index out of bounds: " + index);
        }
    }



    @FindBy(id = "search-query")
    private WebElement searchBar;

    @FindBy(className = "card")
    private List<WebElement> productsInHomepage;

    public static boolean areProductsSortedAlphabetically(List<WebElement> productsTitle) {
        if (productsTitle == null || productsTitle.size() < 2) {
            // A null or single-element list is considered sorted
            return true;
        }

        String previousTitle = productsTitle.get(0).getText().toLowerCase();

        for (int i = 1; i < productsTitle.size(); i++) {
            String currentTitle = productsTitle.get(i).getText().toLowerCase();
            if (previousTitle.compareTo(currentTitle) > 0) {
                // If the previous title is greater than the current, the list is not sorted
                return false;
            }
            previousTitle = currentTitle;
        }

        return true;
    }

    public boolean verifyProductTitlesSorted() {
        return this.areProductsSortedAlphabetically(jacketsList);
    }
}

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
}

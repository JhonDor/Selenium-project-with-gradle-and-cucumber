package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount extends BasePage{
    public MyAccount(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h1[data-test=\"page-title\"]")
    private WebElement title;

    /**
     * this method get the text of the page title
     * @return the text of the title page
     */
    public String getTitle () {
        super.waitForVisibility(title);
        return title.getText();
    }


}

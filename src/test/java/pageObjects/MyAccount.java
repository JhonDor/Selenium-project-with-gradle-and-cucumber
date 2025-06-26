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

    public String getTitle () {
        super.waitForVisibility(title);
        return title.getText();
    }


}

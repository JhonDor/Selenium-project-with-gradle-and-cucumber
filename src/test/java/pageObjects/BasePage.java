package pageObjects;

import configuration.WebOperations;
import org.openqa.selenium.WebDriver;

public class BasePage extends WebOperations {

    public BasePage(WebDriver driver) {
        super(driver);
    }
}

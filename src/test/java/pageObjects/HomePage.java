package pageObjects;

import configuration.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "authorization-link")
    private WebElement sigIn;

    @FindBy(className = "logged-in")
    private WebElement loggedInIcon;

    ConfigLoader configLoader = new ConfigLoader();

    public void clickSignIn (){
        super.clickElement(this.sigIn);
        new LoginPage(getDriver());
    }

    public boolean verifyWelcomeText () {
        String userName = configLoader.getUserName();
        super.waitForVisibility(this.loggedInIcon);
        String actualText = loggedInIcon.getText();
        return actualText.equals("Welcome, User "+ userName+"!");
    }



}

package pageObjects;

import configuration.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "pass")
    private WebElement password;

    @FindBy(id = "send2")
    private WebElement singInButton;

    ConfigLoader configLoader = new ConfigLoader();

    /*
    This method sends the keys to log in
     */
    public void sendLoginInfo () {
        String email = configLoader.getEmail();
        String password = configLoader.getPassword();

        super.typeOnInput(this.email, email);
        super.typeOnInput(this.password, password);
        super.clickElement(this.singInButton);
    }
}

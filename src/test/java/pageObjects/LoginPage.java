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

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(className = "btnSubmit")
    private WebElement loginButton;

    ConfigLoader configLoader = new ConfigLoader();

    /**
    This method sends the keys to log in
     */
    public void sendLoginInfo () {
        String email = configLoader.getEmail();
        String password = configLoader.getPassword();

        super.typeOnInput(this.email, email);
        super.typeOnInput(this.password, password);
        super.clickElement(this.loginButton);
    }
}

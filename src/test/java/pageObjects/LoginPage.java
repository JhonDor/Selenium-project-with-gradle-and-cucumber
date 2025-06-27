package pageObjects;

import configuration.ConfigLoader;
import configuration.EmailGenerator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import stepDefinitions.WebHooks;

public class LoginPage extends BasePage {
    private HomePage homePage = new HomePage(WebHooks.getDriver());
    private RegisterAccount registerAccount = new RegisterAccount(WebHooks.getDriver());
    private MyAccount myAccount = new MyAccount(WebHooks.getDriver());

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(className = "btnSubmit")
    private WebElement loginButton;

    @FindBy(css = "a[data-test=\"register-link\"]")
    private WebElement register;

    @FindBy(xpath = "//h3[text()='Login']")
    private WebElement loginTitle;



    ConfigLoader configLoader = new ConfigLoader();

    /**
    This method sends the keys to log in
     */
    public void sendLoginInfo () {
        super.waitForVisibility(loginTitle);
        String password = configLoader.getPassword();
        String randomEmail = EmailGenerator.getRandomEmail();

        super.typeOnInput(this.email, randomEmail);
        super.typeOnInput(this.password, password);
        super.clickElement(this.loginButton);
    }

    /**
     * click on the register link
     */
    public void clickRegisterLink () {
        super.clickElement(register);
    }

    /**
     * this method does the whole login process including the registration and login
     */
    public void loginProcess (){
        homePage.clickSignIn();
        this.clickRegisterLink();
        registerAccount.IFillTheRegisterInformation();
        this.sendLoginInfo();
        myAccount.getTitle();
        homePage.clickHomePageIcon();

    }
}

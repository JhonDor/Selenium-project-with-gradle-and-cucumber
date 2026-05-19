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
     * Waits for the login page to be fully loaded and ready
     */
    public void waitForLoginPageToLoad() {
        try {
            // Wait for the login title to be visible (indicates page is ready)
            super.waitForVisibility(loginTitle);
        } catch (org.openqa.selenium.TimeoutException e) {
            // If the title element is not found, try waiting for the email input as fallback
            System.out.println("Login title not found, waiting for email input field instead");
            super.waitForVisibility(email);
        }
    }

    /**
    This method sends the keys to log in
     */
    public void sendLoginInfo () {
        // Wait for login page to be ready before filling the form
        waitForLoginPageToLoad();
        
        String password = configLoader.getPassword();
        String randomEmail = EmailGenerator.getRandomEmail();

        super.typeOnInput(this.email, randomEmail);
        super.typeOnInput(this.password, password);
        super.clickElement(this.loginButton);
    }

    /**
     * Sends login credentials using email from config.properties
     * Uses the email address defined in config.properties instead of a random email
     */
    public void sendLoginInfoWithConfigEmail() {
        // Wait for login page to be ready before filling the form
        waitForLoginPageToLoad();
        
        String email = configLoader.getEmail();
        String password = configLoader.getPassword();

        super.typeOnInput(this.email, email);
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

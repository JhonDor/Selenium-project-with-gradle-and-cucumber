package pageObjects;

import configuration.EmailGenerator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class RegisterAccount extends BasePage{
    public RegisterAccount(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "first_name")
    private WebElement firstName;

    @FindBy(id = "last_name")
    private WebElement lastName;

    @FindBy(id = "dob")
    private WebElement dateOfBirth;

    @FindBy(id = "street")
    private WebElement street;

    @FindBy(id = "postal_code")
    private WebElement postalCode;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "state")
    private WebElement state;

    @FindBy(id = "country")
    private WebElement country;

    @FindBy(id = "phone")
    private WebElement phone;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(css = ".btnSubmit.mb-3")
    private WebElement registerButton;

    /**
     * this method fills all the registration information
     */
    public void IFillTheRegisterInformation () {
        String randomEmail = EmailGenerator.getRandomEmail();

        super.waitForVisibility(firstName);
        super.typeOnInput(firstName,("Testing"));
        super.typeOnInput(lastName,("Testing"));
        super.typeOnInput(dateOfBirth,("01/01/2000"));
        super.typeOnInput(street,("calle falsa 123"));
        super.typeOnInput(postalCode,("1234"));
        super.typeOnInput(city,("city"));
        super.typeOnInput(state,("state"));
        Select select = new Select(this.country);
        select.selectByVisibleText("Albania");
        super.typeOnInput(phone,("123456789"));
        super.typeOnInput(email,(randomEmail));
        super.typeOnInput(password,("Testing@Testing1"));
        super.clickElement(registerButton);
    }
}


package configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.PageFactory.initElements;

/**
 * This is the class to define all common operations in the web application.
 */
public class WebOperations {

    private final WebDriver driver;
    protected final WebDriverWait wait;

    public WebOperations(WebDriver driver) {
        this.driver = driver;
        Duration waitDuration = Duration.ofSeconds(20);
        this.wait = new WebDriverWait(driver, waitDuration);
        initElements(driver, this);
    }

    /**
     * getter for the WebDriver
     *
     * @return the WebDriver
     */
    public WebDriver getDriver() {
        return this.driver;
    }

    /**
     * method to  click elements
     *
     * @param element WebElement
     */
    public void clickElement(WebElement element) {
        this.waitForVisibility(element);
        this.waitForClickable(element);
        element.click();
    }

    /**
     * method  to wait for an element to be clickable before performing actions
     *
     * @param element WebElement
     */
    public void waitForClickable(WebElement element) {
        this.wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Wrapper for sendKeys event.
     *
     * @param element WebElement
     * @param text    String
     */
    public void typeOnInput(WebElement element, String text) {
        this.waitForVisibility(element);
        element.sendKeys(text);
    }


    /**
     * Allows to wait for an element to be visible.
     *
     * @param element a WebElement provided
     */
    public void waitForVisibility(WebElement element) {
        this.wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Allows to wait for an element to be visible.
     * @param element a webElement provided
     * @param seconds the amount of seconds to wait
     */
    public void waitForVisibility(WebElement element, Duration seconds){
        new WebDriverWait(driver,seconds).until(ExpectedConditions.visibilityOf(element));

    }

    /**
     * Allows to wait for an element to be invisible.
     *
     * @param element WebElement
     */
    public void waitForInvisibility(WebElement element) {
        this.wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Allows to move the mouse over a given element.
     *
     * @param element WebElement
     */
    public void mouseOver(WebElement element) {
        this.waitForVisibility(element);
        new Actions(getDriver()).moveToElement(element).perform();
    }

    /**
     * Allows to wait for an element to be present on the DOM of the page.
     *
     * @param locator String
     */
    public void waitForPresenceOfElement(String locator) {
        this.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
    }

    /**
     * Allows to wait for an element attribute to change to a given value.
     *
     * @param element   WebElement
     * @param attribute String
     * @param value     String
     */
    public void waitForAttributeChange(WebElement element, String attribute, String value) {
        this.wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

    public void scrollToElement (WebElement element) {
        new Actions(driver)
                .scrollToElement(element)
                .perform();
    }

    public void ScrollByGivenAmount (WebElement element) {
        int deltaY = element.getRect().y;
        new Actions(driver)
                .scrollByAmount(0, deltaY)
                .perform();
    }

    public void waitForStaleness (WebElement element) {
        this.wait.until(ExpectedConditions.stalenessOf(element));
    }

}
